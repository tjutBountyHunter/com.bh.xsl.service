package service;

import org.springframework.web.multipart.MultipartFile;
import util.XslResult;

public interface FileOperateService {

	/**
	 * 上传图片
	 *
	 * @param uploadFile
	 * @return
	 */
	XslResult fileUpload(MultipartFile uploadFile);
}
