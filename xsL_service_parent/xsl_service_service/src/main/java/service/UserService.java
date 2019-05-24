package service;

import org.springframework.web.multipart.MultipartFile;
import util.XslResult;
import vo.XslUserReqVo;

/**
 * 用户注册
 */
public interface UserService {
	/**
	 * 保存用户信息
	 *
	 * @param xslUserReqVo
	 * @return
	 */
	XslResult saveUserInfo(XslUserReqVo xslUserReqVo);

	/**
	 * 获取雇主和猎人信息
	 * @return
	 */
	XslResult getHMinfo(XslUserReqVo xslUserReqVo);

	/**
	 * 上传用户头像
	 * @return
	 */
	XslResult upLoadUserTx(MultipartFile uploadFile, String userid);
}
