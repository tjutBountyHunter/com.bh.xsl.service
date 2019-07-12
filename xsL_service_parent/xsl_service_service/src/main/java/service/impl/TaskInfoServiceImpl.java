package service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xsl.task.TaskInfoResource;
import com.xsl.task.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import pojo.XslTask;
import service.TaskInfoService;
import util.GsonSingle;
import util.XslResult;
import vo.TaskInfo;
import vo.*;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskInfoServiceImpl implements TaskInfoService {

	@Resource
	private TaskInfoResource taskInfoResource;

	@Value("${TASK_TAG_INFO}")
	private String TASK_TAG_INFO;


	@Autowired
	private JmsTemplate jmsTemplate;

	@Resource
	private Destination mqTest;


	@Override
	public List<TagResVo> getTaskTags(String taskId) {
		List<TagResVo> taskTags = taskInfoResource.getTaskTags(taskId);
		return taskTags;
	}

	@Override
	public XslResult sendMq(String msg) {
		XslResult build = XslResult.build(500, "000");
		jmsTemplate.send(mqTest, (session)->session.createObjectMessage(build));
		return XslResult.ok();
	}

	@Override
	public List<XslTask> getTaskByMasterId(String masterId) {
		return null;
	}

	@Override
	public XslResult initTaskInfo(XslTaskInfoListReqVo xslTaskInfoListReqVo) {
		TaskInfoListReqVo taskInfoListReqVo = new TaskInfoListReqVo();
		BeanUtils.copyProperties(xslTaskInfoListReqVo,taskInfoListReqVo);
		try {
			TaskInfoListResVo taskInfo = taskInfoResource.initTaskInfo(taskInfoListReqVo);
			if(taskInfo.getStatus()==200){
				XslTaskInfoListResVo xslTaskInfoListResVo = completeTaskInfoListResVoInfo(taskInfo);
				return XslResult.ok(xslTaskInfoListResVo);
			}

			return XslResult.build(taskInfo.getStatus(),taskInfo.getMsg());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public XslResult reloadTaskInfo(XslTaskInfoListReqVo xslTaskInfoListReqVo) {
		TaskInfoListReqVo taskInfoListReqVo = new TaskInfoListReqVo();
		BeanUtils.copyProperties(xslTaskInfoListReqVo,taskInfoListReqVo);
		try {
			TaskInfoListResVo taskInfo = taskInfoResource.reloadTaskInfo(taskInfoListReqVo);
			if(taskInfo.getStatus()==200){
				XslTaskInfoListResVo xslTaskInfoListResVo = completeTaskInfoListResVoInfo(taskInfo);
				return XslResult.ok(xslTaskInfoListResVo);
			}

			return XslResult.build(taskInfo.getStatus(),taskInfo.getMsg());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public XslResult taskInfo(String taskId) {
		try {
			TaskInfoResVo taskInfoResVo = taskInfoResource.taskInfo(taskId);
			if(taskInfoResVo.getStatus()==200){
				XslTaskInfoResVo xslTaskInfoResVo = new XslTaskInfoResVo();
				BeanUtils.copyProperties(taskInfoResVo,xslTaskInfoResVo);

				return XslResult.ok(xslTaskInfoResVo);
			}

			return XslResult.build(taskInfoResVo.getStatus(),taskInfoResVo.getMsg());
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public XslResult querySendTask(XslSendAndRecTaskReqVo xslSendAndRecTaskReqVo) {
		SendAndRecTaskReqVo sendAndRecTaskReqVo = new SendAndRecTaskReqVo();
		BeanUtils.copyProperties(xslSendAndRecTaskReqVo,sendAndRecTaskReqVo);

		try {
			TaskListResVo taskListResVo = taskInfoResource.querySendTask(sendAndRecTaskReqVo);
			if(taskListResVo.getStatus()==200){
				List<XslTask> taskList = copyTaskList(taskListResVo.getTaskList());
				return XslResult.ok(taskList);
			}

			return XslResult.build(taskListResVo.getStatus(),taskListResVo.getMsg());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public XslResult queryReceiveTask(XslSendAndRecTaskReqVo xslSendAndRecTaskReqVo) {
		SendAndRecTaskReqVo sendAndRecTaskReqVo = new SendAndRecTaskReqVo();
		BeanUtils.copyProperties(xslSendAndRecTaskReqVo,sendAndRecTaskReqVo);

		try {
			TaskListResVo taskListResVo = taskInfoResource.queryReceiveTask(sendAndRecTaskReqVo);
			if(taskListResVo.getStatus()==200){
				List<XslTask> taskList = copyTaskList(taskListResVo.getTaskList());
				return XslResult.ok(taskList);
			}
			return XslResult.build(taskListResVo.getStatus(),taskListResVo.getMsg());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public XslResult UpCategoryHunter(String tagName, Integer type, Integer rows) {
		try {
			ResBaseVo resBaseVo = taskInfoResource.UpCategoryHunter(tagName,type,rows);
			if(resBaseVo.getStatus()==200){
				return XslResult.ok(resBaseVo.getExParam());
			}

			return XslResult.build(resBaseVo.getStatus(),resBaseVo.getMsg());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private XslTaskInfoListResVo completeTaskInfoListResVoInfo(TaskInfoListResVo taskInfoListResVo){
		XslTaskInfoListResVo xslTaskInfoListResVo = new XslTaskInfoListResVo();
		xslTaskInfoListResVo.setUpFlag(taskInfoListResVo.getUpFlag());
		xslTaskInfoListResVo.setDownFlag(taskInfoListResVo.getDownFlag());
		List<TaskInfoVo> taskInfoVos = taskInfoListResVo.getTaskInfoVos();
		List<TaskInfo> taskInfos =  taskInfoVos.stream().collect(ArrayList::new,
				(res,item)->res.add(completeTaskInfo(item)),ArrayList::addAll);
		xslTaskInfoListResVo.setTaskInfoVos(taskInfos);
		return xslTaskInfoListResVo;
	}

	private TaskInfo completeTaskInfo(TaskInfoVo taskInfoVo){
		TaskInfo taskInfo = new TaskInfo();
		BeanUtils.copyProperties(taskInfoVo,taskInfo);
		return taskInfo;
	}

	private List<XslTask> copyTaskList(List<Task> taskList){
		Gson gson = GsonSingle.getGson();
		String tasks = gson.toJson(taskList);
		List<XslTask> xslTasks = gson.fromJson(tasks,new TypeToken<List<XslTask>>(){}.getType());
		return xslTasks;
	}
}
