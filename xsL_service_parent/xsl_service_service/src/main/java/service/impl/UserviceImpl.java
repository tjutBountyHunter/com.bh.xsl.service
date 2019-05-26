package service.impl;

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
import service.*;
import util.*;
import vo.*;

import javax.annotation.Resource;

@Service
public class UserviceImpl implements UserService {
	@Resource
	private SupplementUserInfoResource supplementUserInfoResource;
	@Resource
	private UserInfoResouce userInfoResouce;

    private static final Logger logger = LoggerFactory.getLogger(UserviceImpl.class);

	@Override
	public XslResult saveUserInfo(XslUserReqVo xslUserReqVo){
		UserReqVo userReqVo = new UserReqVo();
		BeanUtils.copyProperties(xslUserReqVo, userReqVo);
		try {
			ResBaseVo resBaseVo = supplementUserInfoResource.saveUserInfo(userReqVo);

			if(resBaseVo.getStatus() != 200){
				return XslResult.build(resBaseVo.getStatus(), resBaseVo.getMsg());
			}

			return XslResult.ok();

		}catch (Exception e){
			e.printStackTrace();
			return XslResult.build(500, "服务器异常");

		}
	}



	public XslResult getHMinfo(XslUserReqVo xslUserReqVo){
		UserReqVo userReqVo = new UserReqVo();
		BeanUtils.copyProperties(xslUserReqVo, userReqVo);

		try {
			UserHMResVo hMinfo = userInfoResouce.getHMinfo(userReqVo);
			if(hMinfo.getStatus() != 200){
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
