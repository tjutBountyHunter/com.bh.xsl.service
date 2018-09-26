package service.impl;

import classify.LocalMacAddress;
import classify.SchoolClassied;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import mapper.*;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;
import pojo.*;
import service.*;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static service.Md5Utils.digestMds;

@Service
public class UserviceImpl implements UserService {
    @Autowired
    private HunMaster hunMaster;
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

    //注册
    @Override
    public XslResult createUser(String user, String schoolUser, MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse response) throws Exception {
        XslUser xslUser = (XslUser) JsonUtils.jsonToPojo(user, XslUser.class);
        XslSchoolinfo xslSchool = JsonUtils.jsonToPojo(schoolUser, XslSchoolinfo.class);
        XslFile xslFile = new XslFile();
        String s = null;
        UserService userService = new UserviceImpl();
        if (!userService.checkData(xslUser.getPhone(), "u_account").getData().equals("true")) {
            return XslResult.build(400, "手机号码格式错误");
        } else if (!userService.checkData(xslUser.getPassword(), "u_password").getData().equals("true")) {
            return XslResult.build(400, "密码格式错误");
        } else {
            hunMaster.insertPeople();
            xslSchool.setSchoolhours((byte) 4);

            xslSchool.setStartdate(new Date());
            xslSchool.setDegree((byte) 4);
            xslSchoollinfoMapper.insert(xslSchool);

            XslSchoolinfoExample xslSchoolinfoExample = new XslSchoolinfoExample();
            XslSchoolinfoExample.Criteria criteria = xslSchoolinfoExample.createCriteria();
            criteria.andSnoEqualTo(xslSchool.getSno());
            List<XslSchoolinfo> list = xslSchoollinfoMapper.selectByExample(xslSchoolinfoExample);

            xslUser.setCreatedate(new Date());
            xslUser.setSchoolinfo(xslSchool.getId());
            xslUser.setUpdatedate(new Date());
            xslUser.setSchoolinfo(list.get(0).getId());
            xslUser.setHunterid(list.get(0).getId());
            xslUser.setMasterid(list.get(0).getId());
            XslUserExample xslUserExample = new XslUserExample();
            if (!uploadFile.isEmpty() && uploadFile.getSize() > 0) {
                ImageSave imageSave = new ImageSaveImpl();
                //存储照片
                Map resultMap = imageSave.uploadPicture(uploadFile);
                s = (String) resultMap.get("url");
                //为保证兼容性，需要把result转换为json格式的字符串
                String json = JsonUtils.objectToJson(resultMap);
                if (resultMap.get("error").equals(1)) {
                    return XslResult.build(400, "照片上传失败");
                } else {
                    XslUserExample.Criteria criteria1 = xslUserExample.createCriteria();
                    criteria1.andPhoneEqualTo(xslUser.getPhone());
                    List<XslUser> list1 = xslUserMapper.selectByExample(xslUserExample);
                    xslFile.setId(list.get(0).getId());
                    xslFile.setUrl(s);
                    xslFile.setCreatedate(new Date());
                    xslFile.setUpdatedate((new Date()));
                    xslFileMapper.insert(xslFile);
                    return XslResult.ok("注册成功");
                }
            } else {
                return XslResult.build(400, "没有图片上传");
            }
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
    public XslResult userLogin(String phone, String password) {
        LocalMacAddress localMacAddress = new LocalMacAddress();
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
            //生成token
            XslTokenExample example1 = new XslTokenExample();
            XslTokenExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andPhoneEqualTo(phone);
            List<XslToken> list1 = xslTokenMapper.selectByExample(example1);
            String token = "";
            if (list1 != null && list1.size() != 0) {
//                token = localMacAddress.getLocalMacAddress() + user.getName();
                token = "SDSCSDDC15655615D6CD";
                XslToken xslToken = new XslToken();
                xslToken.setToken(token);
                xslToken.setCreatedate(new Date());
                xslTokenMapper.updateByExampleSelective(xslToken, example1);
            } else {
//                token = localMacAddress.getLocalMacAddress() + user.getName();
                token = "SDSCSDDC15655615D6CD";
                XslToken xslToken = new XslToken();
                xslToken.setToken(token);
                xslToken.setCreatedate(new Date());
                xslToken.setState((byte) 1);
                xslToken.setPhone(phone);
                xslTokenMapper.insert(xslToken);
            }
            return XslResult.ok(token);
        } else {
            return XslResult.build(400, "审核未通过");
        }

    }

    /**
     * 检查Token被更换
     *
     * @param token
     * @return
     */
    @Override
    public XslResult getUserByToken(String token) {
        XslTokenExample xslTokenExample = new XslTokenExample();
        XslTokenExample.Criteria criteria = xslTokenExample.createCriteria();
        criteria.andTokenEqualTo(token);
        List<XslToken> list = xslTokenMapper.selectByExample(xslTokenExample);
        // 判断是否存在Token
        //判断是否为空
        if (list.size() == 0 && list.isEmpty()) {
            return XslResult.build(400, "异地登录，如若非本人操作，请修改密码");
        } else {
            return XslResult.ok("ok");
        }
    }

    /**
     * 账号密码核对
     *
     * @param content
     * @param type
     * @return
     */
    @Override
    public XslResult checkData(String content, String type) {
        //用户校检
        if ("u_account".equals(type)) {
            boolean b = content.matches("^[1][3578]\\d{9}");
            if (!b) {
                b = content.matches("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$ ");
                return XslResult.ok(b);
            }
            return XslResult.ok(b);
        } else {
            boolean a = content.matches("^[a-z0-9A-Z]+[^ ]{6,16}$");
            return XslResult.ok(a);
        }
    }

    /**
     * 短信验证码发送
     *
     * @param phone
     * @return
     */
    @Override
    public SendSmsResponse excute(String phone) {
        XslCodeExample xslCodeExample = new XslCodeExample();
        XslCodeExample.Criteria criteria = xslCodeExample.createCriteria();
        criteria.andPhoneEqualTo(phone);
        List<XslCode> list = xslCodeMapper.selectByExample(xslCodeExample);

        String num = new RandomNum().getRandom();
        SendSmsResponse response = Message.sendIdentifyingCode(phone, num);
        if (list.isEmpty() && list.size() == 0) {
            XslCode xslCode = new XslCode();
            xslCode.setCode(num);
            xslCode.setPhone(phone);
            Date date = new Date();
            xslCode.setCreatedate(date);
            xslCode.setUpdatedate(date);
            if (response.getCode().equals("OK")) {
                xslCodeMapper.insertSelective(xslCode);
            }
        } else {
            XslCode xslCode = list.get(0);
            xslCode.setCode(num);
            if (response.getCode().equals("OK")) {
                xslCodeMapper.updateByExampleSelective(xslCode, xslCodeExample);
            }
        }
        return response;
    }

    /**
     * 短信验证码验证
     *
     * @param phone
     * @return
     */
    @Override
    public String checkcode(String phone) {
        XslCodeExample xslCodeExample = new XslCodeExample();
        XslCodeExample.Criteria criteria = xslCodeExample.createCriteria();
        criteria.andPhoneEqualTo(phone);
        List<XslCode> list = xslCodeMapper.selectByExample(xslCodeExample);
        XslCode xslCode = list.get(0);
        String num = xslCode.getCode();
        return num;
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
    public String collegMessage(String school) {
        try {
            String schoolJson = new String(school.getBytes("iso-8859-1"), "utf-8");
            int schoolid = xslSchoolMessageMapper.selectBySchoolName(schoolJson);
            List<String> list = xslCollegeMessageMapper.selectBySchoolId(schoolid);
            return JsonUtils.objectToJson(list);
        } catch (Exception e) {
            e.printStackTrace();
            return "服务异常";
        }
    }

    /**
     * 专业种类
     *
     * @param college
     * @return
     */
    @Override
    public String majorMessage(String college) {
        try {
            String collegeJson = new String(college.getBytes("iso-8859-1"), "utf-8");
            int collegeid = xslCollegeMessageMapper.selectBycollegeName(collegeJson);
            List<String> list = xslMajorMessageMapper.selectByCollegeId(collegeid);
            return JsonUtils.objectToJson(list);
        } catch (Exception e) {
            e.printStackTrace();
            return "服务异常";
        }

    }
}
