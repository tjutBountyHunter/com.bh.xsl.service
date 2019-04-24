package controller;


import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.SerchDubbo;
import util.XslResult;

@Controller
@RequestMapping("/xsl/dubbo")
public class SearchDubbo {
    @Autowired
    private SerchDubbo serchDubbo;

    /**
     * 搜索框查找任务
     *
     * @param keyword
     * @param page
     * @param rows
     * @param sort_type
     * @return
     */
    @RequestMapping("/searchitem")
    @ResponseBody
    public XslResult searchDubbo_item(@Param("keyword") String keyword, @Param("page") int page, @Param("rows") int rows, @Param("sort_type") int sort_type) {
        XslResult xslResult = serchDubbo.searchDubbo_item(keyword, page, rows, sort_type);
        return xslResult;
    }

    @RequestMapping("/searchhunter")
    @ResponseBody
    public XslResult searchDubbo_hunter(@Param("keyword") String keyword, @Param("page") int page, @Param("rows") int rows, @Param("sort_type") int sort_type) {
        XslResult xslResult = serchDubbo.searchDubbo_hunter(keyword, page, rows, sort_type);
        return xslResult;
    }
}
