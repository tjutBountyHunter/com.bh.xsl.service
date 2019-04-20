package service.impl;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import dao.JedisClient;
import enums.UserStateEnum;
import mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;
import pojo.*;
import service.*;
import util.*;

import java.util.*;

import static util.ImageFile.fileName;

@Service
public class UserviceImpl implements UserService {
    @Autowired
    private HunMaster hunMaster;
    @Autowired
    private ImageSave imageSave;
    @Autowired
    private XslFileMapper xslFileMapper;
    @Autowired
    private XslMajorMessageMapper xslMajorMessageMapper;
    @Autowired
    private XslCollegeMessageMapper xslCollegeMessageMapper;
    @Autowired
    private XslSchoolMessageMapper xslSchoolMessageMapper;
	@Autowired
	private XslHunterMapper xslHunterMapper;
	@Autowired
	private XslMasterMapper xslMasterMapper;
    @Autowired
    private XslUserMapper xslUserMapper;
    @Autowired
    private XslSchoolinfoMapper xslSchoollinfoMapper;
    @Autowired
    private XslUserUpdateMapper xslUserUpdateMapper;
    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;
    @Value("${REDIS_USER_SESSION_CODE_KEY}")
    private String REDIS_USER_SESSION_CODE_KEY;
    @Value("${REDIS_USER_SESSION_PASSWORD}")
    private String REDIS_USER_SESSION_PASSWORD;
    @Value("${Login_SESSION_EXPIRE}")
    private Integer Login_SESSION_EXPIRE;
    @Value("${Login_SESSION_EXPIRE_CODE}")
    private Integer Login_SESSION_EXPIRE_CODE;
    @Value("${Login_SESSION_EXPIRE_PASSWORD}")
    private Integer Login_SESSION_EXPIRE_PASSWORD;

    private static final Logger logger = LoggerFactory.getLogger(UserviceImpl.class);

