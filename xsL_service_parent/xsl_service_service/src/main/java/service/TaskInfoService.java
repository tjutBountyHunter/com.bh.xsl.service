package service;

import pojo.XslTask;
import util.XslResult;
import vo.XslSendAndRecTaskReqVo;
import vo.XslTaskInfoListReqVo;

import java.util.List;

public interface TaskInfoService {


	XslResult sendMq(String msg);

	List<XslTask> getTaskByMasterId(String masterId);

	/**
	 * 初始化任务大厅数据
	 * @param xslTaskInfoListReqVo
	 * @return
	 */
	XslResult initTaskInfo(XslTaskInfoListReqVo xslTaskInfoListReqVo);

	/**
	 * 加载任务信息
	 * @param xslTaskInfoListReqVo
	 * @return
	 */
	XslResult reloadTaskInfo(XslTaskInfoListReqVo xslTaskInfoListReqVo);

	/**
	 * 任务详情
	 * @param taskId
	 * @return
	 */
	XslResult taskInfo(String taskId);

	/**
	 * 获取已发任务
	 * @param xslSendAndRecTaskReqVo
	 * @return
	 */
	XslResult querySendTask(XslSendAndRecTaskReqVo xslSendAndRecTaskReqVo);

	/**
	 * 获取已结任务
	 * @param xslSendAndRecTaskReqVo
	 * @return
	 */
	XslResult queryReceiveTask(XslSendAndRecTaskReqVo xslSendAndRecTaskReqVo);

	/**
	 * 分页展示猎人种类
	 *
	 * @param tagName
	 * @param type
	 * @param rows
	 * @return
	 */
	XslResult UpCategoryHunter(String tagName, Integer type, Integer rows);
}
