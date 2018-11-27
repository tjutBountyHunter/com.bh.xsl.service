package service.impl;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import dao.JedisClient;
import mapper.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;
import pojo.*;
import service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static service.ImageFile.fileName;

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
    private XslUserMapper xslUserMapper;
    @Autowired
    private XslSchoolinfoMapper xslSchoollinfoMapper;
    @Autowired
    private XslUserUpdateMapper xslUserUpdateMapper;
    @Autowired
    private JedisClient jedisClient;

    org.slf4j.Logger logger = LoggerFactory.getLogger(UserviceImpl.class);

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
    //注册
    @Override
    public XslResult createUser(String all) {


        try {

            all = new String(all.getBytes("iso-8859-1"), "utf-8");
            XslUserRegister xslUserRegister = JsonUtils.jsonToPojo(all, XslUserRegister.class);
//            XslUserExample example = new XslUserExample();
//            XslUserExample.Criteria criteria = example.createCriteria();
//            criteria.andPhoneEqualTo(xslUserRegister.getPhone());
//            List<XslUser> list = xslUserMapper.selectByExample(example);
//            if(list.size()!=0&&!list.equals("")){
//                return XslResult.build(400,"该手机号已经注册过");
//            }
            System.out.println(xslUserRegister.getSex());
            XslResult schoolId = createUserSchool(xslUserRegister);
            XslResult xslResult = createUseruser(xslUserRegister, (Integer) schoolId.getData());
            Map<String, Integer> map = hunMaster.insertPeople((Integer) xslResult.getData());
            jedisClient.set(REDIS_USER_SESSION_KEY + ":" + xslUserRegister.getPhone(), xslUserRegister.getToken());
            //设置session过期时间
            jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + xslUserRegister.getPhone(), Login_SESSION_EXPIRE);
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
    @Override
    public XslResult createUseruser(XslUserRegister xslUserRegister, Integer schoolId) {
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
     * 学校表
     *
     * @param xslUserRegister
     * @return
     */
    @Override
    public XslResult createUserSchool(XslUserRegister xslUserRegister) {
        XslSchoolinfo xslSchoolinfo = new XslSchoolinfo();
        xslSchoolinfo.setDegree((byte) 2);
        xslSchoolinfo.setSchoolhours((byte) 4);
        xslSchoolinfo.setSno(xslUserRegister.getSchoolNumber());
        System.out.println(xslUserRegister.getSchoolNumber());
        xslSchoolinfo.setSchool(xslUserRegister.getSchoolinfo());
        System.out.println(xslUserRegister.getSchoolinfo());
        xslSchoolinfo.setCollege(xslUserRegister.getCollege());
        System.out.println(xslUserRegister.getCollege());
        xslSchoolinfo.setMajor(xslUserRegister.getMajor());
        xslSchoolinfo.setStartdate(new Date());
        try {
            xslSchoollinfoMapper.insertSelective(xslSchoolinfo);
            XslSchoolinfoExample xslSchoolinfoExample = new XslSchoolinfoExample();
            XslSchoolinfoExample.Criteria criteria = xslSchoolinfoExample.createCriteria();
            criteria.andSnoEqualTo(xslUserRegister.getSchoolNumber());
            List<XslSchoolinfo> list = xslSchoollinfoMapper.selectByExample(xslSchoolinfoExample);
            return XslResult.ok(list.get(0).getId());
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }
    /**
     * 登录
     *
     * @param phone
     * @param password
     * @return
     */
    @Override
    public XslResult userLogin(String phone, String password, String token,
                               HttpServletRequest request, HttpServletResponse response) {

        System.out.println(phone);
        XslUserExample example = new XslUserExample();
        XslUserExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone);
        List<XslUser> list = xslUserMapper.selectByExample(example);

        XslFileExample xslFileExample = new XslFileExample();
        XslFileExample.Criteria criteria1 = xslFileExample.createCriteria();
        int id = list.get(0).getId();
        System.out.println(id);
        criteria1.andUseridEqualTo(id);
        List<XslFile> xslFileList = xslFileMapper.selectByExample(xslFileExample);
        //没有此用户
        if (list.isEmpty() && list.size() == 0) {
            return XslResult.build(400, "用户名或密码错误");
        }
        String json = null;
        if (xslFileList.size() == 0 && xslFileList.isEmpty()) {
            json = "http://47.93.200.190/images/default.png";
        } else {
            json = xslFileList.get(0).getUrl();
        }
        XslUser user = list.get(0);
        user.setUrl(json);
        if (user.getState() == 1 || user.getState() == 0) {
            //校验密码
            if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
                System.out.println(DigestUtils.md5DigestAsHex(password.getBytes()));
                logger.info("login password is {}", !DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword()));
                return XslResult.build(400, "用户名或密码错误");
            }
            jedisClient.set(REDIS_USER_SESSION_KEY + ":" +user.getPhone(),token);
            //设置session过期时间
            jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + user.getPhone(), Login_SESSION_EXPIRE);
            logger.info("login return message is {}", JsonUtils.objectToJson(user));
            return XslResult.ok(JsonUtils.objectToJson(user));
        } else {
            logger.info("login check status is {}", user.getState());
            return XslResult.build(400, "审核未通过");
        }

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
     * 账号密码核对
     *
     * @param content
     * @return
     */
    @Override
    public XslResult checkData(String content) {
        //用户校检
        boolean b = content.matches("^[1][35678]\\d{9}");
        System.out.println(b);
        if (b) {
            return XslResult.ok(b);
        } else {
            return XslResult.build(400,"手机格式错误");
        }
    }

    /**
     * 短信验证码存入缓存
     * @param phone
     * @return
     */
    @Override
    public SendSmsResponse verifyCode(String phone) {
        String num = new RandomNum().getRandom();
        jedisClient.set(REDIS_USER_SESSION_CODE_KEY+ ":" +phone,num);
        SendSmsResponse response = Message.sendIdentifyingCode(phone, num);
        //设置session过期时间
        jedisClient.expire(REDIS_USER_SESSION_CODE_KEY + ":" + phone, Login_SESSION_EXPIRE_CODE);
        return response;
    }

    /**
     * 短信验证码
     *
     * @param phone
     * @return
     */
    @Override
    public XslResult sendMessageCode(String phone) {
        String message = null;
        int bool = checkData(phone).getStatus();
        if (bool != 200) {
            message = "手机号码填写错误,请检查手机号码格式是否正确";
            return XslResult.build(400, JsonUtils.objectToJson(message));
        } else {
            SendSmsResponse q = verifyCode(phone);
            if (q.getCode().equals("OK")) {
                message = "短信验证请求成功";
                return XslResult.ok(JsonUtils.objectToJson(message));
            } else {
                message = "短信验证未请求成功,请联系工作人员";
                return XslResult.build(500,JsonUtils.objectToJson(message));
            }
        }
    }

    /**
     * 下一步
     *
     * @param phone
     * @return
     */
    @Override
    public XslResult checkcode(String phone, String code, String password) {
        System.out.println(code);
        jedisClient.set(REDIS_USER_SESSION_PASSWORD + ":" + phone, password);
        jedisClient.expire(REDIS_USER_SESSION_PASSWORD + ":" + phone, Login_SESSION_EXPIRE_PASSWORD);
        String num = null;
        num = jedisClient.get(REDIS_USER_SESSION_CODE_KEY + ":" + phone);
        System.out.println(num);
        String message = null;
        if (num == null) {
            return XslResult.build(400, "您的验证码失效");
        } else {
            System.out.println(!code.equals(num));
            if (!code.equals(num)) {
                message = "验证码错误";
                System.out.println(message);
                return XslResult.build(400, message);
            } else {
                return XslResult.ok("验证成功");
            }
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
    /**
     * 学校种类
     *
     * @return
     */
    @Override
    public String schoolMessage() {
        List<String> list = xslSchoolMessageMapper.selectByXslSchool();
        String json = JsonUtils.objectToJson(list);
        return json;
    }
    /**
     * 学院种类
     *
     * @param school
     * @return
     */
    @Override
    public XslResult collegMessage(String school) {
        try {
            int schoolid = xslSchoolMessageMapper.selectBySchoolName(school);
            List<String> list = xslCollegeMessageMapper.selectBySchoolId(schoolid);
            Map<String, Object> map = new HashMap<>();
            map.put("collegeMessage", JsonUtils.objectToJson(list));
            map.put("shoolId", schoolid);
            return XslResult.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500,"服务异常");
        }
    }

    /**
     * 专业种类
     *
     * @param college
     * @return
     */
    @Override
    public XslResult majorMessage(String college,Integer schoolId) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("collegeName", college);
            map.put("schoolId", schoolId);
            System.out.println(map.get("collegeName"));
            int collegeid = xslCollegeMessageMapper.selectBycollegeName(map);
            List<String> list = xslMajorMessageMapper.selectByCollegeId(collegeid);
            return XslResult.ok(JsonUtils.objectToJson(list));
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500,"服务异常");
        }
    }
}
