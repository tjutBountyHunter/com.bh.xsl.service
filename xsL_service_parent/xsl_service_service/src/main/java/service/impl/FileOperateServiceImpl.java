package service.impl;

import mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pojo.XslFile;
import pojo.XslUser;
import example.XslUserExample;
import pojo.XslUserFile;
import service.FileOperateService;
import service.ImageSave;
import util.XslResult;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class FileOperateServiceImpl implements FileOperateService {
	@Autowired
	private ImageSave imageSave;
	@Autowired
	private XslFileMapper xslFileMapper;
	@Autowired
	private XslUserMapper xslUserMapper;
	@Autowired
	private XslUserFileMapper xslUserFileMapper;

	/**
	 * 上传图片
	 *
	 * @param uploadFile
	 * @return
	 */
	@Override
	public XslResult fileUpload(MultipartFile uploadFile) {
		if(uploadFile == null){
			return XslResult.build(400, "参数错误");
		}

		//初始化文件信息
		XslFile xslFile = new XslFile();
		xslFile.setFileid(UUID.randomUUID().toString());

		try {
			Map map = imageSave.uploadPicture(uploadFile);
			if ("1".equals(map.get("error"))) {
				return XslResult.build(500, "图片上传失败");
			}
			xslFile.setUrl((String) map.get("url"));

			int insert = xslFileMapper.insertSelective(xslFile);
			if(insert < 1){
				return XslResult.build(500, "服务器异常");
			}

			return XslResult.ok(xslFile);

		} catch (Exception e) {
			e.printStackTrace();
			return XslResult.build(500, "服务器异常");
		}

	}

	private XslResult createUserFile(XslFile xslFile, String phone, String type){
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
		//建立用户与文件关联
		XslUserFile xslUserFile = new XslUserFile();
		xslUserFile.setUserid(xslUser.getUserid());
		xslUserFile.setFileid(xslFile.getFileid());
		xslUserFile.setType(type);
		xslUserFile.setCreatedate(new Date());
		xslUserFile.setUpdatedate(new Date());

		try {
			int insert = xslUserFileMapper.insert(xslUserFile);
			if(insert < 1){
				return XslResult.build(500, "服务器异常");
			}
			return XslResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return XslResult.build(500, "服务器异常");
		}

	}

}
