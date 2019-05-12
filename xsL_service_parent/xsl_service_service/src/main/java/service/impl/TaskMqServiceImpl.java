package service.impl;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import pojo.XslTask;
import service.TaskMqService;
import util.GsonSingle;
import vo.CreateOrderReqVo;
import vo.TaskEsInfo;
import vo.TaskInfo;
import vo.UpdateTaskVo;

import javax.annotation.Resource;
import javax.jms.Destination;

@Service
public class TaskMqServiceImpl implements TaskMqService {
	@Autowired
	private JmsTemplate jmsTemplate;

	@Resource
	private Destination updateTaskInfo;

	@Resource
	private Destination addTaskInfo;

	@Resource
	private Destination createOrder;

	@Resource
	private Destination updateNetwork;



	@Override
	public void updateEsTask(UpdateTaskVo updateTaskVo) {
		String s = GsonSingle.getGson().toJson(updateTaskVo);
		jmsTemplate.send(updateTaskInfo, (session -> session.createTextMessage(s)));
	}

	@Override
	public void addEsTask(TaskEsInfo taskEsInfo) {
		Gson gson = GsonSingle.getGson();
		jmsTemplate.send(addTaskInfo, (session)-> session.createTextMessage(gson.toJson(taskEsInfo)));
	}

	@Override
	public void createOrder(CreateOrderReqVo createOrderReqVo) {
		String s= GsonSingle.getGson().toJson(createOrderReqVo);
		jmsTemplate.send(createOrder,(session -> session.createTextMessage(s)));

	}

	@Override
	public void updateNetwork(String msg) {
		jmsTemplate.send(updateNetwork,(session -> session.createTextMessage(msg)));
	}
}
