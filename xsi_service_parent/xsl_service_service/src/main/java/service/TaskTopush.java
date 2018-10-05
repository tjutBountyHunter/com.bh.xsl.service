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
    XslResult accertdata(String xslTask);

    /**
     * 点击任务大厅展示
     * @param flagid
     * @param type
     * @param rows
     * @return
     * @throws ParseException
     */
    XslResult searchPage(Integer flagid, Integer type, Integer rows) throws ParseException;
}
