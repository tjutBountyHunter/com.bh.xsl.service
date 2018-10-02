package service;

import java.text.ParseException;

public interface TaskTopush {
    /**
     * 种类
     *
     * @return
     */
    String taskClassied();

    String tagClassied();

    /**
     * 任务和任务种类初始化
     *
     * @param xslTask
     * @return
     */
    String accertdata(String xslTask);

    /**
     * 分页查询
     *
     * @param queryText
     * @param pageno
     * @param pagesize
     * @return
     */
    PageDataResult searchPage(String queryText, Integer pageno, Integer pagesize);

    /**
     * 点击任务大厅展示
     *
     * @param pageno
     * @param pagesize
     * @return
     */
    PageDataResult searchPage(Integer pageno, Integer pagesize) throws ParseException;

    String deleteTaskMQ(Integer taskId);
}
