package controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.*;
import util.XslResult;
import vo.*;

import java.text.ParseException;

/**
 * 任务推送、展示
 *
 * @author 高山潍
 */
@Controller
@RequestMapping("/xsl/task")
public class TaskController {
    @Autowired
    private TaskTopush taskTopush;
    @Autowired
    private TaskAccept taskAccept;
    @Autowired
    private Collect collect;
    @Autowired
    private TaskService taskService;
    @Autowired
    private SupplementDataService supplementDataService;


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
     * 任务接收
     * @param hunterId
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/accept", method = RequestMethod.GET)
    @ResponseBody
    public XslResult acceptTask(@RequestParam("hunterId") int hunterId, @Param("taskId") String taskId) {
        XslResult xslResult = null;
        try {
            xslResult = taskAccept.acceptTask(hunterId, taskId);
            return xslResult;
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }


    /**
     * 收藏任务
     *
     * @param userId
     * @param taskId
     * @return
     */
    @RequestMapping("/collecttask")
    @ResponseBody
    public XslResult collectTask(Integer userId, Integer taskId) {
        try {
            XslResult xslResult = collect.collectTask(userId, taskId);
            return xslResult;
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 查找收藏任务
     *
     * @param userId
     * @return
     */
    @RequestMapping("/findcollecttask")
    @ResponseBody
    public XslResult findCollectTask(Integer userId, Integer page, Integer rows) {
        try {
            XslResult xslResult = collect.findcollectTask(userId, page, rows);
            return xslResult;
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
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
