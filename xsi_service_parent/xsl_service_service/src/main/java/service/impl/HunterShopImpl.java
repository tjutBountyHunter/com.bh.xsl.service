package service.impl;

import mapper.XslHistoryHunterMap;
import mapper.XslHunterShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.*;
import service.*;
import util.JsonUtils;
import util.RandomWriter;
import util.XslResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 猎人
 */
@Service
public class HunterShopImpl implements HunterShop {
    @Autowired
    private XslHistoryHunterMap xslHistoryHunterMap;
    @Autowired
    private XslHunterShopMapper xslHunterShopMapper;

    /**
     * 全部猎人
     * @param rows
     * @param userId
     * @return
     */
    @Override
    public XslResult hunterShop(Integer rows, Integer userId, Integer hunterId) {

        List<String> list_Tag = new ArrayList<>();
        Map<String, Object> map = new HashMap<>(3);
        Map<String, Object> map1 = new HashMap<>(3);
        map.put("rows", rows);
        map.put("userId", userId);
        map.put("hunterId", hunterId);
        try {
            List<XslAllHistoryHunter> list = new ArrayList<>();
            if (hunterId == null) {
                hunterId = 0;
                list = xslHunterShopMapper.selectByThree(map);
            } else {
                list = xslHunterShopMapper.selectByThreenew(map);
            }
            for (int i = 0; i < list.size(); i++) {
                List<String> listTag = xslHunterShopMapper.selectHotTag(list.get(0).getId());
                String json = JsonUtils.objectToJson(listTag);
                list_Tag.add(json);
            }
            if (list.size() == 0 && list.isEmpty()) {
                return XslResult.ok("您还有历史猎人，请去发布任务");
            } else {
                hunterId = list.get(list.size() - 1).getId();
                map1.put("hunterId", hunterId);
                map1.put("xslAllHistoryHunter", list);
                map1.put("list_Tag", list_Tag);
                return XslResult.ok(map1);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器错误");
        }

    }

    /**
     * 猎人市场
     *
     * @param useId
     * @param rows
     * @return
     */
    @Override
    public XslResult hunterShopCount(Integer useId, Integer rows) {
        XslResult xslResult = historyDefault(useId);
        Map<String, Object> map = new HashMap<>(2);
        XslResult xslResult1 = hotHunter(rows);
        if (xslResult.getStatus() == 200) {
            map.put("historyDefault", xslResult.getData());
            if (xslResult1.getStatus() == 200) {
                map.put("hotHunter", xslResult1.getData());
                return XslResult.ok(map);
            }
            return xslResult1;
        }
        return xslResult;
    }

    /**
     * 单击猎人
     *
     * @param hunterid
     * @return
     */
    @Override
    public XslResult hunterOne(Integer hunterid) {
        try {
            XslOneHunter xslHistoryHunter = xslHunterShopMapper.selectByhunterId(hunterid);
            List<String> list = xslHunterShopMapper.selectHotTag(hunterid);
            Map<String, Object> map = new HashMap<>(2);
            map.put("onehunter", xslHistoryHunter);
            map.put("hunterTag", list);
            return XslResult.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 默认猎人
     *
     * @return
     */
    public XslResult historyDefault(Integer useId) {
        try {
            List<XslHunterhistoryDefault> list = xslHunterShopMapper.selectBydefault(useId);
            if (list.size() == 0 && list.isEmpty()) {
                return XslResult.ok("您还有历史猎人，请去发布任务");
            }
            return XslResult.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 热门猎人
     *
     * @param rows
     * @return
     */
    public XslResult hotHunter(Integer rows) {
        List<String> list_tag = new ArrayList<>();
        List<XslHotHisory> list = new ArrayList<>();
        try {
            list = xslHunterShopMapper.selectByHot(rows);
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setEvaluate(new RandomWriter().randowEvalune());
                List<String> listTag = xslHunterShopMapper.selectHotTag(list.get(i).getId());
                String tag = JsonUtils.objectToJson(listTag);
                list_tag.add(tag);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put("hothunter", list);
        map.put("tag", list_tag);
        return XslResult.ok(map);
    }

}
