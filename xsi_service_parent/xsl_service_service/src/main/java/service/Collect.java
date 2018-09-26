package service;

import mapper.XslHistoryHunterMap;
import pojo.XslHistoryHunter;

import java.util.List;

public interface Collect {
    /**
     * 收藏猎人
     *
     * @param hunterId
     * @param userId
     * @return
     */
    XslResult collectHunter(int hunterId, int userId);

    /**
     * 历史猎人
     *
     * @param userId
     * @return
     */
    String historyHunter(int userId);

    /**
     * 查看收藏猎人
     *
     * @param userId
     * @return
     */
    String findCollectHunter(int userId);

    /**
     * 收藏任务
     *
     * @param userId
     * @param taskId
     * @return
     */
    XslResult collectTask(Integer userId, Integer taskId);

    /**
     * 查看收藏任务
     *
     * @param userId
     * @return
     */
    String findcollectTask(Integer userId);
}
