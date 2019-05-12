package service;

import pojo.XslTag;
import util.XslResult;

import java.util.List;

public interface TaskInfoService {
	List<XslTag> getTaskTags(String taskId);

	XslResult sendMq(String msg);
}
