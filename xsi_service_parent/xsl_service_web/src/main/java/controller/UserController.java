package controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import org.apache.ibatis.annotations.Param;
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
     * @param all
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public XslResult register(@Param("all") String all) {
        XslResult xslResult = null;
        xslResult = userService.createUser(all);
        return xslResult;
    }

    /**
     * 图片
     * @param uploadFile
     * @param phone
     * @return
     */
    @RequestMapping(value = "/file", method = RequestMethod.POST)
    @ResponseBody
    public XslResult fileUp(@Param("uploadFile") MultipartFile uploadFile, @Param("phone") String phone) {
        XslResult xslResult = null;
        xslResult = userService.createFile(uploadFile, phone);
        return xslResult;
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
    public XslResult collegeMessage(@Param("schoolName") String schoolName) {
        XslResult xslResult = null;
        xslResult = userService.collegMessage(schoolName);
        return xslResult;
    }

    /**
     * 专业类别
     *
     * @param collegeName
     * @return
     */
    @RequestMapping("/majorClasses")
    @ResponseBody
    public XslResult majorMessage(@Param("collegeName") String collegeName, @Param("schoolId") Integer schoolId) {
        XslResult xslResult = null;
        try {
            xslResult = userService.majorMessage(collegeName, schoolId);
            return xslResult;
        } catch (Exception e) {
            return XslResult.build(500, "服务器异常");
        }
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
    @RequestMapping(value = "/token", method = RequestMethod.GET)
    @ResponseBody
    public Object getUserByToken(@Param("token") String token, @Param("phone") String phone) {
        XslResult result = null;
        try {
            result = userService.getUserByToken(token,phone);
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
        XslResult xslResult = userService.sendMessageCode(phone);
        return xslResult;
    }

    /**
     * 下一步
     *
     * @param phone
     * @param code
     * @return
     */
    @RequestMapping(value = "/checkmessage", method = RequestMethod.POST)
    @ResponseBody
    public XslResult checkMessage(@RequestParam("phone") String phone, @RequestParam("code") String code, @RequestParam("password") String password) {
        System.out.println(phone);
        XslResult xslResult = userService.checkcode(phone, code, password);
        return xslResult;
    }
}