package service.impl;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import pojo.XslNetwork;
import pojo.XslTask;
import service.TaskMqService;
import util.GsonSingle;
import vo.CreateOrderReqVo;
import vo.RecTaskReqVo;
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

	@Resource
	private Destination createOrder;

	@Resource
	private  Destination updateNetwork;

	public void sendMq(Destination destination,Object object){
		String s = GsonSingle.getGson().toJson(object);
		jmsTemplate.send(destination, (session -> session.createTextMessage(s)));
	}


	@Override
	public void updateEsTask(UpdateTaskVo updateTaskVo) {
		sendMq(updateTaskInfo,updateTaskVo);
	}

	@Override
	public void addEsTask(TaskInfo taskInfoVo) {
		sendMq(addTaskInfo,taskInfoVo);

	}

	@Override
	public void createOrder(CreateOrderReqVo createOrderReqVo) {
		sendMq(createOrder,createOrderReqVo);
	}

	@Override
	public void updateNetWork(XslNetwork xslNetwork) {
		sendMq(updateNetwork,xslNetwork);

	}
}
