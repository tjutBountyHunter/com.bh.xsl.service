package service;

import pojo.XslTask;
import vo.CreateOrderReqVo;
import vo.TaskInfo;
import vo.UpdateTaskVo;

public interface TaskMqService {

	void updateEsTask(UpdateTaskVo updateTaskVo);

	void addEsTask(TaskInfo taskInfoVo);

	void createOrder(CreateOrderReqVo createOrderReqVo);

	void updateNetwork(String msg);


}
