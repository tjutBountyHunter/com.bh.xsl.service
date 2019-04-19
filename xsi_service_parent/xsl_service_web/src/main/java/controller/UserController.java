package controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pojo.XslUserRegister;
import service.UserService;
import util.XslResult;


/**
 * 登录注册
 *
 * @author 高山潍
 * @after 何林鸿
 */
@Controller
@RequestMapping("/xsl/xsluser")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 注册
     *
     * @param xslUserRegister
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public XslResult register(XslUserRegister xslUserRegister) {
        XslResult xslResult = userService.createUser(xslUserRegister);
        return xslResult;
    }

    /**
     * 快速注册
     *
     * @param xslUserRegister
     * @return
     */
    @RequestMapping(value = "/quickRegister", method = RequestMethod.POST)
    @ResponseBody
    public XslResult quickRegister(XslUserRegister xslUserRegister) {
        XslResult xslResult = userService.quickCreateUser(xslUserRegister);
        return xslResult;
    }

    /**
     * 图片
     *
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
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public XslResult userLogin(String username, String password, String token) {
        try {
            XslResult result = userService.userLogin(username, password, token);
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

}