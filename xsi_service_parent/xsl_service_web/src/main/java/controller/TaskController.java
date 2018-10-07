package controller;

import com.sun.org.glassfish.external.probe.provider.annotations.ProbeParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.*;

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
    @Autowired
    private UpTaskService upTaskService;
    @Autowired
    private SupplementDataService supplementDataService;

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
     * @return
     */
    @RequestMapping("/push")
    @ResponseBody
    public XslResult accertdata(@ProbeParam("userId") String json) {
        try {
            XslResult xslResult = supplementDataService.SupplementTaskData(json);
            return xslResult;
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 任务接收
     * @param hunterId
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/accept", method = RequestMethod.GET)
    @ResponseBody
    public XslResult acceptTask(@RequestParam("hunterId") int hunterId, @ProbeParam("taskId") String taskId) {
        XslResult xslResult = null;
        try {
            xslResult = taskAccept.acceptTask(hunterId, taskId);
            return xslResult;
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 任务接收成功
     * @param hunterId
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/decidedaccept", method = RequestMethod.GET)
    @ResponseBody
    public XslResult decidedTask(@RequestParam("hunterId") int hunterId, @ProbeParam("taskId") String taskId) {
        XslResult xslResult = null;
        try {
            xslResult = taskAccept.decidedTask(hunterId, taskId);
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
            String json = taskAccept.timeDate();
            return XslResult.ok(json);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 获取猎人在接任务的时候的时间
     * @param hunterId
     * @param taskId
     * @return
     */
    @RequestMapping("/olddatetime")
    @ResponseBody
    public XslResult oldDatetime(Integer hunterId, String taskId) {
        try {
            String jsonTime = taskAccept.oldTime(hunterId, taskId);
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

    /**
     * 查找收藏任务
     *
     * @param userId
     * @return
     */
    @RequestMapping("/findcollecttask")
    @ResponseBody
    public XslResult findCollectTask(Integer userId, Integer page, Integer rows) {
        try {
            XslResult xslResult = collect.findcollectTask(userId, page, rows);
            return xslResult;
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 发送任务
     *
     * @param json
     * @return
     */
    @RequestMapping("/sendtask")
    @ResponseBody
    public XslResult sendTask(String json) {
        XslResult xslResult = upTaskService.UpuseTask(json);
        return xslResult;
    }

    /**
     * 自定义标签
     *
     * @param json
     * @return
     */
    @RequestMapping("/makeselftag")
    @ResponseBody
    public XslResult makeTag(String json) {
        XslResult xslResult = supplementDataService.SupplementTagWrite(json);
        return xslResult;
    }
//    @RequestMapping("/donetask")
////    @ResponseBody
////    public
}
