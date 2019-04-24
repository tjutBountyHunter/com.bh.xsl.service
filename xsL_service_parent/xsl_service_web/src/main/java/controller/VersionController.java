package controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.VersionServie;
import util.XslResult;

@Controller
@RequestMapping("/xsl/version")
public class VersionController {
	@Autowired
	private VersionServie versionServie;

	/**
	 * 获取版本号
	 *
	 * @return
	 */
	@RequestMapping("/getVersion")
	@ResponseBody
	public XslResult schoolMessage(@Param("key") String key) {
		return versionServie.getVersion(key);
	}


}
