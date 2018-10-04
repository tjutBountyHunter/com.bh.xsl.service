package service.impl;

import com.sun.javafx.collections.MappingChange;
import mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.*;
import service.Collect;
import service.JsonUtils;
import service.PageDataResult;
import service.XslResult;

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
    public PageDataResult historyHunter(int userId, int pageno, int pagesize) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        List<XslHistoryHunter> list = xslHistoryHunterMap.selectByHunterid(map);
        PageDataResult<XslHistoryHunter> masterlevelPage = new PageDataResult<XslHistoryHunter>();
        List<XslHistoryHunter> list_change = new ArrayList<>();
        if (list.size() < pagesize) {
            pagesize = list.size();
        }
        for (int i = (pageno - 1) * pagesize; i < pagesize; i++) {
            list_change.add(list.get(i));
        }
        // 当前页码
        // 总的数据条数
        int totalsize = xslFindcollectMapper.selectCountByuserId(userId);
        // 最大页码（总页码）
        int totalno = 0;
        if (totalsize % pagesize == 0) {
            totalno = totalsize / pagesize;
        } else {
            totalno = totalsize / pagesize + 1;
        }
        masterlevelPage.setDatas(list_change);
        masterlevelPage.setTotalno(totalno);
        masterlevelPage.setTotalsize(totalsize);
        masterlevelPage.setPageno(pageno);
        return masterlevelPage;
    }

    /**
     * 查看收藏猎人
     *
     * @param userId
     * @return
     */
    @Override
    public PageDataResult findCollectHunter(int userId, Integer pageno, Integer pagesize) {
        List<XslHistoryHunter> list = xslFindcollectMapper.selectByuserId(userId);
        PageDataResult<XslHistoryHunter> masterlevelPage = new PageDataResult<XslHistoryHunter>();
        List<XslHistoryHunter> list_change = new ArrayList<>();
        if (list.size() < pagesize) {
            pagesize = list.size();
        }
        for (int i = (pageno - 1) * pagesize; i < pagesize; i++) {
            list_change.add(list.get(i));
        }
        // 当前页码
        // 总的数据条数
        int totalsize = xslFindcollectMapper.selectCountByuserId(userId);
        // 最大页码（总页码）
        int totalno = 0;
        if (totalsize % pagesize == 0) {
            totalno = totalsize / pagesize;
        } else {
            totalno = totalsize / pagesize + 1;
        }
        masterlevelPage.setDatas(list_change);
        masterlevelPage.setTotalno(totalno);
        masterlevelPage.setTotalsize(totalsize);
        masterlevelPage.setPageno(pageno);
        return masterlevelPage;
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
            List<XslHistoryHunter> list = xslFindcollectMapper.selectByuserId(userId);
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