    //注册
    @Override
    public XslResult createUser(XslUserRegister xslUserRegister) {
        try {

//            all = new String(all.getBytes("iso-8859-1"), "utf-8");
//            XslUserRegister xslUserRegister = JsonUtils.jsonToPojo(all, XslUserRegister.class);
//            XslUserExample example = new XslUserExample();
//            XslUserExample.Criteria criteria = example.createCriteria();
//            criteria.andPhoneEqualTo(xslUserRegister.getPhone());
//            List<XslUser> list = xslUserMapper.selectByExample(example);
//            if(list.size()!=0&&!list.equals("")){
//                return XslResult.build(400,"该手机号已经注册过");
//            }

            xslUserRegister.setUserId(UUID.randomUUID().toString());

            //初始化学校信息
            XslResult schoolId = createUserSchool(xslUserRegister);

            //初始化用户表
            XslResult xslResult = createUseruser(xslUserRegister, (String) schoolId.getData());

            Map<String, Integer> map = hunMaster.insertPeople((Integer) xslResult.getData());
            JedisClientUtil.set(REDIS_USER_SESSION_KEY + ":" + xslUserRegister.getPhone(), xslUserRegister.getToken(), Login_SESSION_EXPIRE);

            XslResult xsl = defaultFileImage(xslUserRegister.getPhone());

            map.put("id", (Integer) xslResult.getData());
            System.out.println(map.get("id") + " 51651");
            xslUserUpdateMapper.updateXslUser(map);
            XslUserExample example1 = new XslUserExample();
            XslUserExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andPhoneEqualTo(xslUserRegister.getPhone());
            List<XslUser> list1 = xslUserMapper.selectByExample(example1);
            XslUser xslUser = list1.get(0);
            xslUser.setUrl((String) xsl.getData());
            return XslResult.ok(xslUser);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

	/**
	 * 快速注册
	 *
	 * @return
	 */
	@Override
	public XslResult quickCreateUser(XslUserRegister xslUserRegister){
		XslUser xslUser = new XslUser();
		xslUser.setUserId(UUID.randomUUID().toString());

		XslHunter xslHunter = initXslHunter(xslUser);

		XslMaster xslMaster = initXslMaster(xslUser);

		//初始化用户信息
		initUserInfo(xslUserRegister, xslUser, xslHunter, xslMaster);

		return XslResult.ok();

	}

	/**
     * 上传图片
     *
     * @param uploadFile
     * @param phone
     * @return
     */
    @Override
    public XslResult createFile(MultipartFile uploadFile, String phone) {
        XslResult xslResult = createFileTool(uploadFile, phone);
        return xslResult;
    }

    public XslResult defaultFileImage(String phone) {
        XslUserExample xslUserExample = new XslUserExample();
        XslUserExample.Criteria criteria = xslUserExample.createCriteria();
        criteria.andPhoneEqualTo(phone);
        XslFile xslFile = new XslFile();
        List<XslUser> list = xslUserMapper.selectByExample(xslUserExample);
        if (list.size() == 0 && list.isEmpty()) {
            return XslResult.build(400, "用户不存在");
        } else {
            XslUser xslUser = list.get(0);

            xslFile.setDesc("学生证");
            xslFile.setUserid(xslUser.getId());
            xslFile.setUpdatedate(new Date());
            xslFile.setCreatedate(new Date());
            xslFile.setUrl(fileName());
            try {
                xslFileMapper.insertSelective(xslFile);
                return XslResult.ok(xslFile.getUrl());
            } catch (Exception e) {
                e.printStackTrace();
                return XslResult.build(500, "服务器异常");
            }
        }
    }

    public XslResult createFileTool(MultipartFile uploadFile, String phone) {
        XslUserExample xslUserExample = new XslUserExample();
        XslUserExample.Criteria criteria = xslUserExample.createCriteria();
        criteria.andPhoneEqualTo(phone);
        List<XslUser> list = xslUserMapper.selectByExample(xslUserExample);
        if (list.size() == 0 && list.isEmpty()) {
            return XslResult.build(400, "用户不存在");
        } else {
            XslUser xslUser = list.get(0);
            XslFile xslFile = new XslFile();
            xslFile.setDesc("学生证");
            xslFile.setUserid(xslUser.getId());
            xslFile.setUpdatedate(new Date());
            xslFile.setCreatedate(new Date());
            Map<String, Object> map = new HashMap<>();
            map = imageSave.uploadPicture(uploadFile);
            if ((int) map.get("error") == 1) {
                return XslResult.build(400, (String) map.get("message"));
            } else {
                xslFile.setUrl((String) map.get("url"));
            }
            try {
                xslFileMapper.insert(xslFile);
                return XslResult.ok(xslFile.getUrl());
            } catch (Exception e) {
                e.printStackTrace();
                return XslResult.build(500, "服务器异常");
            }
        }
    }

    /**
     * 用户表
     *
     * @param xslUserRegister
     * @param schoolId
     * @return
     */
    private XslResult createUseruser(XslUserRegister xslUserRegister, String schoolId) {
        XslUser xslUser = new XslUser();
        xslUser.setPhone(xslUserRegister.getPhone());
        xslUser.setSchoolinfo(schoolId);
        xslUser.setPassword(Md5Utils.digestMds(xslUserRegister.getPassword()));
        xslUser.setName(xslUserRegister.getName());
        xslUser.setSex(xslUserRegister.getSex());
        xslUser.setUpdatedate(new Date());
        xslUser.setCreatedate(new Date());
        try {
            xslUserMapper.insertSelective(xslUser);
            XslUserExample xslUserExample = new XslUserExample();
            XslUserExample.Criteria criteria = xslUserExample.createCriteria();
            criteria.andPhoneEqualTo(xslUserRegister.getPhone());
            List<XslUser> list = xslUserMapper.selectByExample(xslUserExample);
            return XslResult.ok(list.get(0).getId());
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }

    }

    /**
     * 用户学校信息
     *
     * @param xslUserRegister
     * @return
     */
    private XslResult createUserSchool(XslUserRegister xslUserRegister) {
        XslSchoolinfo xslSchoolinfo = initXslSchoolinfo(xslUserRegister);
        try {
			int i = xslSchoollinfoMapper.insertSelective(xslSchoolinfo);

			if(i < 1){
				throw new RuntimeException("插入学校信息异常");
			}

			return XslResult.ok(xslSchoolinfo.getSchoolId());
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    private XslSchoolinfo initXslSchoolinfo(XslUserRegister xslUserRegister) {
        XslSchoolinfo xslSchoolinfo = new XslSchoolinfo();
        xslSchoolinfo.setSchoolId(UUID.randomUUID().toString());
        xslSchoolinfo.setUserId(xslUserRegister.getUserId());
        xslSchoolinfo.setDegree((byte) 2);
        xslSchoolinfo.setSchoolhours((byte) 4);
        xslSchoolinfo.setSno(xslUserRegister.getSchoolNumber());
        xslSchoolinfo.setSchool(xslUserRegister.getSchoolinfo());
        xslSchoolinfo.setCollege(xslUserRegister.getCollege());
        xslSchoolinfo.setMajor(xslUserRegister.getMajor());
        xslSchoolinfo.setStartdate(new Date());
        return xslSchoolinfo;
    }

    /**
     * 登录
     *
     * @param phone
     * @param password
     * @return
     */
    @Override
    public XslResult  userLogin(String phone, String password, String token) {
        //1.查询
        XslUserExample example = new XslUserExample();
        XslUserExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone);
        List<XslUser> list = xslUserMapper.selectByExample(example);

        //2.判断用户是否存在
        if(list == null || list.size() < 1){
            return XslResult.build(403, "用户名或密码错误");
        }
        XslUser user = list.get(0);

        //3.校验密码
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            logger.info("password error");
            return XslResult.build(400, "用户名或密码错误");
        }

        //4.查询图片信息
        int id = user.getId();
        XslFileExample xslFileExample = new XslFileExample();
        XslFileExample.Criteria criteria1 = xslFileExample.createCriteria();
        criteria1.andUseridEqualTo(id);
        List<XslFile> xslFileList = xslFileMapper.selectByExample(xslFileExample);
        String imgUrl = "http://47.93.200.190/images/default.png";
        if (xslFileList != null && xslFileList.size() > 0) {
            imgUrl = xslFileList.get(0).getUrl();
        }
        user.setUrl(imgUrl);

        //5.判断用户异常状态
        Byte state = user.getState();
        if(state == -2){
            logger.info("login check status is {}", user.getState());
            return XslResult.build(403, "审核未通过");
        }

        if(state == -1){
            logger.info("login check status is {}", user.getState());
            return XslResult.build(403, "账户被冻结");
        }

        if(state == -3){
            logger.info("login check status is {}", user.getState());
            return XslResult.build(403, "账户已被删除");
        }

        jedisClient.set(REDIS_USER_SESSION_KEY + ":" +user.getPhone(), token);
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + user.getPhone(), Login_SESSION_EXPIRE);
        logger.info("login return message is {}", JsonUtils.objectToJson(user));
        return XslResult.ok(JsonUtils.objectToJson(user));

    }

    /**
     * 检查Token被更换
     * @param token
     * @return
     */
    @Override
    public XslResult getUserByToken(String token, String phone) {
        String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + phone);
        //判断是否为空
        if (!token.equals(json)) {
            return XslResult.build(400, "登陆时间已经过期。请重新登录");
        } else {
            return XslResult.ok(jedisClient.get(REDIS_USER_SESSION_KEY + ":" + phone));
        }
    }

