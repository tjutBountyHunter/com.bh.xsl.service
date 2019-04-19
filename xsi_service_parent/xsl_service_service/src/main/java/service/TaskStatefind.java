package service;

import pojo.XslWaitTask;
import util.XslResult;

import java.util.List;

public interface TaskStatefind {
    /**
     * 已经发送人任务
     *
     * @param usrId
     * @return
     */
    XslResult sendTask(Integer usrId, Integer page, Integer rows);

    /**
     * 已接受任务
     *
     * @param usrId
     * @param page
     * @param rows
     * @return
     */
    XslResult accectTask(Integer usrId, Integer page, Integer rows);

    /**
     * 雇主发送的未完成任务
     *
     * @param userId
     * @return
     */
    List<XslWaitTask> waitAccomplish(Integer userId);

    /**
     * 用户作为猎人接受的未完成任务
     *
     * @param userId
     * @return
     */
    List<XslWaitTask> waitHunterAccomplish(Integer userId);

    /**
     * 总的未完成成任务
     *
     * @param userId
     * @param page
     * @param rows
     * @return
     */
    XslResult coutWaitTask(Integer userId, Integer page, Integer rows);

    /**
     * 用户作为猎人完成任务
     *
     * @param userId
     * @return
     */
    List<XslWaitTask> doneTaskHunter(Integer userId);

    /**
     * 用户作为雇主发送任务完成
     *
     * @param userId
     * @return
     */
    List<XslWaitTask> doneTaskMaster(Integer userId);

    /**
     * 总的完成任务
     *
     * @param userId
     * @param page
     * @param rows
     * @return
     */
    XslResult coutDoneTask(Integer userId, Integer page, Integer rows);
}
