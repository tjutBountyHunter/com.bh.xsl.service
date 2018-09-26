package service;

import pojo.XslTag;
import pojo.XslTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * @param xslTag
     * @param request
     * @param response
     * @return
     */
    String accertdata(XslTask xslTask, XslTag xslTag, HttpServletRequest request, HttpServletResponse response);

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
}
