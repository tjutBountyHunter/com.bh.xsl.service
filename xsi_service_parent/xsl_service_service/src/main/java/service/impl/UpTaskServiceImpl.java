package service.impl;

import mapper.XslHuntershowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.XslOneHunter;
import service.TaskTopush;
import service.UpTaskService;
import service.SupplementDataService;
import service.XslResult;

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
    private XslHuntershowMapper xslHuntershowMapper;

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

    @Override
    public XslResult UpuseTask(String json) {
        try {
            json = new String(json.getBytes("iso-8859-1"), "utf-8");
            XslResult xslResult = null;
            xslResult = supplementDataService.SupplementTaskData(json);
            return xslResult;
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

}
