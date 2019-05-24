package service.impl;

import com.xsl.user.SupplementUserInfoResource;
import com.xsl.user.UserOperateResource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pojo.XslUserRegister;
import service.UserOperateService;
import util.JedisClientUtil;
import util.XslResult;
import vo.*;

import javax.annotation.Resource;

@Service
public class UserOperateServiceImpl implements UserOperateService {
	@Resource
	private UserOperateResource userOperateResource;
	@Resource
	private SupplementUserInfoResource supplementUserInfoResource;

	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;


	@Override
	public XslResult quickCreateUser(XslUserRegister xslUserRegister) {
		XslUserRegisterReqVo xslUserRegisterReqVo = new XslUserRegisterReqVo();
		BeanUtils.copyProperties(xslUserRegister, xslUserRegisterReqVo);
		xslUserRegisterReqVo.setSource("app_service");
		try {
			UserResVo userResVo = userOperateResource.quickCreateUser(xslUserRegisterReqVo);

			if(userResVo.getStatus() == 200){
				XslUserResVo xslUserResVo = new XslUserResVo();
				BeanUtils.copyProperties(userResVo, xslUserResVo);
				return XslResult.ok(xslUserResVo);
			}

			return XslResult.build(500, "服务器错误");
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public XslResult userLogin(XslUserReqVo xslUserReqVo) {
		UserReqVo userReqVo = new UserReqVo();
		BeanUtils.copyProperties(xslUserReqVo, userReqVo);
		userReqVo.setSource("app_service");
		try {
			UserResVo userResVo = userOperateResource.userLogin(userReqVo);

			if(userResVo.getStatus() == 200){
				XslUserResVo xslUserResVo = new XslUserResVo();
				BeanUtils.copyProperties(userResVo, xslUserResVo);
				return XslResult.ok(xslUserResVo);
			}

			return XslResult.build(500, "服务器错误");
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public XslResult userLogout(XslUserReqVo xslUserReqVo) {
		UserReqVo userReqVo = new UserReqVo();
		BeanUtils.copyProperties(xslUserReqVo, userReqVo);
		userReqVo.setSource("app_service");
		try {
			ResBaseVo resBaseVo = userOperateResource.userLogout(userReqVo);

			if(resBaseVo.getStatus() == 200){
				return XslResult.ok();
			}

			return XslResult.build(500, "服务器错误");
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public XslResult getUserByToken(String token, String phone) {
		String result = JedisClientUtil.get(REDIS_USER_SESSION_KEY + ":" + phone);

		//判断是否为空
		if (!token.equals(result)) {
			return XslResult.build(400, "登陆时间已经过期。请重新登录");
		}

		return XslResult.ok(JedisClientUtil.get(REDIS_USER_SESSION_KEY + ":" + phone));
	}

	@Override
	public XslResult userAcc(XslUserAccReqVo xslUserAccReqVo) {
		UserAccReqVo userAccReqVo = new UserAccReqVo();
		BeanUtils.copyProperties(xslUserAccReqVo, userAccReqVo);
		userAccReqVo.setSource("app_service");
		try {
			ResBaseVo resBaseVo = supplementUserInfoResource.userAcc(userAccReqVo);

			if(resBaseVo.getStatus() == 200){
				return XslResult.ok();
			}

			return XslResult.build(500, "服务器错误");
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

}
