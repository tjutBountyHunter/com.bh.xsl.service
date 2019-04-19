package service.impl;

import mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.*;
import service.Collect;
import util.JsonUtils;
import util.XslResult;

import java.util.*;

/**
 * 收藏历史系列
 */
@Service
public class CollectImpl implements Collect {
    @Autowired
    private XslCollecthMapper xslCollecthMapper;
    @Autowired
    private XslHunterMapper xslHunterMapper;
    @Autowired
    private XslHistoryhMapper xslHistoryhMapper;
    @Autowired
    private XslHistoryHunterMap xslHistoryHunterMap;
    @Autowired
    private XslFindcollectMapper xslFindcollectMapper;
    @Autowired
    private XslCollecttMapper xslCollecttMapper;
    @Autowired
    private XslFindCollectTaskMapper xslFindCollectTaskMapper;
    @Autowired
    private XslHunterShopMapper xslHunterShopMapper;
    @Autowired
    private XslFileUrlMapper xslFileUrlMapper;
    /**
     * 收藏猎人
     *
     * @param hunterId
     * @param userId
     * @return
     */
    @Override
    public XslResult collectHunter(int hunterId, int userId) {

        try {
            XslCollecthExample xslCollecthExample = new XslCollecthExample();
            XslCollecthExample.Criteria criteria = xslCollecthExample.createCriteria();
            criteria.andUseridEqualTo(userId);
            List<XslCollecth> list = xslCollecthMapper.selectByExample(xslCollecthExample);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getHunterid().equals(hunterId)) {
                    return XslResult.ok("已经收藏");
                }
            }
            XslCollecth xslCollecth = new XslCollecth();
            Date date = new Date();
            xslCollecth.setCollectdate(date);
            xslCollecth.setHunterid(hunterId);
            xslCollecth.setUserid(userId);
            xslCollecthMapper.insert(xslCollecth);
            return XslResult.ok("收藏成功");
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }
    /**
     * 收藏任务
     *
     * @param userId
     * @param taskId
     * @return
     */
    @Override
    public XslResult collectTask(Integer userId, Integer taskId) {
        XslCollecttExample xslCollecttExample = new XslCollecttExample();
        XslCollecttExample.Criteria criteria = xslCollecttExample.createCriteria();
        criteria.andTaskidEqualTo(taskId);
        List<XslCollectt> list = xslCollecttMapper.selectByExample(xslCollecttExample);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUserid().equals(userId)) {
                return XslResult.ok("已经收藏");
            }
        }
        Date date = new Date();
        XslCollectt xslCollectt = new XslCollectt();
        xslCollectt.setCollectdate(date);
        xslCollectt.setUserid(userId);
        xslCollectt.setTaskid(taskId);
        try {
            xslCollecttMapper.insertSelective(xslCollectt);
            return XslResult.ok("收藏成功");
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
    @Override
    public XslResult findcollectThunter(Integer userId, Integer page, Integer rows) {
        try {
            List<XslHistoryHunter> list = xslFindcollectMapper.selectByuserId(userId);
            if (list.size() == 0 && list.isEmpty()) {
                return XslResult.ok("您还没有收藏猎人");
            } else {
                if (rows > list.size()) {
                    rows = list.size();
                }
                List<XslHistoryHunter> list_change = new ArrayList<>();
                List<Map> mapList = new ArrayList<>();
                for (int i = (page - 1); i < rows; i++) {
                    list_change.add(list.get(i));
                    mapList.add(findTagfile(list.get(i).getId()));
                }
                Map<String, Object> map = new HashMap<>(2);
                map.put("basicList", list_change);
                map.put("mapList", mapList);
                return XslResult.ok(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 查看收藏任务
     *
     * @param userId
     * @param page
     * @param rows
     * @return
     */
    @Override
    public XslResult findcollectTask(Integer userId, Integer page, Integer rows) {
        try {
            List<XslcollectTask> list = xslFindcollectMapper.selectByUserIdTask(userId);
            if (list.size() == 0 && list.isEmpty()) {
                return XslResult.ok("您还没有收藏猎人");
            } else {
                if (rows > list.size()) {
                    rows = list.size();
                }
                List<XslcollectTask> list_change = new ArrayList<>();
                List<Map> mapList = new ArrayList<>();
                for (int i = (page - 1); i < rows; i++) {
                    list_change.add(list.get(i));
                }
                return XslResult.ok(list_change);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    public Map<String, Object> findTagfile(Integer hunterId) {
        Map<String, Object> map = new HashMap<>();
        List<String> list = xslHunterShopMapper.selectHotTag(hunterId);
        String tag_json = JsonUtils.objectToJson(list);
        String imageUrl = xslFileUrlMapper.selectByhunterId(hunterId);
        map.put("hunterTag", tag_json);
        map.put("url", imageUrl);
        return map;
    }
}
