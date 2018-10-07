package service;

import pojo.XslTask;

import java.util.List;

public interface ViewTaskStateService {

    /**
     * 通过发送者ID得到已经发送的任务
     *
     * @param sendId
     * @return
     */
    XslResult getIssuedTaskBySendId(Integer sendId);

    /**
     * 通过发送者ID得到已经接受的任务和待完成的任务
     *
     * @param sendId
     * @return
     */
    XslResult getAccessTaskBySendId(Integer sendId);

    /**
     * 通过发送者ID得到已经完成的任务
     *
     * @param sendId
     * @return
     */
    XslResult getcompleteTaskBySendId(Integer sendId);
}
