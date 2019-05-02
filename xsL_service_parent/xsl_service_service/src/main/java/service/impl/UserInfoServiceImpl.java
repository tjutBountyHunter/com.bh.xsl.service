package service.impl;

import com.google.gson.Gson;
import example.*;
import mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pojo.*;
import service.UserInfoService;
import util.GsonSingle;
import util.JedisClientUtil;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private XslUserMapper xslUserMapper;
	@Autowired
	private XslHunterMapper xslHunterMapper;
	@Autowired
	private XslMasterMapper xslMasterMapper;
	@Autowired
	private XslSchoolinfoMapper xslSchoolinfoMapper;
	@Autowired
	private XslSchoolMapper xslSchoolMapper;


	@Value("${USER_INFO}")
	private String USER_INFO;
	@Value("${USER_HUNTER_INFO}")
	private String USER_HUNTER_INFO;
	@Value("${USER_MASTER_INFO}")
	private String USER_MASTER_INFO;
	@Value("${USER_SCHOOL_INFO_INFO}")
	private String USER_SCHOOL_INFO_INFO;
	@Value("${USER_SCHOOL_INFO}")
	private String USER_SCHOOL_INFO;

	@Override
	public XslUser getUserInfo(String useid){
		return getUserInfo(useid, "", "");
	}

	@Override
	public XslUser getUserInfoByHunterId(String hunterid){
		return getUserInfo("", hunterid, "");
	}

	@Override
	public XslUser getUserInfoMasterId(String masterid){
		return getUserInfo("", "", masterid);
	}

	@Override
	public XslSchoolinfo getSchoolInfo(String schoolid){
		Gson gson = GsonSingle.getGson();
		String schoolInfo = JedisClientUtil.get(USER_SCHOOL_INFO_INFO + ":" + schoolid);

		if(!StringUtils.isEmpty(schoolInfo)){
			return gson.fromJson(schoolInfo, XslSchoolinfo.class);
		}

		XslSchoolinfoExample xslSchoolinfoExample = new XslSchoolinfoExample();
		xslSchoolinfoExample.createCriteria().andSchoolIdEqualTo(schoolid);
		List<XslSchoolinfo> xslUsers = xslSchoolinfoMapper.selectByExample(xslSchoolinfoExample);

		if(xslUsers != null && xslUsers.size() > 0){
			String info =  gson.toJson(xslUsers.get(0));
			JedisClientUtil.setEx(USER_INFO + ":" + schoolid, info , 300);
			return xslUsers.get(0);
		}

		return new XslSchoolinfo();
	}

	@Override
	public XslHunter getHunterInfo(String hunterid) {
		Gson gson = GsonSingle.getGson();
		String hunterInfo = JedisClientUtil.get(USER_HUNTER_INFO + ":" + hunterid);

		if(!StringUtils.isEmpty(hunterInfo)){
			return gson.fromJson(hunterInfo, XslHunter.class);
		}

		XslHunterExample xslHunterExample = new XslHunterExample();
		xslHunterExample.createCriteria().andHunteridEqualTo(hunterid);
		List<XslHunter> xslHunters = xslHunterMapper.selectByExample(xslHunterExample);

		if(xslHunters != null && xslHunters.size() > 0){
			JedisClientUtil.setEx(USER_HUNTER_INFO + ":" + hunterid, gson.toJson(xslHunters.get(0)), 300);
			return xslHunters.get(0);
		}

		return new XslHunter();
	}

	@Override
	public XslMaster getMasterInfo(String masterid) {
		Gson gson = GsonSingle.getGson();
		String userInfo = JedisClientUtil.get(USER_MASTER_INFO + ":" + masterid);

		if(!StringUtils.isEmpty(userInfo)){
			return gson.fromJson(userInfo, XslMaster.class);
		}

		XslMasterExample xslMasterExample = new XslMasterExample();
		xslMasterExample.createCriteria().andMasteridEqualTo(masterid);
		List<XslMaster> xslMasters = xslMasterMapper.selectByExample(xslMasterExample);

		if(xslMasters != null && xslMasters.size() > 0){
			JedisClientUtil.setEx(USER_MASTER_INFO + ":" + masterid, gson.toJson(xslMasters.get(0)), 300);
			return xslMasters.get(0);
		}

		return new XslMaster();
	}

	@Override
	public XslSchool getSchoolByName(String SchoolName) {
		Gson gson = GsonSingle.getGson();
		String schoolInfo = JedisClientUtil.get(USER_SCHOOL_INFO + ":" + SchoolName);

		if(!StringUtils.isEmpty(schoolInfo)){
			return gson.fromJson(schoolInfo, XslSchool.class);
		}

		XslSchoolExample xslSchoolExample = new XslSchoolExample();
		xslSchoolExample.createCriteria().andSchoolnameEqualTo(SchoolName);
		List<XslSchool> xslSchools = xslSchoolMapper.selectByExample(xslSchoolExample);

		if(xslSchools != null && xslSchools.size() > 0){
			JedisClientUtil.setEx(USER_SCHOOL_INFO + ":" + SchoolName, gson.toJson(xslSchools.get(0)), 300);
			return xslSchools.get(0);
		}

		return new XslSchool();
	}


	private XslUser getUserInfo(String useid, String hunterid, String masterid){
		Gson gson = GsonSingle.getGson();
		String userInfo = JedisClientUtil.get(USER_INFO + ":" + useid);

		if(!StringUtils.isEmpty(userInfo)){
			return gson.fromJson(userInfo, XslUser.class);
		}

		XslUserExample xslUserExample = new XslUserExample();
		XslUserExample.Criteria criteria = xslUserExample.createCriteria();

		if(!StringUtils.isEmpty(useid)){
			criteria.andUseridEqualTo(useid);
		}

		if(!StringUtils.isEmpty(hunterid)){
			criteria.andHunteridEqualTo(hunterid);
		}

		if(!StringUtils.isEmpty(masterid)){
			criteria.andMasteridEqualTo(masterid);
		}

		List<XslUser> xslUsers = xslUserMapper.selectByExample(xslUserExample);

		if(xslUsers != null && xslUsers.size() > 0){
			String info =  gson.toJson(xslUsers.get(0));
			JedisClientUtil.setEx(USER_INFO + ":" + useid, info , 300);
			return xslUsers.get(0);
		}

		return new XslUser();
	}

}
