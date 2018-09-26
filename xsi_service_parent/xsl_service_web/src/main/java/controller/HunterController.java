package controller;


import mapper.XslHistoryHunterMap;
import mapper.XslHistoryhMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.XslHistoryHunter;
import service.Collect;
import service.HunterShop;
import service.PageDataResult;
import service.XslResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 猎人URL
 *
 * @author 高山潍
 */
@Controller
@RequestMapping("/xsl/hunter")
public class HunterController {
    @Autowired
    private HunterShop hunterShop;
    @Autowired
    private XslHistoryHunterMap xslHistoryHunterMap;
    @Autowired
    private Collect collectl;

    /**
     * 猎人市场
     *
     * @param pageno
     * @param pagesize
     * @param userId
     * @return
     */
    @RequestMapping("/shop")
    @ResponseBody
    public XslResult hunterShop(Integer pageno, Integer pagesize, Integer userId) {
        try {
            PageDataResult result = hunterShop.hunterShop(pageno, pagesize, userId);
            return XslResult.build(200, "猎人信息分页查询成功", result);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(400, "猎人信息分页信息查询失败");
        }
    }

    /**
     * 查看历史猎人
     *
     * @param userId
     * @return
     */
    @RequestMapping("/history")
    @ResponseBody
    public XslResult hunterShop(Integer userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        try {
            String json = collectl.historyHunter(userId);
            return XslResult.build(200, "历史猎人信息分页查询成功", json);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(400, "历史猎人信息分页信息查询失败");
        }
    }

    /**
     * 收藏猎人
     *
     * @param hunterId
     * @param userId
     * @return
     */
    @RequestMapping("/collect")
    @ResponseBody
    public XslResult collectHunter(int hunterId, int userId) {
        XslResult xslResult = null;
        try {
            xslResult = collectl.collectHunter(hunterId, userId);
            return xslResult;
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 查看收藏猎人
     *
     * @param userId
     * @return
     */
    @RequestMapping("/findcollecth")
    @ResponseBody
    public XslResult collectHunter(int userId) {
        try {
            String json = collectl.findCollectHunter(userId);
            return XslResult.ok(json);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }
}
