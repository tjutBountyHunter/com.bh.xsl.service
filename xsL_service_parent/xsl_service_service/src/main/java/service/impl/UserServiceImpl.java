package service.impl;

import com.google.gson.Gson;
import com.xsl.user.SupplementUserInfoResource;
import com.xsl.user.UserInfoResouce;
import com.xsl.user.vo.FileUploadReqVo;
import com.xsl.user.vo.ResBaseVo;
import com.xsl.user.vo.UserHMResVo;
import com.xsl.user.vo.UserReqVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.UserService;
import util.GsonSingle;
import util.XslResult;
import vo.XslUserHMResVo;
import vo.XslUserReqVo;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private SupplementUserInfoResource supplementUserInfoResource;
	@Resource
	private UserInfoResouce userInfoResouce;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public XslResult saveUserInfo(XslUserReqVo xslUserReqVo){
	    Gson gson = GsonSingle.getGson();
	    logger.info("saveUserInfo xslUserReqVo is {}",gson.toJson(xslUserReqVo));
		UserReqVo userReqVo = new UserReqVo();
		BeanUtils.copyProperties(xslUserReqVo, userReqVo);
		try {
			ResBaseVo resBaseVo = supplementUserInfoResource.saveUserInfo(userReqVo);

			if(resBaseVo.getStatus() != 200){
			    logger.error("保存用户信息失败 resBaseVo is {}",gson.toJson(resBaseVo));
				return XslResult.build(resBaseVo.getStatus(), resBaseVo.getMsg());
			}
			return XslResult.ok();

		}catch (Exception e){
			e.printStackTrace();
			return XslResult.build(500, "服务器异常");

		}
	}


	@Override
	public XslResult getHMinfo(XslUserReqVo xslUserReqVo){
        Gson gson =GsonSingle.getGson();
        logger.info("getHMinfo: xslUserReqVo is {}",gson.toJson(xslUserReqVo));
		UserReqVo userReqVo = new UserReqVo();
		BeanUtils.copyProperties(xslUserReqVo, userReqVo);

		try {
			UserHMResVo hMinfo = userInfoResouce.getHMinfo(userReqVo);
			if(hMinfo.getStatus() != 200){
			    logger.error("获取雇主猎人信息失败: UserHMResVo is {}",gson.toJson(hMinfo));
				return XslResult.build(hMinfo.getStatus(), hMinfo.getMsg());
			}

			XslUserHMResVo xslUserHMResVo = new XslUserHMResVo();
			BeanUtils.copyProperties(hMinfo, xslUserHMResVo);

			return XslResult.ok(xslUserHMResVo);

		}catch (Exception e){
			e.printStackTrace();
			return XslResult.build(500, "服务器异常");
		}
	}


	@Override
	public XslResult upLoadUserTx(MultipartFile uploadFile, String userid){
	    logger.info("upLoadUserTx: uploadFile:{},userid: {}",uploadFile,userid);
		//1.获取用户信息
		FileUploadReqVo fileUploadReqVo = new FileUploadReqVo();
		try {
			fileUploadReqVo.setName(uploadFile.getName());
			fileUploadReqVo.setOriginalFilename(uploadFile.getOriginalFilename());
			fileUploadReqVo.setBytes(uploadFile.getBytes());
			fileUploadReqVo.setContentType(uploadFile.getContentType());
			fileUploadReqVo.setInputStream(uploadFile.getBytes());
			fileUploadReqVo.setSize(uploadFile.getSize());
			fileUploadReqVo.setUserId(userid);
			fileUploadReqVo.setSource("APP_SERVICE");
			ResBaseVo resBaseVo = supplementUserInfoResource.upLoadUserTx(fileUploadReqVo);

			if(resBaseVo.getStatus() != 200){
			    logger.error("上传头像失败：resBaseVo is {}",GsonSingle.getGson().toJson(resBaseVo));
				return XslResult.build(resBaseVo.getStatus(), resBaseVo.getMsg());
			}

			String url = (String) resBaseVo.getExParam();
			return XslResult.ok(url);
		} catch (Exception e) {
			e.printStackTrace();
			return XslResult.build(500, "服务器异常");
		}
	}

}