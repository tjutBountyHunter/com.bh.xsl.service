package service.impl;

import com.xsl.user.SupplementUserInfoResource;
import com.xsl.user.UserInfoResouce;
import com.xsl.user.vo.ResBaseVo;
import com.xsl.user.vo.UserHMResVo;
import com.xsl.user.vo.UserReqVo;
import enums.UserStateEnum;
import example.*;
import mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pojo.*;
import pojo.XslUserRegister;
import service.*;
import util.*;
import vo.*;

import javax.annotation.Resource;
import java.util.*;


@Service
public class UserviceImpl implements UserService {
	@Autowired
	private XslHunterMapper xslHunterMapper;
	@Autowired
	private XslMasterMapper xslMasterMapper;
    @Autowired
    private XslUserMapper xslUserMapper;
	@Autowired
	private XslUserFileMapper xslUserFileMapper;

	@Autowired
	private FileOperateService fileOperateService;

	@Resource
	private ThreadPoolTaskExecutor userExecutor;
	@Autowired
	private TaskMqService taskMqService;

	@Resource
	private TaskInfoService taskInfoService;

	@Resource
	private SupplementUserInfoResource supplementUserInfoResource;
	@Resource
	private UserInfoResouce userInfoResouce;


    @Autowired
	private UserInfoService userInfoService;

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;
	@Value("${USER_INFO}")
	private String USER_INFO;
	@Value("${USER_TX_URL}")
	private String USER_TX_URL;



    private static final Logger logger = LoggerFactory.getLogger(UserviceImpl.class);

	@Override
	public XslResult saveUserInfo(XslUserReqVo xslUserReqVo){
		UserReqVo userReqVo = new UserReqVo();
		BeanUtils.copyProperties(xslUserReqVo, userReqVo);

		ResBaseVo resBaseVo = supplementUserInfoResource.saveUserInfo(userReqVo);

		if(resBaseVo.getStatus() != 200){
			return XslResult.build(resBaseVo.getStatus(), resBaseVo.getMsg());
		}

		return XslResult.ok();
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
			throw new RuntimeException(e);
		}
	}


	@Override
	public XslResult upLoadUserTx(MultipartFile uploadFile, String userid){
		//1.获取用户信息
		XslUser userInfo = userInfoService.getUserInfo(userid);
		if (userInfo.getUserid() == null) {
			return XslResult.build(403, "用户不存在");
		}

		try {
			String userTx = userInfoService.getUserTx(userid);
			if(!StringUtils.isEmpty(userTx)){
				XslUserFileExample xslUserFileExample = new XslUserFileExample();
				xslUserFileExample.createCriteria().andUseridEqualTo(userid).andTypeEqualTo("TX");
				xslUserFileMapper.deleteByExample(xslUserFileExample);
				JedisClientUtil.delete(USER_TX_URL + ":" + userid);
			}

			XslResult xslResult = fileOperateService.fileUpload(uploadFile);
			XslFile xslFile = (XslFile)xslResult.getData();

			//2.获取用户信息
			//建立用户与文件关联
			XslUserFile xslUserFile = new XslUserFile();
			xslUserFile.setUserid(userInfo.getUserid());
			xslUserFile.setFileid(xslFile.getFileid());
			xslUserFile.setType("TX");

			int insert = xslUserFileMapper.insert(xslUserFile);
			if(insert < 1){
				return XslResult.build(500, "服务器异常");
			}

			//3.异步更新状态
			userExecutor.execute(() -> esUserTxurl(userid, xslFile.getUrl()));


			return XslResult.ok(xslFile.getUrl());
		} catch (Exception e) {
			e.printStackTrace();
			return XslResult.build(500, "服务器异常");
		}

	}

	private void esUserTxurl(String userid, String txUrl) {
		esUserInfo(userid, "", txUrl);
	}

	private void esUserInfo(String userid, String name, String txUrl) {
		XslUser userInfo = userInfoService.getUserInfo(userid);
		String masterId = userInfo.getMasterid();
		List<XslTask> tasks = taskInfoService.getTaskByMasterId(masterId);

		if(ListUtil.isNotEmpty(tasks)){
			for(XslTask xslTask : tasks){
				UpdateTaskVo updateTaskVo = new UpdateTaskVo();
				if(!StringUtils.isEmpty(name)){
					updateTaskVo.setName(name);
				}
				if(!StringUtils.isEmpty(txUrl)){
					updateTaskVo.setTxUrl(txUrl);
				}
				updateTaskVo.setTaskId(xslTask.getTaskid());
				taskMqService.updateEsTask(updateTaskVo);
			}
		}
	}

	private void initUserInfo(XslUserRegister xslUserRegister, XslUser xslUser, XslHunter xslHunter, XslMaster xslMaster) {
		xslUser.setHunterid(xslHunter.getHunterid());
		xslUser.setMasterid(xslMaster.getMasterid());
		xslUser.setPhone(xslUserRegister.getPhone());
		xslUser.setState(UserStateEnum.NA.getCode());
		xslUser.setPassword(Md5Utils.digestMds(xslUserRegister.getPassword()));
		xslUser.setSex("男");
		xslUser.setName("xsl_"+xslUserRegister.getPhone());

		try {
			int result = xslUserMapper.insertSelective(xslUser);

			if (result < 1){
				throw new RuntimeException("用户信息插入失败");
			}

		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException("服务器异常");
		}
	}

	private XslMaster initXslMaster(XslUser xslUser) {
		//初始化雇主信息
		XslMaster xslMaster = new XslMaster();
		xslMaster.setUserid(xslUser.getUserid());
		xslMaster.setMasterid(UuidUtil.getUUID().substring(0, 12));
		xslMaster.setLevel((short) 1);
		xslMaster.setDescr("新人雇主");
		try {
			int result = xslMasterMapper.insertSelective(xslMaster);

			if (result < 1){
				throw new RuntimeException("雇主信息插入失败");
			}

		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException("服务器异常");
		}

		return xslMaster;
	}

	private XslHunter initXslHunter(XslUser xslUser) {
		//初始化猎人信息
		XslHunter xslHunter = new XslHunter();
		xslHunter.setUserid(xslUser.getUserid());
		xslHunter.setHunterid(UuidUtil.getUUID().substring(0, 12));
		xslHunter.setLevel((short) 1);
		xslHunter.setDescr("新手猎人");
		try {
			int result = xslHunterMapper.insertSelective(xslHunter);

			if (result < 1){
				throw new RuntimeException("猎人信息插入失败");
			}

		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException("服务器异常");
		}

		return xslHunter;
	}

}
