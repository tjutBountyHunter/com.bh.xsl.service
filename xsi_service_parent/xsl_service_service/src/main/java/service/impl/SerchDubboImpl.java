package service.impl;

import com.search.service.SearchService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import pojo.SearchItem;
import pojo.SearchResult;
import service.*;

import java.util.List;

/**
 * @author 高山潍
 */
@Service
public class SerchDubboImpl implements SerchDubbo {


    /**
     * dubbo工具
     *
     * @return
     */
    public SearchService xslDubboTools() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "spring/consumer.xml");
        context.start();
        // 获取远程服务代理
        SearchService searchService = (SearchService) context.getBean("searchService");
        return searchService;
    }

    /**
     * 任务大厅搜索框
     *
     * @param keyword
     * @param page
     * @param rows
     * @param sort_type
     * @return
     */
    @Override
    public XslResult searchDubbo_item(String keyword, int page, int rows, int sort_type) {
        try {
            keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
            SearchService searchService = xslDubboTools();
            SearchResult searchResult = searchService.search_item(keyword, page, rows, sort_type);
            List<SearchItem> list = searchResult.getItemList();
            if (list.size() == 0 && list.isEmpty()) {
                return XslResult.ok("没有此任务类型");
            } else {
                String json = JsonUtils.objectToJson(list);
                return XslResult.ok(searchResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 猎人市场搜索框
     * @param keyword
     * @param page
     * @param rows
     * @param sort_type
     * @return
     */
    @Override
    public XslResult searchDubbo_hunter(String keyword, int page, int rows, int sort_type) {
        try {
            keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
            SearchService searchService = xslDubboTools();
            SearchResult searchResult = searchService.search_hunter(keyword, page, rows, sort_type);
            List<SearchItem> list = searchResult.getItemList();
            if (list.size() == 0 && list.isEmpty()) {
                return XslResult.ok("没有达到您要求的猎人");
            } else {
                String json = JsonUtils.objectToJson(list);
                return XslResult.ok(searchResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }
}
