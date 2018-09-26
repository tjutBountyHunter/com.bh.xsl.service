package service.impl;

import mapper.XslHistoryHunterMap;
import mapper.XslHunterShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.XslHistoryHunter;
import service.Collect;
import service.HunterShop;
import service.JsonUtils;
import service.PageDataResult;

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
     * 猎人市场
     *
     * @param pageno
     * @param pagesize
     * @return
     */
    @Override
    public PageDataResult hunterShop(Integer pageno, Integer pagesize, Integer userId) {
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("userId", userId);
        List<XslHistoryHunter> historyList = xslHistoryHunterMap.selectByHunterid(map1);
        int length = historyList.size();
        List<XslHistoryHunter> historyList_random = null;
        int pagesize_random;
        if (length > 3) {
            for (int i = 0; i < 3; i++) {
                int random = (int) (1 + Math.random() * length);
                historyList_random.add(historyList.get(random));
            }
            pagesize_random = pagesize - 3;
        } else {
            pagesize_random = pagesize - length;
        }
        //取到分页信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", (pageno - 1) * pagesize);
        map.put("size", pagesize_random);
        List<XslHistoryHunter> masterlevelList = xslHunterShopMapper.selectByThree(map);
        // 当前页码
        // 总的数据条数
        int totalsize = xslHunterShopMapper.getXslHunterCount(map);
        // 最大页码（总页码）
        int totalno = 0;
        if (totalsize % pagesize == 0) {
            totalno = totalsize / pagesize;
        } else {
            totalno = totalsize / pagesize + 1;
        }
        if (historyList.size() > 3) {
            masterlevelList.addAll(historyList_random);
        } else {
            masterlevelList.addAll(historyList);
        }
        List<XslHistoryHunter> xslHistoryHunters = new ArrayList<>();
        xslHistoryHunters.addAll(masterlevelList);
        PageDataResult<XslHistoryHunter> masterlevelPage = new PageDataResult<XslHistoryHunter>();
        masterlevelPage.setDatas(xslHistoryHunters);
        masterlevelPage.setTotalno(totalno);
        masterlevelPage.setTotalsize(totalsize);
        masterlevelPage.setPageno(pageno);
        return masterlevelPage;
    }
}
