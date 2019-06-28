package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.*;
import util.XslResult;
import vo.*;


@Controller
@RequestMapping("/xsl/task")
public class TaskController {
    @Autowired
    private TaskService taskService;


    /**
     * 已发任务
     *
     * @param sendAndRecTaskReqVo
     * @return
     */
    @RequestMapping("/querySendTask")
    @ResponseBody
    public XslResult querySendTask(SendAndRecTaskReqVo sendAndRecTaskReqVo) {
        XslResult xslResult = taskService.querySendTask(sendAndRecTaskReqVo);
        return xslResult;
    }

    /**
     * 已接任务
     * @return
     */
    @RequestMapping("/queryReceiveTask")
    @ResponseBody
    public XslResult queryReceiveTask(SendAndRecTaskReqVo sendAndRecTaskReqVo) {
        XslResult xslResult = taskService.queryReceiveTask(sendAndRecTaskReqVo);
        return xslResult;
    }


    /**
     * 发送任务
     *
     * @return
     */
    @RequestMapping(value = "/sendTask", method = RequestMethod.POST)
    @ResponseBody
    public XslResult sendTask(@RequestBody TaskReqVo taskReqVo) {
        XslResult xslResult = taskService.sendTask(taskReqVo);
        return xslResult;
    }


    /**
     * 初始化任务大厅
     *
     * @return
     */
    @RequestMapping("/initTaskInfo")
    @ResponseBody
    public XslResult initTaskInfo(TaskInfoListReqVo taskInfoListReqVo){
        return taskService.initTaskInfo(taskInfoListReqVo);
    }

    /**
     * 刷新任务大厅
     *
     * @return
     */
    @RequestMapping("/reloadTaskInfo")
    @ResponseBody
    public XslResult reloadTaskInfo(TaskInfoListReqVo taskInfoListReqVo){
        return taskService.reloadTaskInfo(taskInfoListReqVo);
    }

    /**
     * 接收任务
     *
     * @return
     */
    @RequestMapping("/receiveTask")
    @ResponseBody
    public XslResult receiveTask(RecTaskReqVo recTaskReqVo){
        return taskService.receiveTask(recTaskReqVo);
    }


    /**
     * 任务详情
     *
     * @return
     */
    @RequestMapping("/queryTaskInfo")
    @ResponseBody
    public XslResult queryTaskInfo(String taskId){
        return taskService.taskInfo(taskId);
    }

    /**
     * 猎人确认任务完成
     *
     * @return
     */
    @RequestMapping("/confirmTask")
    @ResponseBody
    public XslResult confirmTask(ConfirmTaskReqVo confirmTaskReqVo){
		confirmTaskReqVo.setNowState((byte) 2);
		confirmTaskReqVo.setAfterState((byte) 4);
        return taskService.confirmTask(confirmTaskReqVo);
    }

	/**
	 * 任务终结
	 *
	 * @return
	 */
	@RequestMapping("/okTask")
	@ResponseBody
	public XslResult okTask(ConfirmTaskReqVo confirmTaskReqVo){
		confirmTaskReqVo.setNowState((byte) 4);
		confirmTaskReqVo.setAfterState((byte) 3);
		return taskService.confirmTask(confirmTaskReqVo);
	}

    /**
     * 任务搜索
     *
     * @return
     */
    @RequestMapping("/searchTask")
    @ResponseBody
    public XslResult searchTask(SearchTaskReqVo taskSearchVo){
        return taskService.searchTask(taskSearchVo);
    }


    /**
     * 任务撤回
     *
     * @return
     */
    @RequestMapping("/cancelTask")
    @ResponseBody
    public XslResult cancelTask(String taskId){
        return taskService.cancelTask(taskId);
    }

}
