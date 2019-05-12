package service;

import pojo.XslTask;
import vo.CreateOrderReqVo;
import vo.TaskEsInfo;
import vo.TaskInfo;
import vo.UpdateTaskVo;

public interface TaskMqService {

	void updateEsTask(UpdateTaskVo updateTaskVo);

	void addEsTask(TaskEsInfo taskEsInfo);

	void createOrder(CreateOrderReqVo createOrderReqVo);

	void updateNetwork(String msg);


}
