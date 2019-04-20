package service.impl;

import mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import service.SchoolService;
import util.JsonUtils;
import util.XslResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SchoolServiceImpl implements SchoolService {
	@Autowired
	private XslMajorMessageMapper xslMajorMessageMapper;
	@Autowired
	private XslCollegeMessageMapper xslCollegeMessageMapper;
	@Autowired
	private XslSchoolMessageMapper xslSchoolMessageMapper;

	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;
	@Value("${REDIS_USER_SESSION_CODE_KEY}")
	private String REDIS_USER_SESSION_CODE_KEY;
	@Value("${REDIS_USER_SESSION_PASSWORD}")
	private String REDIS_USER_SESSION_PASSWORD;
	@Value("${Login_SESSION_EXPIRE}")
	private Integer Login_SESSION_EXPIRE;
	@Value("${Login_SESSION_EXPIRE_CODE}")
	private Integer Login_SESSION_EXPIRE_CODE;
	@Value("${Login_SESSION_EXPIRE_PASSWORD}")
	private Integer Login_SESSION_EXPIRE_PASSWORD;

	private static final Logger logger = LoggerFactory.getLogger(SchoolServiceImpl.class);

	/**
	 * 学校种类
	 *
	 * @return
	 */
	@Override
	public String schoolMessage() {
		List<String> list = xslSchoolMessageMapper.selectByXslSchool();
		String json = JsonUtils.objectToJson(list);
		return json;
	}
	/**
	 * 学院种类
	 *
	 * @param school
	 * @return
	 */
	@Override
	public XslResult collegMessage(String school) {
		try {
			int schoolid = xslSchoolMessageMapper.selectBySchoolName(school);
			List<String> list = xslCollegeMessageMapper.selectBySchoolId(schoolid);
			Map<String, Object> map = new HashMap<>();
			map.put("collegeMessage", JsonUtils.objectToJson(list));
			map.put("shoolId", schoolid);
			return XslResult.ok(map);
		} catch (Exception e) {
			e.printStackTrace();
			return XslResult.build(500,"服务异常");
		}
	}

	/**
	 * 专业种类
	 *
	 * @param college
	 * @return
	 */
	@Override
	public XslResult majorMessage(String college,Integer schoolId) {
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("collegeName", college);
			map.put("schoolId", schoolId);
			System.out.println(map.get("collegeName"));
			int collegeid = xslCollegeMessageMapper.selectBycollegeName(map);
			List<String> list = xslMajorMessageMapper.selectByCollegeId(collegeid);
			return XslResult.ok(JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			return XslResult.build(500,"服务异常");
		}
	}


}
