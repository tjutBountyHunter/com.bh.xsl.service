package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.VerifyCodeService;
import util.XslResult;

@Controller
@RequestMapping("/xsl/verify")
public class VerifyCodeController {
	@Autowired
	private VerifyCodeService verifyCodeService;

	/**
	 * 发送短信验证码
	 *
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	@ResponseBody
	public XslResult sendMessage(@RequestParam("phone") String phone) {
		XslResult xslResult = verifyCodeService.sendMessageCode(phone);
		return xslResult;
	}

	/**
	 * 短信验证码验证
	 *
	 * @param phone
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/checkmessage", method = RequestMethod.POST)
	@ResponseBody
	public XslResult checkMessage(@RequestParam("phone") String phone, @RequestParam("code") String code, @RequestParam("password") String password) {
		System.out.println(phone);
		XslResult xslResult = verifyCodeService.checkCode(phone, code, password);
		return xslResult;
	}


}
