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
     * 全部历史猎人
     * @param rows
     * @param userId
     * @param hunterId
     * @return
     */
    @RequestMapping("/shop")
    @ResponseBody
    public XslResult hunterShop(Integer rows, Integer userId, Integer hunterId) {
        try {
            XslResult result = hunterShop.hunterShop(rows, userId, hunterId);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "猎人信息分页信息查询失败");
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
    public XslResult histotyHunterShop(Integer userId, Integer pageno, Integer pagesize) {
        try {
            PageDataResult json = collectl.historyHunter(userId, pageno, pagesize);
            return XslResult.build(200, "历史猎人信息分页查询成功", json);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(400, "历史猎人信息分页信息查询失败");
        }
    }

    /**
     * 单击猎人
     *
     * @param hunterId
     * @return
     */
    @RequestMapping("/onehunter")
    @ResponseBody
    public XslResult oneHunter(int hunterId) {
        XslResult xslResult = hunterShop.hunterOne(hunterId);
        return xslResult;
    }

    /**
     * 猎人市场二
     *
     * @param userId
     * @param rows
     * @return
     */
    @RequestMapping("/hothunter")
    @ResponseBody
    public XslResult hotHunter(int userId, int rows) {
        XslResult xslResult = null;
        try {
            xslResult = hunterShop.hunterShopCount(userId, rows);
            return xslResult;
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
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
    public XslResult collectHunter(int userId, int pageo, int rows) {
        try {
            PageDataResult<XslHistoryHunter> masterlevelPage = collectl.findCollectHunter(userId, pageo, rows);
            return XslResult.ok(masterlevelPage);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }
}
