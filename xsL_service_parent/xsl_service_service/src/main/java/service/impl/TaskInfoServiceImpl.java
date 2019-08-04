package service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xsl.task.TaskInfoResource;
import com.xsl.task.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskInfoServiceImpl.class);

	@Override
	public XslResult sendMq(String msg) {
		XslResult build = XslResult.build(500, "000");
		jmsTemplate.send(mqTest, (session)->session.createObjectMessage(build));
		return XslResult.ok();
	}

	@Override
	public List<XslTask> getTaskByMasterId(String masterId) {
		try {
			TaskListResVo taskListResVo = taskInfoResource.getTaskByMasterId(masterId);
			if(taskListResVo.getStatus()==200){
				List<XslTask> taskList = copySendRecTaskVosList(taskListResVo.getSendRecTaskVoList());
				return taskList;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public XslResult initTaskInfo(XslTaskInfoListReqVo xslTaskInfoListReqVo) {
		Gson gson = GsonSingle.getGson();
		LOGGER.info("initTaskInfo InParam:{}",gson.toJson(xslTaskInfoListReqVo));
		TaskInfoListReqVo taskInfoListReqVo = new TaskInfoListReqVo();
		BeanUtils.copyProperties(xslTaskInfoListReqVo,taskInfoListReqVo);
		try {
			TaskInfoListResVo taskInfo = taskInfoResource.initTaskInfo(taskInfoListReqVo);
			if(taskInfo.getStatus()==200){
				XslTaskInfoListResVo xslTaskInfoListResVo = gson.fromJson(gson.toJson(taskInfo),XslTaskInfoListResVo.class);
				LOGGER.info("initTaskInfo OutParam:{}",gson.toJson(xslTaskInfoListResVo));
				return XslResult.ok(xslTaskInfoListResVo);
			}
			LOGGER.error("初始化任务厅失败:  TaskInfoListReqVo is {}",gson.toJson(taskInfoListReqVo));
			return XslResult.build(taskInfo.getStatus(),taskInfo.getMsg());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public XslResult reloadTaskInfo(XslTaskInfoListReqVo xslTaskInfoListReqVo) {
		Gson gson = GsonSingle.getGson();
		LOGGER.info("reloadTaskInfo InParam:{}",gson.toJson(xslTaskInfoListReqVo));
		TaskInfoListReqVo taskInfoListReqVo = new TaskInfoListReqVo();
		BeanUtils.copyProperties(xslTaskInfoListReqVo,taskInfoListReqVo);
		try {
			TaskInfoListResVo taskInfo = taskInfoResource.reloadTaskInfo(taskInfoListReqVo);
			if(taskInfo.getStatus()==200){
				XslTaskInfoListResVo xslTaskInfoListResVo = gson.fromJson(gson.toJson(taskInfo),XslTaskInfoListResVo.class);
				LOGGER.info("reloadTaskInfo OutParam:{}",gson.toJson(xslTaskInfoListResVo));
				return XslResult.ok(xslTaskInfoListResVo);
			}
			LOGGER.error("重新加载任务大厅失败： TaskInfoListReqVo is {}",gson.toJson(taskInfoListReqVo));
			return XslResult.build(taskInfo.getStatus(),taskInfo.getMsg());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public XslResult taskInfo(String taskId) {
		LOGGER.info("taskInfo InParam:{}",taskId);
		try {
			TaskInfoResVo taskInfoResVo = taskInfoResource.taskInfo(taskId);
			if(taskInfoResVo.getStatus()==200){
				XslTaskInfoResVo xslTaskInfoResVo = new XslTaskInfoResVo();
				BeanUtils.copyProperties(taskInfoResVo,xslTaskInfoResVo);
				LOGGER.info("taskInfo OutParam:{}",GsonSingle.getGson().toJson(xslTaskInfoResVo));
				return XslResult.ok(xslTaskInfoResVo);
			}
			LOGGER.error("获取任务信息失败 taskInfoResVo is {}",GsonSingle.getGson().toJson(taskInfoResVo));
			return XslResult.build(taskInfoResVo.getStatus(),taskInfoResVo.getMsg());
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public XslResult querySendTask(XslSendAndRecTaskReqVo xslSendAndRecTaskReqVo) {
		Gson gson = GsonSingle.getGson();
		LOGGER.info("querySendTask InParam:{}",gson.toJson(xslSendAndRecTaskReqVo));
		SendAndRecTaskReqVo sendAndRecTaskReqVo = new SendAndRecTaskReqVo();
		BeanUtils.copyProperties(xslSendAndRecTaskReqVo,sendAndRecTaskReqVo);

		try {
			TaskListResVo taskListResVo = taskInfoResource.querySendTask(sendAndRecTaskReqVo);
			if(taskListResVo.getStatus()==200){
				List<XslTask> taskList = copySendRecTaskVosList(taskListResVo.getSendRecTaskVoList());
				LOGGER.info("querySendTask OutParam:{}",gson.toJson(taskList));
				return XslResult.ok(taskList);
			}
			LOGGER.error("查询已发任务失败： taskListResVo is {}",gson.toJson(taskListResVo));
			return XslResult.build(taskListResVo.getStatus(),taskListResVo.getMsg());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public XslResult queryReceiveTask(XslSendAndRecTaskReqVo xslSendAndRecTaskReqVo) {
		Gson gson = GsonSingle.getGson();
		LOGGER.info("queryReceiveTask InParam:{}",gson.toJson(xslSendAndRecTaskReqVo));
		SendAndRecTaskReqVo sendAndRecTaskReqVo = new SendAndRecTaskReqVo();
		BeanUtils.copyProperties(xslSendAndRecTaskReqVo,sendAndRecTaskReqVo);

		try {
			TaskListResVo taskListResVo = taskInfoResource.queryReceiveTask(sendAndRecTaskReqVo);
			if(taskListResVo.getStatus()==200){
				List<XslTask> taskList = copySendRecTaskVosList(taskListResVo.getSendRecTaskVoList());
				LOGGER.info("queryReceiveTask OutParam:{}",gson.toJson(taskList));
				return XslResult.ok(taskList);
			}
			LOGGER.error("查询已接任务失败： taskListResVo is {}",gson.toJson(taskListResVo));
			return XslResult.build(taskListResVo.getStatus(),taskListResVo.getMsg());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public XslResult UpCategoryHunter(String tagName, Integer type, Integer rows) {
		try {
			Gson gson = GsonSingle.getGson();
			LOGGER.info("UpCategoryHunter InParam:{} {} {}",tagName,type,rows);
			ResBaseVo resBaseVo = taskInfoResource.UpCategoryHunter(tagName,type,rows);
			if(resBaseVo.getStatus()==200){
				LOGGER.info("UpCategoryHunter OutParam:{}",gson.toJson(resBaseVo.getExParam()));
				return XslResult.ok(resBaseVo.getExParam());
			}
			LOGGER.error("分类展示猎人失败 resBaseVo is {}",gson.toJson(resBaseVo));
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

	private List<XslTask> copySendRecTaskVosList(List<SendRecTaskVo> sendRecTaskVos){
		Gson gson = GsonSingle.getGson();
		String tasks = gson.toJson(sendRecTaskVos);
		List<XslTask> xslTasks = gson.fromJson(tasks,new TypeToken<List<XslTask>>(){}.getType());
		return xslTasks;
	}
}
