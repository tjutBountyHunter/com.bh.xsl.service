package controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pojo.XslUserRegister;
import service.UserOperateService;
import util.XslResult;
import vo.XslUserAccReqVo;
import vo.XslUserReqVo;

@Controller
@RequestMapping("/xsl/user")
public class UserOperateController {
	@Autowired
	private UserOperateService userOperateService;


	/**
	 * 快速注册
	 *
	 * @param xslUserRegister
	 * @return
	 */
	@RequestMapping(value = "/quickRegister", method = RequestMethod.POST)
	@ResponseBody
	public XslResult quickRegister(XslUserRegister xslUserRegister) {
		XslResult xslResult = userOperateService.quickCreateUser(xslUserRegister);
		return xslResult;
	}

	/**
	 * 用户登录
	 *
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public XslResult userLogin(XslUserReqVo xslUserReqVo) {
		try {
			XslResult result = userOperateService.userLogin(xslUserReqVo);
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
	public XslResult userLogout(XslUserReqVo xslUserReqVo) {
		try {
			XslResult result = userOperateService.userLogout(xslUserReqVo);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return XslResult.build(500, "服务器错误");
		}
	}


	/**
	 * 用户认证
	 *
	 * @param xslUserAccReqVo
	 * @return
	 */
	@RequestMapping(value = "/userAcc")
	@ResponseBody
	public XslResult userAcc(@RequestBody XslUserAccReqVo xslUserAccReqVo) {
		try {
			return userOperateService.userAcc(xslUserAccReqVo);
		} catch (Exception e) {
			e.printStackTrace();
			return XslResult.build(500, "服务器异常");
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
		XslResult result;
		try {
			result = userOperateService.getUserByToken(token,phone);
		} catch (Exception e) {
			e.printStackTrace();
			result = XslResult.build(500, "服务器异常");
		}
		return result;
	}

}
