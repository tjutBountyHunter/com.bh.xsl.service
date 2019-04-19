package service;

import util.XslResult;

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
    XslResult findcollectThunter(Integer userId, Integer page, Integer rows);

    XslResult findcollectTask(Integer userId, Integer page, Integer rows);
}
