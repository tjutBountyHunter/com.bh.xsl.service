package controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import service.FileOperateService;
import util.XslResult;

/**
 * 文件操作
 */
@Controller
@RequestMapping("/xsl/file")
public class FileController {
	@Autowired
	private FileOperateService fileOperateService;

	/**
	 * 图片
	 *
	 * @param uploadFile
	 * @return
	 */
	@RequestMapping(value = "/upload")
	@ResponseBody
	public XslResult fileUp(@Param("uploadFile") MultipartFile uploadFile) {
		XslResult xslResult = fileOperateService.fileUpload(uploadFile);
		return xslResult;
	}
}
