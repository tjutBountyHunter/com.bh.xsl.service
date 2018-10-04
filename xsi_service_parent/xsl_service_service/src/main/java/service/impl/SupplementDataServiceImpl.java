package service.impl;

import mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.*;
import service.*;

import javax.xml.bind.util.JAXBSource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SupplementDataServiceImpl implements SupplementDataService {

    @Autowired
    private XslTaskMapper xslTaskMapper;

    @Autowired
    private XslDateTaskMapper xslDateTaskMapperr;

    @Autowired
    private XslTaskTagMapper xslTaskTagMapper;
    @Autowired
    private XslTagMapper xslTagMapper;
    @Autowired
    private XslUpdateTagMapper xslUpdateTagMapper;

    /**
     * 添加任务信息
     *
     * @param json
     */
    @Override
    public XslResult SupplementTaskData(String json) {
        XslDateTask xslDateTask = JsonUtils.jsonToPojo(json, XslDateTask.class);
        try {
            XslTask xslTask = new XslTask();
            xslTask.setCid(xslDateTaskMapperr.getCidByName(xslDateTask.getCategoryname()));
            System.out.println(xslDateTaskMapperr.getCidByName(xslDateTask.getCategoryname()));
            xslTask.setSendid(xslDateTask.getSendId());
            xslTask.setTaskid(UuidTaskId.getUUID());
            xslTask.setDescr(xslDateTask.getDescr());
            xslTask.setMoney(xslDateTask.getMoney());
            xslTask.setState((byte) 0);
            xslTask.setCreatedate(new Date());
            xslTask.setUpdatedate(new Date());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dateTime = formatter.parse(xslDateTask.getDeadline());
            xslTask.setDeadline(dateTime);
            searchTaskMQ searchTaskMQ = new searchTaskMQImpl();
            searchTaskMQ.addTaskJson(JsonUtils.objectToJson(xslTask));
            xslTaskMapper.insertSelective(xslTask);
            return XslResult.ok(xslTask.getTaskid());
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 更新标签使用次数
     *
     * @param json
     */
    @Override
    public XslResult SupplementTagData(String json) {
        XslDateTask xslDateTask = JsonUtils.jsonToPojo(json, XslDateTask.class);
        XslResult xslResult = SupplementTaskData(json);
        if (xslResult.getStatus() == 200) {
            try {
                Integer useNum = xslDateTaskMapperr.getUseNum(xslDateTask.getTagname());
                useNum++;
                HashMap map = new HashMap();
                map.put("useNum", useNum);
                map.put("name", xslDateTask.getTagname());
                xslDateTaskMapperr.UpdateTaskNumByName(map);
                return XslResult.ok(xslResult.getData());
            } catch (Exception e) {
                e.printStackTrace();
                return XslResult.build(500, "服务器异常");
            }
        } else {
            return xslResult;
        }
    }

    /**
     * 更新任务种类使用次数
     *
     * @param json
     */
    @Override
    public XslResult SupplementCategoryData(String json) {
        XslDateTask xslDateTask = JsonUtils.jsonToPojo(json, XslDateTask.class);
        XslResult xslResult = SupplementTagData(json);
        if (xslResult.getStatus() == 200) {
            try {
                Integer taskNum = xslDateTaskMapperr.getTaskNumByName(xslDateTask.getCategoryname());
                taskNum++;
                HashMap map = new HashMap();
                map.put("taskNum", taskNum);
                map.put("name", xslDateTask.getCategoryname());
                xslDateTaskMapperr.UpdateTaskNumByName(map);
                return XslResult.ok(xslResult.getData());
            } catch (Exception e) {
                e.printStackTrace();
                return XslResult.build(500, "服务器异常");
            }
        } else {
            return xslResult;
        }
    }

    /**
     * 添加任务标签关系数据
     *
     * @param json
     * @return
     */
    @Override
    public XslResult SupplementTaskTagData(String json) {
        XslDateTask xslDateTask = JsonUtils.jsonToPojo(json, XslDateTask.class);
        XslResult xslResult = SupplementCategoryData(json);
        if (xslResult.getStatus() == 200) {
            try {
                XslTaskTag xslTaskTag = new XslTaskTag();
                Integer taskId = xslDateTaskMapperr.getTaskIdByName((String) xslResult.getData());
                Integer tagId = xslDateTaskMapperr.getTagIdByName(xslDateTask.getTagname());
                System.out.println(tagId);
                xslTaskTag.setTaskid(taskId);
                xslTaskTag.setTagid(tagId);
                xslTaskTag.setCreatedate(new Date());
                xslTaskTagMapper.insert(xslTaskTag);
                return XslResult.ok(xslResult.getData());
            } catch (Exception e) {
                e.printStackTrace();
                return XslResult.build(500, "服务器异常");
            }
        } else {
            return xslResult;
        }
    }

    /**
     * 自定义标签
     *
     * @param json
     * @return
     */
    @Override
    public XslResult SupplementTagWrite(String json) {
        try {
            XslTag xslTag = new XslTag();
            XslTagExample xslTagExample = new XslTagExample();
            XslTagExample.Criteria criteria = xslTagExample.createCriteria();
            json = new String(json.getBytes("iso-8859-1"), "utf-8");
            criteria.andNameEqualTo(json);
            List<XslTag> list = xslTagMapper.selectByExample(xslTagExample);
            if (list.size() == 0 & list.isEmpty()) {
                xslTag.setName(json);
                xslTag.setCreatedate(new Date());
                xslTag.setUsenum((short) 1);
                xslTagMapper.insertSelective(xslTag);
            } else {
                int num = list.get(0).getUsenum();
                num++;
                xslTag.setUsenum((short) num);
                Map<String, Object> map = new HashMap<>();
                map.put("name", json);
                map.put("useNum", num);
                xslUpdateTagMapper.updateTag(map);
            }
            return XslResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }
}
