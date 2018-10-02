package controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.sun.org.glassfish.external.probe.provider.annotations.ProbeParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.JsonUtils;
import service.UserService;
import service.XslResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

/**
 * 登录注册
 *
 * @author 高山潍
 */
@Controller

@RequestMapping("/xsl/xsluser")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 注册
     *
     * @param user
     * @param schoolUser
     * @param uploadFile
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public XslResult register(@ProbeParam("user") String user, @ProbeParam("schoolUser") String schoolUser, @ProbeParam("uploadFile") MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse response) {
        XslResult xslResult = null;
        try {
            xslResult = userService.createUser(user, schoolUser, uploadFile, request, response);
            return xslResult;
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 学校类别
     *
     * @return
     */
    @RequestMapping("/schoolClasses")
    @ResponseBody
    public XslResult schoolMessage() {
        String s = userService.schoolMessage();
        return XslResult.ok(s);
    }

    /**
     * 学院类别
     *
     * @param schoolName
     * @return
     */
    @RequestMapping("/collegeClasses")
    @ResponseBody
    public XslResult collegeMessage(@ProbeParam("schoolName") String schoolName) {
        return XslResult.ok(userService.collegMessage(schoolName));
    }

    /**
     * 专业类别
     *
     * @param majorName
     * @return
     */
    @RequestMapping("/majorClasses")
    @ResponseBody
    public XslResult majorMessage(@ProbeParam("majorName") String majorName) {
        return XslResult.ok(userService.majorMessage(majorName));
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public XslResult userLogin(String username, String password, String token, HttpServletRequest request, HttpServletResponse response) {
        try {
            XslResult result = userService.userLogin(username, password, token, request, response);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器错误");
        }
    }

    /**
     * 通过token检查登录
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "/token/{token}", method = RequestMethod.GET)
    @ResponseBody
    public Object getUserByToken(@PathVariable String token) {
        XslResult result = null;
        try {
            result = userService.getUserByToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            result = XslResult.build(500, "服务器异常");
        }
        return result;
    }

    /**
     * 忘记密码
     *
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping(value = "/upDataPassword")
    @ResponseBody
    public XslResult updataPassword(@RequestParam(value = "phone", required = false) String phone, @RequestParam(value = "password", required = false) String password) {
        try {
            XslResult result = userService.Password(phone, password);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 发送短信验证码
     *
     * @param phone
     * @return
     */
    @RequestMapping(value = "/message", method = RequestMethod.GET)
    @ResponseBody
    public XslResult sendMessage(@RequestParam("phone") String phone) {
        String message;
        boolean bool = (boolean) userService.checkData(phone, "u_account").getData();
        if (bool != true) {
            message = "手机号码填写错误,请检查手机号码格式是否正确";
            return XslResult.ok(JsonUtils.objectToJson(message));
        } else {
            SendSmsResponse q = userService.excute(phone);
            if (q.getCode().equals("OK")) {
                message = "短信验证请求成功";
                return XslResult.ok(JsonUtils.objectToJson(message));
            } else {
                message = "短信验证未请求成功,请联系工作人员";
                return XslResult.ok(JsonUtils.objectToJson(message));
            }
        }
    }

    /**
     * 核对短信验证码
     *
     * @param phone
     * @param code
     * @return
     */
    @RequestMapping(value = "/checkmessage", method = RequestMethod.GET)
    @ResponseBody
    public XslResult checkMessage(@RequestParam("phone") String phone, @RequestParam("code") String code) {
        String num = userService.checkcode(phone);
        if (!code.equals(num)) {
            return XslResult.ok("验证码错误");
        } else {
            return XslResult.ok("验证码正确");
        }
    }
}