package service.impl;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import dao.JedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import service.VerifyCodeService;
import util.JsonUtils;
import util.Message;
import util.XslResult;

@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {
	@Autowired
	private JedisClient jedisClient;

	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;
	@Value("${REDIS_USER_SESSION_CODE_KEY}")
	private String REDIS_USER_SESSION_CODE_KEY;
	@Value("${REDIS_USER_SESSION_PASSWORD}")
	private String REDIS_USER_SESSION_PASSWORD;
	@Value("${Login_SESSION_EXPIRE}")
	private Integer Login_SESSION_EXPIRE;
	@Value("${Login_SESSION_EXPIRE_CODE}")
	private Integer Login_SESSION_EXPIRE_CODE;
	@Value("${Login_SESSION_EXPIRE_PASSWORD}")
	private Integer Login_SESSION_EXPIRE_PASSWORD;

	private static final Logger logger = LoggerFactory.getLogger(VerifyCodeServiceImpl.class);

	/**
	 * 短信验证码
	 *
	 * @param phone
	 * @return
	 */
	@Override
	public XslResult sendMessageCode(String phone) {
		String message;

		//1.手机号检测
		int bool = checkData(phone).getStatus();
		if (bool != 200) {
			message = "手机号码填写错误,请检查手机号码格式是否正确";
			return XslResult.build(400, JsonUtils.objectToJson(message));
		}

		//2.发送短信验证码
		SendSmsResponse q = sendVerifyCode(phone);
		if (!"OK".equals(q.getCode())) {
			message = "短信验证未请求成功,请联系工作人员";
			return XslResult.build(500,JsonUtils.objectToJson(message));
		}
		message = "短信验证请求成功";
		return XslResult.ok(JsonUtils.objectToJson(message));

	}

	/**
	 * 检验手机验证码
	 *
	 * @param phone
	 * @return
	 */
	@Override
	public XslResult checkCode(String phone, String code) {
		String num = jedisClient.get(REDIS_USER_SESSION_CODE_KEY + ":" + phone);

		String message = null;
		if (num == null) {
			return XslResult.build(400, "您的验证码失效");
		} else {
			System.out.println(!code.equals(num));
			if (!code.equals(num)) {
				message = "验证码错误";
				return XslResult.build(400, message);
			} else {
				return XslResult.ok("验证成功");
			}
		}
	}

	/**
	 * 账号密码核对
	 *
	 * @param content
	 * @return
	 */
	private XslResult checkData(String content) {
		//用户校检
		boolean b = content.matches("^[1][35678]\\d{9}");
		if (b) {
			return XslResult.ok();
		} else {
			return XslResult.build(400,"手机格式错误");
		}
	}

	/**
	 * 发送短信验证码
	 * @param phone
	 * @return
	 */
	public SendSmsResponse sendVerifyCode(String phone) {
		//1.获取一个四位数的验证码
		String code = new RandomNum().getRandom();
		//2.发送验证码
		SendSmsResponse response = Message.sendIdentifyingCode(phone, code);
		//2.1验证码发送成功添加缓存
		if ("OK".equals(response.getCode())){
			jedisClient.set(REDIS_USER_SESSION_CODE_KEY+ ":" +phone, code);
			//设置session过期时间
			jedisClient.expire(REDIS_USER_SESSION_CODE_KEY + ":" + phone, Login_SESSION_EXPIRE_CODE);
		}

		return response;
	}
}
