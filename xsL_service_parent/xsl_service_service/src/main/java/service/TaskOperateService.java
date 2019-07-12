package service;

import util.XslResult;
import vo.XslConfirmTaskReqVo;
import vo.XslRecTaskReqVo;
import vo.XslSearchTaskReqVo;
import vo.XslTaskReqVo;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/7/8 12:40
 */
public interface TaskOperateService {

    /**
     * 发送任务
     *
     * @return
     */
    XslResult sendTask(XslTaskReqVo xsltaskReqVo);


    /**
     * 接收任务
     * @return
     */
    XslResult receiveTask(XslRecTaskReqVo xslRecTaskReqVo);


    /**
     * 确认任务完成
     * @return
     */
    XslResult confirmTask(XslConfirmTaskReqVo xslConfirmTaskReqVo);


    /**
     * 取消超时任务
     * @return
     */
    XslResult calcelTaskDDL();

    /**
     * 任务搜索
     * @return
     */
    XslResult searchTask(XslSearchTaskReqVo xslTaskSearchVo);


    /**
     * 任务取消
     * @return
     */
    XslResult cancelTask(String taskId);
}
