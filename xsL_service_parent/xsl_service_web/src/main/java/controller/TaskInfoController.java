package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.TaskInfoService;
import util.XslResult;
import vo.XslSendAndRecTaskReqVo;
import vo.XslTaskInfoListReqVo;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/7/9 9:21
 */
@Controller
@RequestMapping("/xsl/task")
public class TaskInfoController {

    @Autowired
    private TaskInfoService taskInfoService;

    /**
     * 初始化任务大厅
     * @param xslTaskInfoListReqVo
     * @return
     */
    @RequestMapping("/initTaskInfo")
    @ResponseBody
    public XslResult initTaskInfo(XslTaskInfoListReqVo xslTaskInfoListReqVo){
        XslResult xslResult = taskInfoService.initTaskInfo(xslTaskInfoListReqVo);
        return xslResult;
    }

    /**
     * 刷新任务大厅数据
     * @param xslTaskInfoListReqVo
     * @return
     */
    @RequestMapping("/reloadTaskInfo")
    @ResponseBody
    public XslResult reloadTaskInfo(XslTaskInfoListReqVo xslTaskInfoListReqVo){
        XslResult xslResult = taskInfoService.reloadTaskInfo(xslTaskInfoListReqVo);
        return xslResult;
    }

    /**
     * 任务详情
     * @param taskId
     * @return
     */
    @RequestMapping("/queryTaskInfo")
    @ResponseBody
    public XslResult queryTaskInfo(String taskId){
        XslResult xslResult = taskInfoService.taskInfo(taskId);
        return xslResult;
    }

    /**
     * 查找已发任务
     * @param xslSendAndRecTaskReqVo
     * @return
     */
    @RequestMapping("/querySendTask")
    @ResponseBody
    public XslResult querySendTask(XslSendAndRecTaskReqVo xslSendAndRecTaskReqVo){
        XslResult xslResult = taskInfoService.querySendTask(xslSendAndRecTaskReqVo);
        return xslResult;
    }

    /**
     * 获取已接任务
     * @param xslSendAndRecTaskReqVo
     * @return
     */
    @RequestMapping("/queryReceiveTask")
    @ResponseBody
    public XslResult queryReceiveTask(XslSendAndRecTaskReqVo xslSendAndRecTaskReqVo){
        XslResult xslResult = taskInfoService.queryReceiveTask(xslSendAndRecTaskReqVo);
        return xslResult;
    }

}
