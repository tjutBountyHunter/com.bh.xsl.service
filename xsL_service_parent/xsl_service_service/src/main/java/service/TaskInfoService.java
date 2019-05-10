package service;

import util.XslResult;

import java.util.List;

public interface TaskInfoService {
	List getTaskTags(String taskId);

	XslResult sendMq(String msg);
}
