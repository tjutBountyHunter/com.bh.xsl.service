package service.impl;

import mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pojo.XslFile;
import pojo.XslUser;
import pojo.XslUserExample;
import service.FileOperateService;
import service.ImageSave;
import util.XslResult;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class FileOperateServiceImpl implements FileOperateService {
	@Autowired
	private ImageSave imageSave;
	@Autowired
	private XslFileMapper xslFileMapper;
	@Autowired
	private XslUserMapper xslUserMapper;

	/**
	 * 上传图片
	 *
	 * @param uploadFile
	 * @param phone
	 * @return
	 */
	@Override
	public XslResult fileUpload(MultipartFile uploadFile, String phone) {
		//1.判断用户是否存在
		XslUserExample xslUserExample = new XslUserExample();
		XslUserExample.Criteria criteria = xslUserExample.createCriteria();
		criteria.andPhoneEqualTo(phone);
		List<XslUser> list = xslUserMapper.selectByExample(xslUserExample);
		if (list == null || list.size() < 1) {
			return XslResult.build(403, "用户不存在");
		}

		//2.获取用户信息
		XslUser xslUser = list.get(0);

		//3.初始化文件信息
		XslFile xslFile = new XslFile();
		xslFile.setDesc("学生证");
		xslFile.setUserid(xslUser.getId());
		xslFile.setUpdatedate(new Date());
		xslFile.setCreatedate(new Date());

		Map map = imageSave.uploadPicture(uploadFile);

		if ("1".equals(map.get("error"))) {
			return XslResult.build(500, "图片上传失败");
		}

		xslFile.setUrl((String) map.get("url"));

		try {
			int insert = xslFileMapper.insert(xslFile);
			if(insert < 1){
				return XslResult.build(500, "服务器异常");
			}
			return XslResult.ok(xslFile.getUrl());
		} catch (Exception e) {
			e.printStackTrace();
			return XslResult.build(500, "服务器异常");
		}

	}

}
