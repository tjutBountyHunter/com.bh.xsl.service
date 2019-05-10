package controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pojo.XslUserRegister;
import service.FileOperateService;
import service.UserService;
import util.XslResult;
import vo.UserAccReqVo;
import vo.UserReqVo;

/**
 * 登录注册
 *
 */
@Controller
@RequestMapping("/xsl/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FileOperateService fileOperateService;


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
     * @param userid
     * @return
     */
    @RequestMapping(value = "/userTx", method = RequestMethod.POST)
    @ResponseBody
    public XslResult fileUp(@Param("uploadFile") MultipartFile uploadFile, @Param("userid") String userid) {
        XslResult xslResult = userService.upLoadUserTx(uploadFile, userid);
        return xslResult;
    }

    /**
     * 用户登录
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public XslResult userLogin(UserReqVo userReqVo) {
        try {
            XslResult result = userService.userLogin(userReqVo);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器错误");
        }
    }



	/**
	 * 用户注销
	 *
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public XslResult userLogout(UserReqVo userReqVo) {
		try {
			XslResult result = userService.userLogout(userReqVo);
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
	 * 获取猎人雇主信息
	 *
	 * @param userReqVo
	 * @return
	 */
	@RequestMapping(value = "/gethminfo")
	@ResponseBody
	public XslResult gethminfo(UserReqVo userReqVo) {
		try {
			return userService.getHMinfo(userReqVo);
		} catch (Exception e) {
			e.printStackTrace();
			return XslResult.build(500, "服务器异常");
		}
	}

	/**
	 * 获取猎人雇主信息
	 *
	 * @param userReqVo
	 * @return
	 */
	@RequestMapping(value = "/saveUserInfo")
	@ResponseBody
	public XslResult saveUserInfo(UserReqVo userReqVo) {
		try {
			return userService.saveUserInfo(userReqVo);
		} catch (Exception e) {
			e.printStackTrace();
			return XslResult.build(500, "服务器异常");
		}
	}


	/**
	 * 用户认证
	 *
	 * @param userAccReqVo
	 * @return
	 */
	@RequestMapping(value = "/userAcc")
	@ResponseBody
	public XslResult userAcc(@RequestBody UserAccReqVo userAccReqVo) {
		try {
			return userService.userAcc(userAccReqVo);
		} catch (Exception e) {
			e.printStackTrace();
			return XslResult.build(500, "服务器异常");
		}
	}



}