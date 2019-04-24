package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.MainPageMessage;
import service.TaskStatefind;
import util.XslResult;

@Controller
@RequestMapping("/xsl/mainpage")
public class MainPage {
    @Autowired
    private MainPageMessage mainPageMessage;
    @Autowired
    private TaskStatefind taskStatefind;

    @RequestMapping("/page")
    @ResponseBody
    public XslResult mainPageFind(Integer userId) {
        XslResult xslResult = mainPageMessage.mainpageMessage(userId);
        return xslResult;
    }

    @RequestMapping("/send")
    @ResponseBody
    public XslResult sendTask(Integer userId, Integer page, Integer rows) {
        XslResult xslResult = taskStatefind.sendTask(userId, page, rows);
        return xslResult;
    }

    @RequestMapping("/accept")
    @ResponseBody
    public XslResult accectTask(Integer userId, Integer page, Integer rows) {
        XslResult xslResult = taskStatefind.accectTask(userId, page, rows);
        return xslResult;
    }

    @RequestMapping("/nodone")
    @ResponseBody
    public XslResult coutWaitTask(Integer userId, Integer page, Integer rows) {
        System.out.println(userId);
        XslResult xslResult = taskStatefind.coutWaitTask(userId, page, rows);
        return xslResult;
    }

    @RequestMapping("/done")
    @ResponseBody
    public XslResult coutDoneTask(Integer userId, Integer page, Integer rows) {
        XslResult xslResult = taskStatefind.coutDoneTask(userId, page, rows);
        return xslResult;
    }
}
