package service;

public interface TaskStatefind {
    /**
     * 雇主发送的未完成任务
     *
     * @param userId
     * @return
     */
    String waitAccomplish(Integer userId);

    /**
     * 用户作为猎人接受的未完成任务
     *
     * @param userId
     * @return
     */
    String waitHunterAccomplish(Integer userId);

    /**
     * 总的未完成成任务
     *
     * @param userId
     * @param page
     * @param rows
     * @return
     */
    String coutWaitTask(Integer userId, Integer page, Integer rows);

    /**
     * 用户作为猎人完成任务
     *
     * @param userId
     * @return
     */
    String doneTaskHunter(Integer userId);

    /**
     * 用户作为雇主发送任务完成
     *
     * @param userId
     * @return
     */
    String doneTaskMaster(Integer userId);

    /**
     * 总的完成任务
     *
     * @param userId
     * @param page
     * @param rows
     * @return
     */
    String coutDoneTask(Integer userId, Integer page, Integer rows);
}
