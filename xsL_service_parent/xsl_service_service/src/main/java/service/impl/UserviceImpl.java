package service.impl;

import dao.JedisClient;
import enums.UserStateEnum;
import example.*;
import mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import pojo.*;
import service.*;
import util.*;
import vo.UserReqVo;
import vo.UserResVo;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class UserviceImpl implements UserService {
    @Autowired
    private XslFileMapper xslFileMapper;
	@Autowired
	private XslHunterMapper xslHunterMapper;
	@Autowired
	private XslMasterMapper xslMasterMapper;
    @Autowired
    private XslUserMapper xslUserMapper;
    @Autowired
    private XslSchoolinfoMapper xslSchoollinfoMapper;
	@Autowired
	private XslUserFileMapper xslUserFileMapper;
    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;

    private static final Logger logger = LoggerFactory.getLogger(UserviceImpl.class);


	/**
	 * 快速注册
	 *
	 * @return
	 */
	@Override
	public XslResult quickCreateUser(XslUserRegister xslUserRegister){
		XslUserExample example = new XslUserExample();
		XslUserExample.Criteria criteria = example.createCriteria();
		criteria.andPhoneEqualTo(xslUserRegister.getPhone());
		List<XslUser> list = xslUserMapper.selectByExample(example);
		if(list != null && list.size() > 0){
			return XslResult.build(403,"该手机号已经注册过");
		}

		XslUser xslUser = new XslUser();
		xslUser.setUserid(UuidUtil.getUUID().substring(0, 12));
		//初始化猎人信息
		XslHunter xslHunter = initXslHunter(xslUser);
		//初始化雇主信息
		XslMaster xslMaster = initXslMaster(xslUser);
		//初始化用户信息
		initUserInfo(xslUserRegister, xslUser, xslHunter, xslMaster);

		UserResVo userResVo = new UserResVo();
		BeanUtils.copyProperties(xslUser, userResVo);
		userResVo.setUserid(xslUser.getUserid());
		userResVo.setMasterid(xslMaster.getMasterid());
		userResVo.setMasterlevel(xslMaster.getLevel());
		userResVo.setHunterid(xslHunter.getHunterid());
		userResVo.setHunterlevel(xslHunter.getLevel());
		userResVo.setTxUrl("http://47.93.200.190/images/default.png");

		return XslResult.ok(userResVo);

	}

    /**
     * 登录
     *
     * @param userReqVo
     * @return
     */
    @Override
    public XslResult userLogin(UserReqVo userReqVo) {
    	String phone = userReqVo.getPhone();
    	String password = userReqVo.getPassword();
    	String token = userReqVo.getToken();

    	if(StringUtils.isEmpty(phone)){
			return XslResult.build(403, "手机号码为空");
		}

		if(StringUtils.isEmpty(password)){
			return XslResult.build(403, "密码为空");
		}

		if(StringUtils.isEmpty(token)){
			return XslResult.build(403, "权限不足");
		}

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

		UserResVo resVo = new UserResVo();
		BeanUtils.copyProperties(user, resVo);
		resVo.setUserid(user.getUserid());

        //3.校验密码
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            logger.info("password error");
            return XslResult.build(400, "用户名或密码错误");
        }

		String userId = user.getUserid();

		//4.判断用户异常状态
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

        //5.查询图片信息
		String imgUrl = "http://47.93.200.190/images/default.png";
		XslUserFileExample xslUserFileExample = new XslUserFileExample();
		XslUserFileExample.Criteria criteria2 = xslUserFileExample.createCriteria();
		criteria2.andUseridEqualTo(userId);
		criteria2.andTypeEqualTo("TX");
		List<XslUserFile> xslUserFiles = xslUserFileMapper.selectByExample(xslUserFileExample);
		if(xslUserFiles != null && xslUserFiles.size() > 0){
			XslFileExample xslFileExample = new XslFileExample();
			XslFileExample.Criteria criteria1 = xslFileExample.createCriteria();
			criteria1.andFileidEqualTo(xslUserFiles.get(0).getFileid());
			List<XslFile> xslFileList = xslFileMapper.selectByExample(xslFileExample);
			if (xslFileList != null && xslFileList.size() > 0) {
				imgUrl = xslFileList.get(0).getUrl();
			}
		}

		resVo.setTxUrl(imgUrl);

		//6.查询雇主信息和猎人信息
		XslHunterExample xslHunterExample = new XslHunterExample();
		XslMasterExample xslMasterExample = new XslMasterExample();
		XslHunterExample.Criteria criteria1 = xslHunterExample.createCriteria();
		criteria1.andUseridEqualTo(userId);
		XslMasterExample.Criteria criteria3 = xslMasterExample.createCriteria();
		criteria3.andUseridEqualTo(userId);

		List<XslHunter> xslHunters = xslHunterMapper.selectByExample(xslHunterExample);
		List<XslMaster> xslMasters = xslMasterMapper.selectByExample(xslMasterExample);

		resVo.setHunterid(xslHunters.get(0).getHunterid());
		resVo.setHunterlevel(xslHunters.get(0).getLevel());
		resVo.setMasterid(xslMasters.get(0).getMasterid());
		resVo.setMasterlevel(xslMasters.get(0).getLevel());

		//7.获取学校信息
		if(!StringUtils.isEmpty(user.getSchoolinfo())){
			XslSchoolinfoExample xslSchoolinfoExample = new XslSchoolinfoExample();
			XslSchoolinfoExample.Criteria criteria4 = xslSchoolinfoExample.createCriteria();
			criteria4.andSchoolIdEqualTo(user.getSchoolinfo());
			List<XslSchoolinfo> xslSchoolinfos = xslSchoollinfoMapper.selectByExample(xslSchoolinfoExample);
			BeanUtils.copyProperties(xslSchoolinfos.get(0), user);
		}


        jedisClient.set(REDIS_USER_SESSION_KEY + ":" + user.getPhone(), token);

        logger.info("login return message is {}", JsonUtils.objectToJson(resVo));

        return XslResult.ok(JsonUtils.objectToJson(resVo));
    }



    /**
     * 检验用户登录状态
     * @param token
     * @return
     */
    @Override
    public XslResult getUserByToken(String token, String phone) {
        String result = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + phone);

        //判断是否为空
        if (!token.equals(result)) {
            return XslResult.build(400, "登陆时间已经过期。请重新登录");
        }

        return XslResult.ok(jedisClient.get(REDIS_USER_SESSION_KEY + ":" + phone));
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
        xslUser.setPassword(Md5Utils.digestMds(password));
        xslUserMapper.updateByExample(xslUser, example);
        return XslResult.build(200, "修改成功！");
    }

	private void initUserInfo(XslUserRegister xslUserRegister, XslUser xslUser, XslHunter xslHunter, XslMaster xslMaster) {
		xslUser.setHunterid(xslHunter.getHunterid());
		xslUser.setMasterid(xslMaster.getMasterid());
		xslUser.setPhone(xslUserRegister.getPhone());
		xslUser.setState(UserStateEnum.NA.getCode());
		xslUser.setPassword(Md5Utils.digestMds(xslUserRegister.getPassword()));
		xslUser.setSex("男");
		xslUser.setName(xslUserRegister.getPhone());

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
		xslMaster.setUserid(xslUser.getUserid());
		xslMaster.setMasterid(UuidUtil.getUUID().substring(0, 12));
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
		xslHunter.setUserid(xslUser.getUserid());
		xslHunter.setHunterid(UuidUtil.getUUID().substring(0, 12));
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
