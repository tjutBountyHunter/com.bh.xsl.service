package service.impl;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import pojo.XslTask;
import service.TaskMqService;
import util.GsonSingle;
import vo.TaskInfo;
import vo.UpdateTaskVo;

import javax.annotation.Resource;
import javax.jms.Destination;

@Controller
public class TaskMqServiceImpl implements TaskMqService {
	@Autowired
	private JmsTemplate jmsTemplate;

	@Resource
	private Destination updateTaskInfo;

	@Resource
	private Destination addTaskInfo;

	@Override
	public void updateEsTask(UpdateTaskVo updateTaskVo) {
		String s = GsonSingle.getGson().toJson(updateTaskVo);
		jmsTemplate.send(updateTaskInfo, (session -> session.createTextMessage(s)));
	}

	@Override
	public void addEsTask(TaskInfo taskInfoVo) {
		Gson gson = GsonSingle.getGson();
		jmsTemplate.send(addTaskInfo, (session)-> session.createTextMessage(gson.toJson(taskInfoVo)));
	}
}
