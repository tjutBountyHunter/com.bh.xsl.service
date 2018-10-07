package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ViewTaskStateService;
import service.XslResult;

import javax.annotation.Resource;

/**
 * 查看任务状态Controller
 */
@Controller
@RequestMapping("/xsl/vivetask")
public class ViewTaskStateController {

    @Resource
    private ViewTaskStateService viewTaskStateService;

    /**
     * 通过发送者ID得到已经发送的任务
     *
     * @param sendId
     * @return
     */
    @RequestMapping("/issuse")
    @ResponseBody
    public XslResult getIssuedTaskBySendId(Integer sendId) {
        XslResult xslResult = viewTaskStateService.getIssuedTaskBySendId(sendId);
        return xslResult;
    }

    /**
     * 通过发送者ID得到已经接受的任务和待完成的任务
     *
     * @param sendId
     * @return
     */
    @RequestMapping("/access")
    @ResponseBody
    public XslResult getAccessTaskBySendId(Integer sendId) {
        XslResult xslResult = viewTaskStateService.getAccessTaskBySendId(sendId);
        return xslResult;
    }

    /**
     * 通过发送者ID得到已经完成的任务
     *
     * @param sendId
     * @return
     */
    @RequestMapping("/complete")
    @ResponseBody
    public XslResult getcompleteTaskBySendId(Integer sendId) {
        XslResult xslResult = viewTaskStateService.getcompleteTaskBySendId(sendId);
        return xslResult;
    }
}
