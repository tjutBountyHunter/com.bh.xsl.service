package service;

import pojo.XslNetwork;
import pojo.XslTask;
import util.XslResult;
import vo.CreateOrderReqVo;
import vo.RecTaskReqVo;
import vo.TaskInfo;
import vo.UpdateTaskVo;

public interface TaskMqService {

	void updateEsTask(UpdateTaskVo updateTaskVo);

	void addEsTask(TaskInfo taskInfoVo);

	void createOrder(CreateOrderReqVo createOrderReqVo);

	void updateNetWork(XslNetwork xslNetwork);



}
