package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.jpushService;
import util.JedisClientUtil;
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

	@RequestMapping("/delCache")
	@ResponseBody
	public long delCache(String key) {
		long delete = JedisClientUtil.delete(key);
		return delete;
	}

	@RequestMapping("/getCache")
	@ResponseBody
	public String getCache(String key) {
		String data = JedisClientUtil.get(key);
		return data;
	}

}
