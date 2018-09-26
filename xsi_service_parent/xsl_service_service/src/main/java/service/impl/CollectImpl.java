package service.impl;

import com.sun.javafx.collections.MappingChange;
import mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.*;
import service.Collect;
import service.JsonUtils;
import service.XslResult;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 收藏猎人
     *
     * @param hunterId
     * @param userId
     * @return
     */
    @Override
    public XslResult collectHunter(int hunterId, int userId) {
        XslCollecth xslCollecth = new XslCollecth();
        Date date = new Date();
        xslCollecth.setCollectdate(date);
        xslCollecth.setHunterid(hunterId);
        xslCollecth.setUserid(userId);
        xslCollecthMapper.insert(xslCollecth);
        XslHunterExample xslHunterExample = new XslHunterExample();
        XslHunterExample.Criteria criteria = xslHunterExample.createCriteria();
        criteria.andIdEqualTo(hunterId);
        List<XslHunter> list = xslHunterMapper.selectByExample(xslHunterExample);
        XslHunter xslHunter = list.get(0);
        return XslResult.ok(xslHunter);
    }

    /**
     * 历史猎人
     *
     * @param userId
     * @return
     */
    @Override
    public String historyHunter(int userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        List<XslHistoryHunter> list = xslHistoryHunterMap.selectByHunterid(map);
        String json = JsonUtils.objectToJson(list);
        return json;
    }

    /**
     * 查看收藏猎人
     *
     * @param userId
     * @return
     */
    @Override
    public String findCollectHunter(int userId) {
        List<XslHistoryHunter> list = xslFindcollectMapper.selectByuserId(userId);
        String json = JsonUtils.objectToJson(list);
        return json;
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
        Date date = new Date();
        XslCollectt xslCollectt = new XslCollectt();
        xslCollectt.setCollectdate(date);
        xslCollectt.setUserid(userId);
        xslCollectt.setTaskid(taskId);
        try {
            xslCollecttMapper.insertSelective(xslCollectt);
            return XslResult.ok();
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
    public String findcollectTask(Integer userId) {
        try {
            List<XslFindCollectTask> list = xslFindCollectTaskMapper.selectByuserId(userId);
            if (list.size() == 0 && list.isEmpty()) {
                return "您还没有收藏猎人";
            } else {
                String json = JsonUtils.objectToJson(list);
                return json;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "服务器异常";
        }
    }
}
