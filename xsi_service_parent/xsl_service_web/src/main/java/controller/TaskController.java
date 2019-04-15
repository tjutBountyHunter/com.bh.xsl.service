package controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.*;

import java.text.ParseException;
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
    @Autowired
    private TaskStatefind taskStatefind;
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
     * 查看已发任务
     *
     * @param usrId
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/allaread")
    @ResponseBody
    public XslResult taskAllaread(Integer usrId, Integer page, Integer rows) {
        XslResult xslResult = null;
        xslResult = taskStatefind.sendTask(usrId, page, rows);
        return xslResult;
    }

    /**
     * 已接任务
     *
     * @param usrId
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/accectAll")
    @ResponseBody
    public XslResult taskaccectAll(Integer usrId, Integer page, Integer rows) {
        XslResult xslResult = null;
        xslResult = taskStatefind.accectTask(usrId, page, rows);
        return xslResult;
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
    public XslResult accertdata(@Param("json") String json) {
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
    public XslResult acceptTask(@RequestParam("hunterId") int hunterId, @Param("taskId") String taskId) {
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
    public XslResult decidedTask(@RequestParam("hunterId") int hunterId, @Param("taskId") String taskId) {
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
    @RequestMapping(value = "/sendtask", method = RequestMethod.POST)
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

    /**
     * 任务大厅
     *
     * @param flagid
     * @param type
     * @param rows
     * @return
     */
    @RequestMapping("/pageQueryC")
    @ResponseBody
    public XslResult findTask(Integer flagid, Integer type, int rows) {
        XslResult xslResult = null;
        try {
            xslResult = taskTopush.searchPage(flagid, type, rows);
        } catch (ParseException e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
        return xslResult;
    }

    /**
     * 猎人推优
     *
     * @param task_id
     * @return
     */
    @RequestMapping("/hunterChange")
    @ResponseBody
    public XslResult hunterChange(int task_id) {
        XslResult xslResult = null;
        xslResult = upTaskService.hunterDire(task_id);
        return xslResult;
    }

}
