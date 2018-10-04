package service.impl;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import dao.JedisClient;
import mapper.*;
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

@Service
public class UserviceImpl implements UserService {
    @Autowired
    private HunMaster hunMaster;
    @Autowired
    private ImageSave imageSave;
    @Autowired
    private XslCodeMapper xslCodeMapper;
    @Autowired
    private XslTokenMapper xslTokenMapper;
    @Autowired
    private XslTokenFindMapper xslTokenFindMapper;
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
    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;
    @Value("${REDIS_USER_SESSION_CODE_KEY}")
    private String REDIS_USER_SESSION_CODE_KEY;
    @Value("${Login_SESSION_EXPIRE}")
    private Integer Login_SESSION_EXPIRE;
    @Value("${Login_SESSION_EXPIRE_CODE}")
    private Integer Login_SESSION_EXPIRE_CODE;
    //注册
    @Override
    public XslResult createUser(String all) {
        XslUserRegister xslUserRegister = JsonUtils.jsonToPojo(all, XslUserRegister.class);
        XslResult schoolId = createUserSchool(xslUserRegister);
        XslResult xslResult = createUseruser(xslUserRegister, (Integer) schoolId.getData());
        Map<String, Integer> map = hunMaster.insertPeople((Integer) xslResult.getData());
        map.put("id", (Integer) xslResult.getData());
        xslUserUpdateMapper.updateXslUser(map);
        return xslResult;
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
            xslFile.setUrl((String) map.get("url"));
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
        xslUser.setPassword(xslUserRegister.getPassword());
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
        xslSchoolinfo.setSno(xslUserRegister.getSno());
        xslSchoolinfo.setSchool(xslUserRegister.getSchool());
        xslSchoolinfo.setCollege(xslUserRegister.getCollege());
        xslSchoolinfo.setMajor(xslUserRegister.getMajor());
        xslSchoolinfo.setStartdate(new Date());
        try {
            xslSchoollinfoMapper.insert(xslSchoolinfo);
            return XslResult.ok(xslSchoolinfo.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 下一步
     *
     * @param json
     * @param code
     * @return
     */
    @Override
    public XslResult nextStep(String json, String code) {
        XslResult xslResult = null;
        XslnextStep xslnextStep = JsonUtils.jsonToPojo(json, XslnextStep.class);
        xslResult = checkcode(xslnextStep.getPhone(), code);
        return xslResult;
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
        //没有此用户
        if (list.isEmpty() && list.size() == 0) {
            return XslResult.build(400, "用户名或密码错误");
        }
        XslUser user = list.get(0);
        if (user.getState() == 1 || user.getState() == 0) {
            //校验密码
            if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
                System.out.println(DigestUtils.md5DigestAsHex(password.getBytes()));
                return XslResult.build(400, "用户名或密码错误");
            }
            jedisClient.set(REDIS_USER_SESSION_KEY + ":" +user.getPhone(),token);
            //设置session过期时间
            jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + user.getPhone(), Login_SESSION_EXPIRE);
            return XslResult.ok(JsonUtils.objectToJson(user));
        } else {
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
        boolean b = content.matches("^[1][3578]\\d{9}");
        if (b) {
            return XslResult.ok();
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
        String message;
        boolean bool = (boolean) checkData(phone).getData();
        if (bool != true) {
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
     * 短信验证码验证
     *
     * @param phone
     * @return
     */
    @Override
    public XslResult checkcode(String phone, String code) {
        String num = null;
        num = jedisClient.get(REDIS_USER_SESSION_CODE_KEY + ":" + phone);
        String message = null;
        if (!code.equals(num)) {
            message = "验证码错误";
            return XslResult.build(400, message);
        } else {
            return XslResult.ok(phone);
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
            String schoolJson = new String(school.getBytes("iso-8859-1"), "utf-8");
            int schoolid = xslSchoolMessageMapper.selectBySchoolName(schoolJson);
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
            String collegeJson = new String(college.getBytes("iso-8859-1"), "utf-8");
            Map<String, Object> map = new HashMap<>();
            map.put("collegeName", collegeJson);
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
