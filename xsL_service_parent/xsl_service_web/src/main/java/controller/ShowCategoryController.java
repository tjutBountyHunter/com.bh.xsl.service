package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.TaskInfoService;
import util.XslResult;

import javax.annotation.Resource;

/**
 * 展示分类猎人
 */
@Controller
@RequestMapping("/xsl/hunter")
public class ShowCategoryController {

    @Resource
    private TaskInfoService taskInfoService;

    @RequestMapping("/kindhunter")
    @ResponseBody
    public XslResult showCategoryHunter(String tagName, Integer type, Integer rows) {

        try {

            XslResult xslResult = taskInfoService.UpCategoryHunter(tagName, type, rows);
            return xslResult;
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }
}
