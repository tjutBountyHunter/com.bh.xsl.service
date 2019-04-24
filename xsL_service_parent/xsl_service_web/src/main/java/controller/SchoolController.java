package controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.SchoolService;




import util.XslResult;
import vo.SchoolReqVo;
import vo.SchoolResVo;

@Controller
@RequestMapping("/xsl/school")
public class SchoolController {
	@Autowired
	private SchoolService schoolService;

	/**
	 * 学校类别
	 *
	 * @return
	 */
	@RequestMapping("/schoolClasses")
	@ResponseBody
	public XslResult schoolMessage() {
		XslResult resVo = schoolService.schoolMessage();
		return resVo;
	}

	/**
	 * 学院类别
	 *
	 * @param schoolReqVo
	 * @return
	 */
	@RequestMapping("/collegeClasses")
	@ResponseBody
	public XslResult collegeMessage(SchoolReqVo schoolReqVo) {
		XslResult xslResult = schoolService.collegMessage(schoolReqVo);
		return xslResult;
	}

	/**
	 * 专业类别
	 *
	 * @param schoolReqVo
	 * @return
	 */
	@RequestMapping("/majorClasses")
	@ResponseBody
	public XslResult majorMessage(SchoolReqVo schoolReqVo) {
		XslResult xslResult;
		try {
			xslResult = schoolService.majorMessage(schoolReqVo);
			return xslResult;
		} catch (Exception e) {
			return XslResult.build(500, "服务器异常");
		}
	}

	/**
	 * 学院类别
	 *
	 * @param schoolReqVo
	 * @return
	 */
	@RequestMapping("/delCache")
	@ResponseBody
	public XslResult delCache(SchoolReqVo schoolReqVo) {
		return schoolService.delCache(schoolReqVo);
	}
}
