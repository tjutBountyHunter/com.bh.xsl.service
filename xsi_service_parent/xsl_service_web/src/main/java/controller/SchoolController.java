package controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.SchoolService;




import util.XslResult;

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
		String s = schoolService.schoolMessage();
		return XslResult.ok(s);
	}

	/**
	 * 学院类别
	 *
	 * @param schoolName
	 * @return
	 */
	@RequestMapping("/collegeClasses")
	@ResponseBody
	public XslResult collegeMessage(@Param("schoolName") String schoolName) {
		XslResult xslResult = schoolService.collegMessage(schoolName);
		return xslResult;
	}

	/**
	 * 专业类别
	 *
	 * @param collegeName
	 * @return
	 */
	@RequestMapping("/majorClasses")
	@ResponseBody
	public XslResult majorMessage(@Param("collegeName") String collegeName, @Param("schoolId") Integer schoolId) {
		XslResult xslResult = null;
		try {
			xslResult = schoolService.majorMessage(collegeName, schoolId);
			return xslResult;
		} catch (Exception e) {
			return XslResult.build(500, "服务器异常");
		}
	}
}
