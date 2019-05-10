package service;

import pojo.XslTask;
import vo.TaskInfo;
import vo.UpdateTaskVo;

public interface TaskMqService {

	void updateEsTask(UpdateTaskVo updateTaskVo);

	void addEsTask(TaskInfo taskInfoVo);

}
