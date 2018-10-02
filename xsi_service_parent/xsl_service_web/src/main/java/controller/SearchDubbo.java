package controller;

import com.sun.org.glassfish.external.probe.provider.annotations.ProbeParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.SerchDubbo;
import service.XslResult;

@Controller
@RequestMapping("/xsl/dubbo")
public class SearchDubbo {
    @Autowired
    private SerchDubbo serchDubbo;

    @RequestMapping("/search")
    @ResponseBody
    public XslResult searchDubbo(@ProbeParam("keyword") String keyword, @ProbeParam("page") int page, @ProbeParam("rows") int rows, @ProbeParam("sort_type") int sort_type) {
        try {
            XslResult xslResult = serchDubbo.searchDubbo(keyword, page, rows, sort_type);
            return xslResult;
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

}
