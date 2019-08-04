package service.impl;

import com.google.gson.Gson;
import com.xsl.task.TaskOperateResource;
import com.xsl.task.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import service.TaskOperateService;
import util.GsonSingle;
import util.XslResult;
import vo.XslConfirmTaskReqVo;
import vo.XslRecTaskReqVo;
import vo.XslSearchTaskReqVo;
import vo.XslTaskReqVo;

import javax.annotation.Resource;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/7/8 12:43
 */
@Service
public class TaskOperateServiceImpl implements TaskOperateService {

    @Resource
    private TaskOperateResource taskOperateResource;

    private static final Logger LOGGER  = LoggerFactory.getLogger(TaskOperateServiceImpl.class);

    @Override
    public XslResult sendTask(XslTaskReqVo xsltaskReqVo) {
        Gson gson = new Gson();
        String taskReqVoString = gson.toJson(xsltaskReqVo);
        LOGGER.info("sendTask InParam:{}",gson.toJson(xsltaskReqVo));
        TaskReqVo taskReqVo = gson.fromJson(taskReqVoString,TaskReqVo.class);
        try {
            ResBaseVo resBaseVo = taskOperateResource.sendTask(taskReqVo);
            if(resBaseVo.getStatus()==200){
                LOGGER.info("sendTask OutParam:{}",gson.toJson(resBaseVo.getExParam()));
                return XslResult.ok(resBaseVo.getExParam());
            }
            LOGGER.error("发送任务失败 resBaseVo is {}",GsonSingle.getGson().toJson(resBaseVo));
            return XslResult.build(resBaseVo.getStatus(),resBaseVo.getMsg());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public XslResult receiveTask(XslRecTaskReqVo xslRecTaskReqVo) {
        LOGGER.info("receiveTask InParam:{}",GsonSingle.getGson().toJson(xslRecTaskReqVo));
        RecTaskReqVo recTaskReqVo = new RecTaskReqVo();
        BeanUtils.copyProperties(xslRecTaskReqVo,recTaskReqVo);
        try {
            ResBaseVo resBaseVo = taskOperateResource.receiveTask(recTaskReqVo);
            if(resBaseVo.getStatus()==200){
                return XslResult.ok();
            }
            LOGGER.error("接受任务失败 resBaseVo is {}",GsonSingle.getGson().toJson(resBaseVo));
            return XslResult.build(resBaseVo.getStatus(),resBaseVo.getMsg());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public XslResult confirmTask(XslConfirmTaskReqVo xslConfirmTaskReqVo) {
        Gson gson = GsonSingle.getGson();
        LOGGER.info("confirmTask InParam:{}",gson.toJson(xslConfirmTaskReqVo));
        ConfirmTaskReqVo confirmTaskReqVo = new ConfirmTaskReqVo();
        BeanUtils.copyProperties(xslConfirmTaskReqVo,confirmTaskReqVo);
        try {
            ResBaseVo resBaseVo = taskOperateResource.confirmTask(confirmTaskReqVo);
            if(resBaseVo.getStatus()==200){
                LOGGER.info("confirmTask OutParam:{}",gson.toJson(resBaseVo.getExParam()));
                return XslResult.ok(resBaseVo.getExParam());
            }
            LOGGER.error("雇主确认任务失败 resBaseVo is {}",gson.toJson(resBaseVo));
            return XslResult.build(resBaseVo.getStatus(),resBaseVo.getMsg());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public XslResult cancelTaskDDL() {

        try {
            ResBaseVo resBaseVo = taskOperateResource.cancelTaskDDL();
            if(resBaseVo.getStatus()==200){
                return XslResult.ok();
            }

            return XslResult.build(resBaseVo.getStatus(),resBaseVo.getMsg());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public XslResult searchTask(XslSearchTaskReqVo xslTaskSearchVo) {
        Gson gson = GsonSingle.getGson();
        LOGGER.info("searchTask InParam:{}",gson.toJson(xslTaskSearchVo));
        SearchTaskReqVo taskSearchVo = new SearchTaskReqVo();
        BeanUtils.copyProperties(xslTaskSearchVo,taskSearchVo);
        try {
            SearchTaskInfoListResVo searchTaskInfoListResVo = taskOperateResource.searchTask(taskSearchVo);
            if(searchTaskInfoListResVo.getStatus()==200){
                LOGGER.info("searchTask OutParam:{}",gson.toJson(searchTaskInfoListResVo.getTaskInfoList()));
                return XslResult.ok(searchTaskInfoListResVo.getTaskInfoList());
            }
            LOGGER.error("搜索任务失败： searchTaskInfoListResVo is {}",gson.toJson(searchTaskInfoListResVo));
            return XslResult.build(searchTaskInfoListResVo.getStatus(),searchTaskInfoListResVo.getMsg());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public XslResult cancelTask(String taskId) {
        try {
            LOGGER.info("cancelTask InParam:{}",taskId);
            ResBaseVo resBaseVo = taskOperateResource.cancelTask(taskId);
            if(resBaseVo.getStatus()==200){
                return XslResult.ok();
            }
            LOGGER.error("取消任务失败：resBaseVo is {}",GsonSingle.getGson().toJson(resBaseVo));
            return XslResult.build(resBaseVo.getStatus(),resBaseVo.getMsg());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
