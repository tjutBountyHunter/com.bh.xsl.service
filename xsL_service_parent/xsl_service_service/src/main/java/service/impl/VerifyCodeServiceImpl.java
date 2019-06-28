package service.impl;

import com.xsl.user.VerifyCodeResource;
import com.xsl.user.vo.ResBaseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.VerifyCodeService;
import util.XslResult;

import javax.annotation.Resource;

@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {
	@Resource
	private VerifyCodeResource verifyCodeResource;

	private static final Logger logger = LoggerFactory.getLogger(VerifyCodeServiceImpl.class);

	/**
	 * 短信验证码
	 *
	 * @param phone
	 * @return
	 */
	@Override
	public XslResult sendMessageCode(String phone) {
		ResBaseVo resBaseVo = verifyCodeResource.sendMessageCode(phone);

		if (resBaseVo.getStatus() != 200) {
			return XslResult.build(resBaseVo.getStatus(), resBaseVo.getMsg());
		}

		return XslResult.ok();
	}

	/**
	 * 检验手机验证码
	 *
	 * @param phone
	 * @return
	 */
	@Override
	public XslResult checkCode(String phone, String code) {
		ResBaseVo resBaseVo = verifyCodeResource.checkCode(phone, code);

		if (resBaseVo.getStatus() != 200) {
			return XslResult.build(resBaseVo.getStatus(), resBaseVo.getMsg());
		}

		return XslResult.ok("验证成功");
	}

}
