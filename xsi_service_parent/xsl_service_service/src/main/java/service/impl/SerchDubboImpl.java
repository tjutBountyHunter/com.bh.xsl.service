package service.impl;

import com.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import service.*;

import java.util.List;

/**
 * @author 高山潍
 */
@Service
public class SerchDubboImpl implements SerchDubbo {
    @Override
    public XslResult searchDubbo(String keyword, int page, int rows, int sort_type) {
        try {
            keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
            System.out.println(keyword);
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                    "file:spring/consumer.xml");
            context.start();
            // 获取远程服务代理
            SearchService searchService = (SearchService) context.getBean("searchService");
            SearchResult searchResult = searchService.search(keyword, page, rows, sort_type);
            List<SearchItem> list = searchResult.getItemList();
            if (list.size() == 0 && list.isEmpty()) {
                return XslResult.ok();
            } else {
                String json = JsonUtils.objectToJson(list);
                return XslResult.ok(searchResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    @Override
    public XslResult searchDubboExperience(Integer Lever) {

        return null;
    }
}
