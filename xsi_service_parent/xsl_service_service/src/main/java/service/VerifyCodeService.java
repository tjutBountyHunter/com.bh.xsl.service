package service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import org.apache.ibatis.annotations.Param;
import util.XslResult;

public interface VerifyCodeService {
	/**
	 * 传递验证码
	 *
	 * @param phone
	 * @return
	 */
	XslResult sendMessageCode(String phone);
	/**
	 * 检验手机验证码
	 * @param phone
	 * @return
	 */
	XslResult checkCode(String phone, String code);
}
