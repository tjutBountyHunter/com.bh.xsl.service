package service;

import org.springframework.web.multipart.MultipartFile;
import util.XslResult;

public interface FileOperateService {

	/**
	 * 上传图片
	 *
	 * @param uploadFile
	 * @param phone
	 * @return
	 */
	XslResult fileUpload(MultipartFile uploadFile, String phone, String type);
}
