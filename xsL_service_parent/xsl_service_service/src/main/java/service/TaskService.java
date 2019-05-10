package service;

import util.XslResult;
import vo.*;

public interface TaskService {
    /**
     * 分页展示猎人种类
     *
     * @param tagName
     * @param type
     * @param rows
     * @return
     */
    XslResult UpCategoryHunter(String tagName, Integer type, Integer rows);

    /**
     * 发送任务
     *
     * @return
     */
    XslResult sendTask(TaskReqVo taskReqVo);

    /**
     * 获取已发任务
     * @return
     */
    XslResult querySendTask(SendAndRecTaskReqVo sendAndRecTaskReqVo);

    /**
     * 获取已接任务
     * @return
     */
    XslResult queryReceiveTask(SendAndRecTaskReqVo sendAndRecTaskReqVo);

    /**
     * 初始化任务大厅数据
     * @return
     */
    XslResult initTaskInfo(TaskInfoListReqVo taskInfoListReqVo);


    /**
     * 刷新任务大厅数据
     * @return
     */
    XslResult reloadTaskInfo(TaskInfoListReqVo taskInfoListReqVo);


	/**
	 * 接收任务
	 * @return
	 */
	XslResult receiveTask(RecTaskReqVo recTaskReqVo);

	/**
	 * 任务详情
	 * @return
	 */
	XslResult taskInfo(String taskId);

	/**
	 * 雇主猎人确认任务完成
	 * @return
	 */
	XslResult confirmTask(ConfirmTaskReqVo confirmTaskReqVo);

	/**
	 * 任务搜索
	 * @return
	 */
	XslResult searchTask(SearchTaskReqVo taskSearchVo);
}
