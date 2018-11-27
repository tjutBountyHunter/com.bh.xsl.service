package service.impl;

import mapper.XslHunterMapper;
import mapper.XslHunterShopMapper;
import mapper.XslHuntershowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.XslHunterExample;
import pojo.XslOneHunter;
import service.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UpTaskServiceImpl implements UpTaskService {

    @Autowired
    private SupplementDataService supplementDataService;
    @Autowired
    private TaskTopush taskTopush;
    @Autowired
    private HunterRecommend hunterRecommend;
    @Autowired
    private XslHuntershowMapper xslHuntershowMapper;
    @Autowired
    private XslHunterShopMapper xslHunterShopMapper;
    /**
     * 分页展示分类猎人
     *
     * @param tagName 任务id
     * @param type    滑动类型
     * @param rows    所需要的条数
     * @return
     */
    @Override
    public XslResult UpCategoryHunter(String tagName, Integer type, Integer rows) {
        try {
            tagName = new String(tagName.getBytes("iso-8859-1"), "utf-8");
            Map<String, Object> map = new HashMap<>(2);
            map.put("tagName", tagName);
            map.put("rows", rows);
            List<XslOneHunter> oneHunterList = null;
            if (type == 0) {
                oneHunterList = xslHuntershowMapper.getXslHunterListfirst(map);
                return XslResult.ok(oneHunterList);
            } else if (type == 1) {
                oneHunterList = xslHuntershowMapper.getXslHunterOld(map);
                return XslResult.ok(oneHunterList);
            } else {
                oneHunterList = xslHuntershowMapper.getXslHunterNew(map);
                return XslResult.ok(oneHunterList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 猎人推优
     *
     * @param task_id
     * @return
     */
    @Override
    public XslResult hunterDire(int task_id) {
        try {
            int[] hunterid = hunterRecommend.recommend(task_id);
            List<XslOneHunter> list = new ArrayList<>();
            for (int i = 0; i < hunterid.length; i++) {
                XslOneHunter xslOneHunter = new XslOneHunter();
                Integer hunterId = hunterid[i];
                xslOneHunter = xslHunterShopMapper.selectByhunterId(hunterId);
                list.add(xslOneHunter);
            }
            return XslResult.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    @Override
    public XslResult UpuseTask(String json) {
        try {
            System.out.println(json);
            XslResult xslResult = null;
            xslResult = supplementDataService.SupplementTaskData(json);
            return xslResult;
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }
}
