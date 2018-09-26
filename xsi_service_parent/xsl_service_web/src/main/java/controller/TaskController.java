package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.XslTag;
import pojo.XslTask;
import service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 任务推送、展示
 *
 * @author 高山潍
 */
@Controller
@RequestMapping("/xsl/task")
public class TaskController {
    @Autowired
    private TaskTopush taskTopush;
    @Autowired
    private TaskAccept taskAccept;
    @Autowired
    private Collect collect;

    /**
     * 任务分类
     *
     * @return
     */
    @RequestMapping("/class")
    @ResponseBody
    public XslResult taskClassied() {
        try {
            String json = taskTopush.taskClassied();
            return XslResult.ok(json);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 标签分类
     *
     * @return
     */
    @RequestMapping("/tag")
    @ResponseBody
    public XslResult tagClassied() {
        try {
            String json = taskTopush.tagClassied();
            return XslResult.ok(json);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器错误");
        }
    }

    /**
     * 任务推送
     *
     * @param xslTask
     * @param xslTag
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/push")
    @ResponseBody
    public XslResult accertdata(XslTask xslTask, XslTag xslTag, HttpServletRequest request, HttpServletResponse response) {
        try {
            String json = taskTopush.accertdata(xslTask, xslTag, request, response);
            return XslResult.format(json);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 分页默认查询
     *
     * @param queryText
     * @param pageno
     * @param pagesize
     * @return
     */
    @RequestMapping("/pageQuery")
    @ResponseBody
    public XslResult pageQuery(String queryText, Integer pageno, Integer pagesize) {
        try {
            PageDataResult result = taskTopush.searchPage(queryText, pageno, pagesize);
            return XslResult.build(200, "任务信息分页查询成功", result);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(400, "任务信息分页信息查询失败");
        }
    }

    /**
     * 分页直接查询
     *
     * @param pageno
     * @param pagesize
     * @return
     */
    @RequestMapping("/pageQueryC")
    @ResponseBody
    public XslResult pageQuery(Integer pageno, Integer pagesize) {
        try {
            PageDataResult result = taskTopush.searchPage(pageno, pagesize);
            return XslResult.build(200, "任务信息分页查询成功", result);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(400, "任务信息分页信息查询失败");
        }
    }

    /**
     * 任务接收
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "/accept", method = RequestMethod.GET)
    @ResponseBody
    public XslResult acceptTask(@RequestParam("json") String json) {
        XslResult xslResult = null;
        try {
            xslResult = taskAccept.acceptTask(json);
            return xslResult;
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 任务接收成功
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "/decidedaccept", method = RequestMethod.GET)
    @ResponseBody
    public XslResult decidedTask(@RequestParam("json") String json) {
        XslResult xslResult = null;
        try {
            xslResult = taskAccept.decidedTask(json);
            return xslResult;
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 随时传送时间
     *
     * @return
     */
    @RequestMapping("/nowdatetime")
    @ResponseBody
    public XslResult nowDatetime() {
        try {
            Date date = taskAccept.timeDate();
            String json = JsonUtils.objectToJson(date);
            return XslResult.ok(json);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 获取猎人在接任务的时候的时间
     *
     * @param json
     * @return
     */
    @RequestMapping("/olddatetime")
    @ResponseBody
    public XslResult oldDatetime(String json) {
        try {
            String jsonTime = taskAccept.oldTime(json);
            return XslResult.ok(jsonTime);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 收藏任务
     *
     * @param userId
     * @param taskId
     * @return
     */
    @RequestMapping("/collecttask")
    @ResponseBody
    public XslResult collectTask(Integer userId, Integer taskId) {
        try {
            XslResult xslResult = collect.collectTask(userId, taskId);
            return xslResult;
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    @RequestMapping("/findcollecttask")
    @ResponseBody
    public XslResult findCollectTask(Integer userId) {
        try {
            String json = collect.findcollectTask(userId);
            return XslResult.ok(json);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }
}
