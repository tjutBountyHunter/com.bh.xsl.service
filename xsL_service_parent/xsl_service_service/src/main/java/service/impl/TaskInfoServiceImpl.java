package service.impl;

import com.google.gson.Gson;
import example.XslTagExample;
import example.XslTaskTagExample;
import mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pojo.XslSchool;
import pojo.XslTag;
import service.*;
import util.GsonSingle;
import util.JedisClientUtil;
import util.ListUtil;
import util.XslResult;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.util.List;

@Service
public class TaskInfoServiceImpl implements TaskInfoService {
	@Autowired
	private XslTaskTagMapper xslTaskTagMapper;
	@Autowired
	private XslTagMapper xslTagMapper;

	@Value("${TASK_TAG_INFO}")
	private String TASK_TAG_INFO;


	@Autowired
	private JmsTemplate jmsTemplate;

	@Resource
	private Destination mqTest;


	@Override
	public List<XslTag> getTaskTags(String taskId) {
		Gson gson = GsonSingle.getGson();
		String schoolInfo = JedisClientUtil.get(TASK_TAG_INFO + ":" + taskId);

		if(!StringUtils.isEmpty(schoolInfo)){
			List list = gson.fromJson(schoolInfo, List.class);
			return list;
		}

		XslTaskTagExample xslTaskTagExample = new XslTaskTagExample();
		xslTaskTagExample.createCriteria().andTaskidEqualTo(taskId);
		List<String> tagIds = xslTaskTagMapper.selectTagIdByExample(xslTaskTagExample);
		XslTagExample xslTagExample = new XslTagExample();
		xslTagExample.createCriteria().andTagidIn(tagIds);
		List<XslTag> xslTags = xslTagMapper.selectByExample(xslTagExample);
		if(ListUtil.isNotEmpty(xslTags)){
			JedisClientUtil.setEx(TASK_TAG_INFO + ":" + taskId, gson.toJson(xslTags), 300);
		}

		return xslTags;
	}

	@Override
	public XslResult sendMq(String msg) {
		XslResult build = XslResult.build(500, "000");
		jmsTemplate.send(mqTest, (session)->session.createObjectMessage(build));
		return XslResult.ok();
	}
}
