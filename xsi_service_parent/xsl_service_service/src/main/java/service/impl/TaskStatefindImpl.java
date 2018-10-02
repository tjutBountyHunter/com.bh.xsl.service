package service.impl;

import mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.XslHistoryhExample;
import pojo.XslWaitTask;
import service.JsonUtils;
import service.TaskStatefind;
import service.XslResult;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;

public class TaskStatefindImpl implements TaskStatefind {
    @Autowired
    private XslWaitTaskMapper xslWaitTaskMapper;
    @Autowired
    private XslWaitHunterTaskMapper xslWaitHunterTaskMapper;
    @Autowired
    private XslDoneTaskMapper xslDoneTaskMapper;
    @Autowired
    private XslDoneTaskMasterMapper xslDoneTaskMasterMapper;

    /**
     * 雇主发出未完成任务
     *
     * @param userId
     * @return
     */
    @Override
    public String waitAccomplish(Integer userId) {
        List<XslWaitTask> list = xslWaitTaskMapper.selectByuserId(userId);
        String json = JsonUtils.objectToJson(list);
        return json;
    }

    /**
     * 用户作为猎人未完成任务
     *
     * @param userId
     * @return
     */
    @Override
    public String waitHunterAccomplish(Integer userId) {
        List<XslWaitTask> list = xslWaitHunterTaskMapper.selectByHunterId(userId);
        String json = JsonUtils.objectToJson(list);
        return json;
    }

    /**
     * 总共未完成任务
     *
     * @param userId
     * @return
     */
    @Override
    public String coutWaitTask(Integer userId, Integer page, Integer rows) {
        TaskStatefind taskStatefind = new TaskStatefindImpl();
        String jsonMaster = taskStatefind.waitAccomplish(userId);
        String jsonHunter = taskStatefind.waitHunterAccomplish(userId);
        String jsonCount = jsonHunter + jsonMaster;
        String s = jsonTask(jsonCount, page, rows);
        return s;
    }

    /**
     * 用户作为猎人完成任务
     *
     * @param userId
     * @return
     */
    @Override
    public String doneTaskHunter(Integer userId) {
        List<XslWaitTask> list = xslDoneTaskMapper.selectByuseId(userId);
        String json = JsonUtils.objectToJson(list);
        return json;
    }

    /**
     * 用户作为雇主发送任务
     *
     * @param userId
     * @return
     */
    @Override
    public String doneTaskMaster(Integer userId) {
        List<XslWaitTask> list = xslDoneTaskMasterMapper.selectByuserId(userId);
        String json = JsonUtils.objectToJson(list);
        return json;
    }

    /**
     * @param userId
     * @param page
     * @param rows
     * @return
     */
    @Override
    public String coutDoneTask(Integer userId, Integer page, Integer rows) {
        TaskStatefind taskStatefind = new TaskStatefindImpl();
        String jsonMaster = taskStatefind.doneTaskHunter(userId);
        String jsonHunter = taskStatefind.doneTaskMaster(userId);
        String jsonCount = jsonHunter + jsonMaster;
        String s = jsonTask(jsonCount, page, rows);
        return s;
    }

    public String jsonTask(String jsonUtils, Integer page, Integer rows) {
        List<XslWaitTask> list = JsonUtils.jsonToList(jsonUtils, XslWaitTask.class);
        if (list.size() == 0 && list.isEmpty()) {
            return null;
        } else if (list.size() <= 10 && list.size() > 0 && !list.isEmpty()) {
            return jsonUtils;
        } else {
            List<XslWaitTask> list1 = new ArrayList<>();
            for (int i = page - 1; i < rows; i++) {
                list1.add(list.get(i));
            }
            String json = JsonUtils.objectToJson(list1);
            return json;
        }
    }

}
