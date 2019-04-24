package service.impl;

import mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pojo.XslCollege;
import pojo.XslMajor;
import pojo.XslSchool;
import service.SchoolService;
import util.JedisClientUtil;
import util.JsonUtils;
import util.XslResult;
import vo.SchoolReqVo;
import vo.SchoolResVo;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {
	@Autowired
	private XslMajorMessageMapper xslMajorMessageMapper;
	@Autowired
	private XslCollegeMessageMapper xslCollegeMessageMapper;
	@Autowired
	private XslSchoolMessageMapper xslSchoolMessageMapper;

	@Value("${REDIS_SCHOOL_LIST}")
	private String REDIS_SCHOOL_LIST;
	@Value("${REDIS_SCHOOL_LIST_VERSION}")
	private String REDIS_SCHOOL_LIST_VERSION;

	@Value("${REDIS_COLLEGE_LIST}")
	private String REDIS_COLLEGE_LIST;
	@Value("${REDIS_COLLEGE_LIST_VERSION}")
	private String REDIS_COLLEGE_LIST_VERSION;

	@Value("${REDIS_MAJOR_LIST}")
	private String REDIS_MAJOR_LIST;
	@Value("${REDIS_MAJOR_LIST_VERSION}")
	private String REDIS_MAJOR_LIST_VERSION;

	private static final Logger logger = LoggerFactory.getLogger(SchoolServiceImpl.class);

	/**
	 * 学校种类
	 *
	 * @return
	 */
	@Override
	public XslResult schoolMessage() {
		String version = "1";
		SchoolResVo schoolResVo = new SchoolResVo();
		String versionRedis = JedisClientUtil.get(REDIS_SCHOOL_LIST_VERSION);
		schoolResVo.setVersion(version);
		if(!StringUtils.isEmpty(versionRedis)){
			version = versionRedis;
			schoolResVo.setVersion(version);
		}

		String infoByCache = getInfoByCache(1, REDIS_SCHOOL_LIST, null, version);
		if(!StringUtils.isEmpty(infoByCache)){
			schoolResVo.setSchools(infoByCache);
			return XslResult.ok(schoolResVo);
		}

		List<XslSchool> xslSchools = xslSchoolMessageMapper.selectSchoolList();
		if(xslSchools == null || xslSchools.size() < 1){
			return XslResult.ok(schoolResVo);
		}

		String schoolList = JsonUtils.objectToJson(xslSchools);
		JedisClientUtil.set(REDIS_SCHOOL_LIST +"_"+ version, schoolList);
		JedisClientUtil.set(REDIS_SCHOOL_LIST_VERSION, version);
		logger.info("SchoolService.schoolMessage req:{}",schoolList);

		schoolResVo.setVersion(version);
		schoolResVo.setSchools(schoolList);


		return XslResult.ok(schoolResVo);
	}
	/**
	 * 学院种类
	 *
	 * @param schoolReqVo
	 * @return
	 */
	@Override
	public XslResult collegMessage(SchoolReqVo schoolReqVo) {
		Integer schoolId = schoolReqVo.getSchoolid();
		String version = "1";

		SchoolResVo schoolResVo = new SchoolResVo();
		String versionRedis = JedisClientUtil.get(REDIS_COLLEGE_LIST_VERSION);
		schoolResVo.setVersion(version);
		if(!StringUtils.isEmpty(versionRedis)){
			version = versionRedis;
			schoolResVo.setVersion(version);
		}

		String infoByCache = getInfoByCache(2, REDIS_COLLEGE_LIST, schoolReqVo, version);
		if(!StringUtils.isEmpty(infoByCache)){
			schoolResVo.setColleges(infoByCache);
			return XslResult.ok(schoolResVo);
		}

		try {
			List<XslCollege> xslColleges = xslCollegeMessageMapper.selectCollegeList(schoolId);

			if(xslColleges == null || xslColleges.size() < 1){
				return XslResult.ok(schoolResVo);
			}

			String collegeList = JsonUtils.objectToJson(xslColleges);
			schoolResVo.setVersion(version);
			JedisClientUtil.set(REDIS_COLLEGE_LIST +"_"+schoolId+"_"+ version, collegeList);
			JedisClientUtil.set(REDIS_COLLEGE_LIST_VERSION, version);
			logger.info("SchoolService.schoolMessage req:{}", collegeList);

			schoolResVo.setColleges(collegeList);

			return XslResult.ok(schoolResVo);
		} catch (Exception e) {
			e.printStackTrace();
			return XslResult.build(500,"服务异常");
		}
	}

	/**
	 * 专业种类
	 *
	 * @param schoolReqVo
	 * @return
	 */
	@Override
	public XslResult majorMessage(SchoolReqVo schoolReqVo) {
		Integer collegeId = schoolReqVo.getCollegeid();
		String version = "1";
		SchoolResVo schoolResVo = new SchoolResVo();

		String versionRedis = JedisClientUtil.get(REDIS_MAJOR_LIST_VERSION);
		schoolResVo.setVersion(version);

		if(!StringUtils.isEmpty(versionRedis)){
			version = versionRedis;
			schoolResVo.setVersion(version);
		}

		String infoByCache = getInfoByCache(3, REDIS_MAJOR_LIST, schoolReqVo, version);
		if(!StringUtils.isEmpty(infoByCache)){
			schoolResVo.setMajors(infoByCache);
			return XslResult.ok(schoolResVo);
		}

		try {
			List<XslMajor> xslMajors = xslMajorMessageMapper.selectMajorList(collegeId);

			if(xslMajors == null || xslMajors.size() < 1){
				return XslResult.ok(schoolResVo);
			}

			String majorList = JsonUtils.objectToJson(xslMajors);
			schoolResVo.setVersion(version);
			JedisClientUtil.set(REDIS_MAJOR_LIST +"_"+ collegeId+"_"+version, majorList);
			JedisClientUtil.set(REDIS_MAJOR_LIST_VERSION, version);
			logger.info("SchoolService.schoolMessage req:{}", majorList);

			schoolResVo.setColleges(majorList);

			return XslResult.ok(schoolResVo);
		} catch (Exception e) {
			e.printStackTrace();
			return XslResult.build(500,"服务异常");
		}
	}

	@Override
	public XslResult delCache(SchoolReqVo schoolReqVo){
		JedisClientUtil.delete("REDIS_SCHOOL_LIST_VERSION");
		JedisClientUtil.delete("REDIS_SCHOOL_LIST_1");
		return XslResult.ok();
	}

	private String getInfoByCache(int type, String key, SchoolReqVo schoolReqVo, String version){
		String result = "";
		if(type == 1){
			result = JedisClientUtil.get(key +"_"+ version);
		}

		if(type == 2){
			Integer schoolid = schoolReqVo.getSchoolid();
			result = JedisClientUtil.get(key +"_"+ schoolid+"_"+version);
		}

		if(type == 3){
			Integer collegeId = schoolReqVo.getCollegeid();
			result = JedisClientUtil.get(key +"_"+ collegeId+"_"+version);
		}
		return result;
	}


}
