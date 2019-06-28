package service;

import pojo.XslUserRegister;
import util.XslResult;
import vo.XslUserAccReqVo;
import vo.XslUserReqVo;

public interface UserOperateService {
	/**
	 * 快速注册
	 * @param xslUserRegister
	 * @return
	 * @throws Exception
	 */
	XslResult quickCreateUser(XslUserRegister xslUserRegister);

	/**
	 * 登录
	 *
	 * @param xslUserReqVo
	 * @return
	 */
	XslResult userLogin(XslUserReqVo xslUserReqVo);

	/**
	 * 注销
	 *
	 * @param xslUserReqVo
	 * @return
	 */
	XslResult userLogout(XslUserReqVo xslUserReqVo);

	/**
	 * 检查Token被更换
	 *
	 * @param token
	 * @return
	 */
	XslResult getUserByToken(String token, String phone);

	/**
	 * 用户认证
	 * @return
	 */
	XslResult userAcc(XslUserAccReqVo xslUserAccReqVo);
}
