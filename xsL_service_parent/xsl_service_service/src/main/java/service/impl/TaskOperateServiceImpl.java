package service.impl;

import com.google.gson.Gson;
import com.xsl.task.TaskOperateResource;
import com.xsl.task.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import service.TaskOperateService;
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

    @Override
    public XslResult sendTask(XslTaskReqVo xsltaskReqVo) {

        Gson gson = new Gson();
        String taskReqVoString = gson.toJson(xsltaskReqVo);
        TaskReqVo taskReqVo = gson.fromJson(taskReqVoString,TaskReqVo.class);
        try {
            ResBaseVo resBaseVo = taskOperateResource.sendTask(taskReqVo);
            if(resBaseVo.getStatus()==200){
                return XslResult.ok(resBaseVo.getExParam());
            }

            return XslResult.build(resBaseVo.getStatus(),resBaseVo.getMsg());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public XslResult receiveTask(XslRecTaskReqVo xslRecTaskReqVo) {
        RecTaskReqVo recTaskReqVo = new RecTaskReqVo();
        BeanUtils.copyProperties(xslRecTaskReqVo,recTaskReqVo);
        try {
            ResBaseVo resBaseVo = taskOperateResource.receiveTask(recTaskReqVo);
            if(resBaseVo.getStatus()==200){
                return XslResult.ok();
            }

            return XslResult.build(resBaseVo.getStatus(),resBaseVo.getMsg());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public XslResult confirmTask(XslConfirmTaskReqVo xslConfirmTaskReqVo) {
        ConfirmTaskReqVo confirmTaskReqVo = new ConfirmTaskReqVo();
        BeanUtils.copyProperties(xslConfirmTaskReqVo,confirmTaskReqVo);
        try {
            ResBaseVo resBaseVo = taskOperateResource.confirmTask(confirmTaskReqVo);
            if(resBaseVo.getStatus()==200){
                return XslResult.ok(resBaseVo.getExParam());
            }

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
        SearchTaskReqVo taskSearchVo = new SearchTaskReqVo();
        BeanUtils.copyProperties(xslTaskSearchVo,taskSearchVo);
        try {
            SearchTaskInfoListResVo searchTaskInfoListResVo = taskOperateResource.searchTask(taskSearchVo);
            if(searchTaskInfoListResVo.getStatus()==200){
                return XslResult.ok(searchTaskInfoListResVo.getTaskInfoList());
            }

            return XslResult.build(searchTaskInfoListResVo.getStatus(),searchTaskInfoListResVo.getMsg());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public XslResult cancelTask(String taskId) {
        try {
            ResBaseVo resBaseVo = taskOperateResource.cancelTask(taskId);
            if(resBaseVo.getStatus()==200){
                return XslResult.ok();
            }

            return XslResult.build(resBaseVo.getStatus(),resBaseVo.getMsg());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
