package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.TaskOperateService;
import util.XslResult;
import vo.XslConfirmTaskReqVo;
import vo.XslRecTaskReqVo;
import vo.XslSearchTaskReqVo;
import vo.XslTaskReqVo;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/7/8 14:26
 */
@Controller
@RequestMapping("/xsl/task")
public class TaskOperateController {

    @Autowired
    private TaskOperateService taskOperateService;

    /**
     * 发送任务
     * @param xsltaskReqVo
     * @return
     */
    @RequestMapping(value = "/sendTask", method = RequestMethod.POST)
    @ResponseBody
    public XslResult sendTask(@RequestBody XslTaskReqVo xsltaskReqVo){
        XslResult xslResult = taskOperateService.sendTask(xsltaskReqVo);
        return xslResult;
    }

    /**
     * 接受任务
     * @param xslRecTaskReqVo
     * @return
     */
    @RequestMapping("/receiveTask")
    @ResponseBody
    public XslResult receiveTask(XslRecTaskReqVo xslRecTaskReqVo){
        XslResult xslResult = taskOperateService.receiveTask(xslRecTaskReqVo);
        return xslResult;
    }

    /**
     * 猎人确认任务完成
     * @param xslConfirmTaskReqVo
     * @return
     */
    @RequestMapping("/confirmTask")
    @ResponseBody
    public XslResult confirmTask(XslConfirmTaskReqVo xslConfirmTaskReqVo){
        xslConfirmTaskReqVo.setNowState((byte) 2);
        xslConfirmTaskReqVo.setAfterState((byte) 4);
        XslResult xslResult = taskOperateService.confirmTask(xslConfirmTaskReqVo);
        return xslResult;

    }

    /**
     * 任务终结
     *
     * @return
     */
    @RequestMapping("/okTask")
    @ResponseBody
    public XslResult okTask(XslConfirmTaskReqVo xslconfirmTaskReqVo){
        xslconfirmTaskReqVo.setNowState((byte) 4);
        xslconfirmTaskReqVo.setAfterState((byte) 3);
        return taskOperateService.confirmTask(xslconfirmTaskReqVo);
    }

    @RequestMapping("/cancelTask")
    @ResponseBody
    public XslResult cancelTask(String taskId){
        XslResult xslResult = taskOperateService.cancelTask(taskId);
        return xslResult;
    }

    @RequestMapping("/searchTask")
    @ResponseBody
    public XslResult searchTask(XslSearchTaskReqVo xslTaskSearchVo){
        return taskOperateService.searchTask(xslTaskSearchVo);
    }

}
