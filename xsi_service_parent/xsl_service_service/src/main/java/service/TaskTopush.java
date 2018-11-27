package service;

import java.text.ParseException;

public interface TaskTopush {
    /**
     * 任务种类
     * @return
     */
    String taskClassied();

    /**
     * 标签种类
     *
     * @return
     */
    String tagClassied();

    /**
     * 任务和任务种类初始化
     *
     * @param xslTask
     * @return
     */
    XslResult accertdata(String xslTask);

    /**
     * 点击任务大厅展示
     * @param flagid
     * @param type
     * @param rows
     * @return
     * @throws ParseException
     */
    XslResult searchPage(Integer flagid, Integer type, int rows) throws ParseException;

//    XslResult sendTask();
}
