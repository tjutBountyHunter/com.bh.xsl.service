package service.impl;

import mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.*;
import service.*;

import javax.xml.bind.util.JAXBSource;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    private XslTaskCategoryMapper xslTaskCategoryMapper;

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
            XslTaskExample xslTaskExample = new XslTaskExample();
            XslTaskExample.Criteria criteria = xslTaskExample.createCriteria();
            criteria.andTaskidEqualTo(xslTask.getTaskid());
            List<XslTask> list = xslTaskMapper.selectByExample(xslTaskExample);
            XslResult xslResult = SupplementTaskTagData(json, list.get(0).getId());
            return xslResult;
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 更新任务种类使用次数
     * @param json
     */
    @Override
    public XslResult SupplementCategoryData(String json) {
        try {
            json = new String(json.getBytes("iso-8859-1"), "utf-8");
            XslTaskCategoryExample xslTaskCategoryExample = new XslTaskCategoryExample();
            XslTaskCategoryExample.Criteria criteria = xslTaskCategoryExample.createCriteria();
            criteria.andNameEqualTo(json);
            List<XslTaskCategory> list = xslTaskCategoryMapper.selectByExample(xslTaskCategoryExample);
            XslTaskCategory xslTaskCategory = new XslTaskCategory();
            xslTaskCategory.setCreatedate(new Date());
            xslTaskCategory.setName(json);
            int num = xslTaskCategory.getTasknum();
            num++;
            xslTaskCategory.setTasknum(num);
            xslTaskCategoryMapper.updateByPrimaryKeySelective(xslTaskCategory);
            return XslResult.ok(xslTaskCategory.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 添加任务标签关系数据
     *
     * @param json
     * @return
     */
    @Override
    public XslResult SupplementTaskTagData(String json, Integer taskId) {
        try {
            XslDateTask xslDateTask = JsonUtils.jsonToPojo(json, XslDateTask.class);
            String json_tagName = xslDateTask.getTagname();
            List<String> commenderList = new ArrayList<String>();
            StringTokenizer st = new StringTokenizer(json_tagName, ",");
            while (st.hasMoreTokens()) {
                commenderList.add(st.nextToken());
            }
            XslTaskTag xslTaskTag = new XslTaskTag();
            for (int i = 0; i < commenderList.size(); i++) {
                XslTagExample xslTagExample = new XslTagExample();
                XslTagExample.Criteria criteria = xslTagExample.createCriteria();
                String json_taglist = commenderList.get(i);
                System.out.println(commenderList.get(i));
                criteria.andNameEqualTo(commenderList.get(i));
                List<XslTag> list_Tag = xslTagMapper.selectByExample(xslTagExample);
                System.out.println(list_Tag.get(0).getName());
                xslTaskTag.setCreatedate(new Date());
                xslTaskTag.setTagid(list_Tag.get(0).getId());
                xslTaskTag.setTaskid(taskId);
                xslTaskTagMapper.insert(xslTaskTag);
            }
            return XslResult.ok("发送任务成功");
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
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
//                xslTag.setState((Byte)1);
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
            return XslResult.ok("插入成功");
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }
}
