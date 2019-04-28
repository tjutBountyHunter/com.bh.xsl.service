package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.jpushService;
import vo.JPushVo;

@Controller
@RequestMapping("/xsl/test")
public class testController {
	@Autowired
	private jpushService jpushService;

	@RequestMapping("/sendJpush")
	@ResponseBody
	public int sendToAll() {
		JPushVo jPushVo = new JPushVo();
		jPushVo.setNotificationTitle("老子是测试");
		jPushVo.setMsgTitle("老子是测试");
		jPushVo.setMsgContent("老子是测试");
		jPushVo.setExtrasparam("");
		return jpushService.sendToAll(jPushVo);
	}

}