    /**
     * 忘记密码
     *
     * @param phone
     * @param password
     * @return
     */
    @Override
    public XslResult Password(String phone, String password) {
        XslUserExample example = new XslUserExample();
        XslUserExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone);
        List<XslUser> list = xslUserMapper.selectByExample(example);
        if (list.size() == 0 && list.isEmpty()) {
            return XslResult.build(400, "手机号没有注册，请注册!");
        }
        XslUser xslUser = list.get(0);
        xslUser.setPassword(password);
        xslUserMapper.updateByExample(xslUser, example);
        return XslResult.build(200, "修改成功！");
    }

	private void initUserInfo(XslUserRegister xslUserRegister, XslUser xslUser, XslHunter xslHunter, XslMaster xslMaster) {
		xslUser.setHunterid(xslHunter.getHunterId());
		xslUser.setMasterid(xslMaster.getMasterId());
		xslUser.setPhone(xslUserRegister.getPhone());
		xslUser.setState(UserStateEnum.NA.getCode());
		xslUser.setPassword(Md5Utils.digestMds(xslUserRegister.getPassword()));
		xslUser.setCreatedate(new Date());
		xslUser.setUpdatedate(new Date());
		try {
			int result = xslUserMapper.insertSelective(xslUser);

			if (result < 1){
				throw new RuntimeException("用户信息插入失败");
			}

		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException("服务器异常");
		}
	}

	private XslMaster initXslMaster(XslUser xslUser) {
		//初始化雇主信息
		XslMaster xslMaster = new XslMaster();
		xslMaster.setUserid(xslUser.getUserId());
		xslMaster.setMasterId(UUID.randomUUID().toString());
		xslMaster.setLevel((short) 1);
		xslMaster.setDescr("新人雇主");
		try {
			int result = xslMasterMapper.insertSelective(xslMaster);

			if (result < 1){
				throw new RuntimeException("雇主信息插入失败");
			}

		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException("服务器异常");
		}

		return xslMaster;
	}

	private XslHunter initXslHunter(XslUser xslUser) {
		//初始化猎人信息
		XslHunter xslHunter = new XslHunter();
		xslHunter.setUserid(xslUser.getUserId());
		xslHunter.setHunterId(UUID.randomUUID().toString());
		xslHunter.setLevel((short) 1);
		xslHunter.setDescr("新手猎人");
		try {
			int result = xslHunterMapper.insertSelective(xslHunter);

			if (result < 1){
				throw new RuntimeException("猎人信息插入失败");
			}

		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException("服务器异常");
		}

		return xslHunter;
	}
}
