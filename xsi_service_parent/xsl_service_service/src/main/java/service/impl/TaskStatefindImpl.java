package service.impl;

import mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.XslHistoryhExample;
import pojo.XslWaitTask;
import pojo.Xsltaskmainpage;
import service.JsonUtils;
import service.TaskStatefind;
import service.XslResult;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;

@Service
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
     * 发送任务
     *
     * @param usrId
     * @param page
     * @param rows
     * @return
     */
    @Override
    public XslResult sendTask(Integer usrId, Integer page, Integer rows) {
        try {
            List<Xsltaskmainpage> list = xslWaitTaskMapper.selectBysendId(usrId);
            if (list.size() == 0 && list.isEmpty()) {
                return XslResult.ok("您还没有发送任务");
            } else {
                List<Xsltaskmainpage> list_size = new ArrayList<>();
                for (int i = (page - 1) * rows; i < page * rows; i++) {
                    if (list.get(i) == null) {
                        break;
                    }
                    list_size.add(list.get(i));
                }
                return XslResult.ok(list_size);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 已经接收任务
     *
     * @param usrId
     * @param page
     * @param rows
     * @return
     */
    @Override
    public XslResult accectTask(Integer usrId, Integer page, Integer rows) {
        try {
            List<Xsltaskmainpage> list = xslWaitTaskMapper.selectByaccectId(usrId);
            if (list.size() == 0 && list.isEmpty()) {
                return XslResult.ok("您还没有接收任务");
            } else {
                List<Xsltaskmainpage> list_size = new ArrayList<>(1);
                for (int i = (page - 1) * rows; i < page * rows; i++) {
                    if (i >= list.size()) {
                        System.out.println("!!!!!");
                        break;
                    }
                    list_size.add(list.get(i));
                }
                return XslResult.ok(list_size);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 雇主发出未完成任务
     *
     * @param userId
     * @return
     */
    @Override
    public List<XslWaitTask> waitAccomplish(Integer userId) {
        System.out.println(userId);
        List<XslWaitTask> list = xslWaitTaskMapper.selectByuserId(userId);
        return list;
    }

    /**
     * 用户作为猎人未完成任务
     *
     * @param userId
     * @return
     */
    @Override
    public List<XslWaitTask> waitHunterAccomplish(Integer userId) {
        List<XslWaitTask> list = xslWaitHunterTaskMapper.selectByHunterId(userId);
        return list;
    }

    /**
     * 总共未完成任务
     *
     * @param userId
     * @return
     */
    @Override
    public XslResult coutWaitTask(Integer userId, Integer page, Integer rows) {
        try {
            List<XslWaitTask> jsonMaster = waitAccomplish(userId);
            List<XslWaitTask> jsonHunter = waitHunterAccomplish(userId);
            jsonMaster.addAll(jsonHunter);
            List<XslWaitTask> listCount = jsonMaster;
            List<XslWaitTask> jsonCount = new ArrayList<>();
            if (listCount.size() == 0 && listCount.isEmpty()) {
                return XslResult.ok("您还是新用户吧");
            } else {
                for (int i = (page - 1) * rows; i < page * rows; i++) {
                    if (i >= listCount.size()) {
                        System.out.println("!!!!!");
                        break;
                    }
                    jsonCount.add(listCount.get(i));
                }
            }
            return XslResult.ok(jsonCount);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 用户作为猎人完成任务
     *
     * @param userId
     * @return
     */
    @Override
    public List<XslWaitTask> doneTaskHunter(Integer userId) {
        List<XslWaitTask> list = xslDoneTaskMapper.selectByuseId(userId);
        return list;
    }

    /**
     * 用户作为雇主发送任务
     *
     * @param userId
     * @return
     */
    @Override
    public List<XslWaitTask> doneTaskMaster(Integer userId) {
        List<XslWaitTask> list = xslDoneTaskMasterMapper.selectByuserId(userId);
        return list;
    }

    /**
     * 完成任务
     * @param userId
     * @param page
     * @param rows
     * @return
     */
    @Override
    public XslResult coutDoneTask(Integer userId, Integer page, Integer rows) {
        try {
            List<XslWaitTask> jsonMaster = doneTaskHunter(userId);
            List<XslWaitTask> jsonHunter = doneTaskMaster(userId);
            jsonMaster.addAll(jsonHunter);
            List<XslWaitTask> listCount = jsonMaster;
            List<XslWaitTask> jsonCount = new ArrayList<>();
            if (listCount.size() == 0 && listCount.isEmpty()) {
                return XslResult.ok("您还是新用户吧");
            } else {
                for (int i = (page - 1) * rows; i < page * rows; i++) {
                    if (i >= listCount.size()) {
                        System.out.println("!!!!!");
                        break;
                    }
                    jsonCount.add(listCount.get(i));
                }
            }
            return XslResult.ok(jsonCount);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

}
