package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.TagService;
import util.XslResult;
import vo.XslTagReqVo;

@Controller
@RequestMapping("/xsl/tag")
public class TagController {
	@Autowired
	private TagService tagService;

	/**
	 * 创建标签
	 *
	 * @return
	 */
	@RequestMapping("/createTag")
	@ResponseBody
	public XslResult createTag(XslTagReqVo xslTagReqVo) {
		XslResult xslResult = tagService.createTags(xslTagReqVo);
		return xslResult;
	}

	/**
	 * 获取标签
	 *
	 * @return
	 */
	@RequestMapping("/queryTag")
	@ResponseBody
	public XslResult queryTag(@RequestBody XslTagReqVo xslTagReqVo) {
		XslResult xslResult = tagService.queryTag(xslTagReqVo);
		return xslResult;
	}

}
