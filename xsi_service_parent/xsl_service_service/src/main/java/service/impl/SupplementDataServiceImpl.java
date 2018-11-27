package service.impl;

import mapper.*;
import org.python.core.Py;
import org.python.core.PyByteArray;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.*;
import service.*;

import java.io.*;
import java.io.FileInputStream;
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
        XslResult xslResult = null;
        try {
            System.out.println(json);

            XslDateTask xslDateTask = JsonUtils.jsonToPojo(json, XslDateTask.class);
            XslTask xslTask = new XslTask();
            xslTask.setCid(xslDateTaskMapperr.getCidByName(xslDateTask.getCategoryname()));
            xslTask.setSendid(xslDateTask.getSendId());
            xslTask.setTaskid(UuidTaskId.getUUID());
            xslTask.setDescr(xslDateTask.getDescr());
            xslTask.setMoney(xslDateTask.getMoney());
            xslTask.setState((byte) 0);
            xslTask.setNumber(0);
            xslTask.setCreatedate(new Date());
            xslTask.setUpdatedate(new Date());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dateTime = formatter.parse(xslDateTask.getDeadline());
            xslTask.setDeadline(dateTime);
            Map<String, String> map = new HashMap<>(1);
            map.put("sentence", xslDateTask.getDescr());
            String wordFind = HttpClientUtil.doGet("http://47.93.19.164:8080/xsl-search-service/search/wordcheck", map);
            XslResultOk xslResultWord = XslResultOk.format(wordFind);
            List<String> data = (List<String>) xslResultWord.getData();
            if (data == null || data.size() == 0) {
                searchTaskMQ searchTaskMQ = new searchTaskMQImpl();
                searchTaskMQ.addTaskJson(JsonUtils.objectToJson(xslTask));
                xslTaskMapper.insert(xslTask);
                XslTaskExample xslTaskExample = new XslTaskExample();
                XslTaskExample.Criteria criteria = xslTaskExample.createCriteria();
                criteria.andTaskidEqualTo(xslTask.getTaskid());
                List<XslTask> list = xslTaskMapper.selectByExample(xslTaskExample);
                xslResult = SupplementTaskTagData(json, list.get(0).getId());
                return xslResult;
            } else {
                return XslResult.build(400, "有违规词组出现");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    public XslResult wordFind(String descr) {
        try {
            PythonInterpreter interpreter = new PythonInterpreter();
            PySystemState sys = Py.getSystemState();
//            E:\ID-workspeace\xsi_service_parent\xsl_service_common\src\main\
            sys.path.add("/home/ftp/www/images/jieba-master-1/build/lib");
            sys.path.add("/home/ftp/www/images/jieba-master-1/build/lib/jieba");
            FileInputStream filepy = new FileInputStream("/home/ftp/www/images/jieba-master-1/demo/demo2.py");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            interpreter.setOut(baos);
            String str = descr;
            str = str.replace("&", "");
            str = str.replace("*", "");
            str = str.replace("$", "");
            str = str.replace("%", "");
            byte[] bytes = str.getBytes();
            interpreter.exec("text=" + new PyByteArray(bytes));
            interpreter.execfile(filepy);
            ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
            BufferedReader in = new BufferedReader(new InputStreamReader(swapStream));
            String line;
            List<String> list = new ArrayList<String>(10);
            while ((line = in.readLine()) != null) {
                String[] strArray = line.split(" ");
                list.add(strArray[0]);
                if (strArray[1].equals("nz")) {
                    return XslResult.build(400, "有违规词组出现");
                }
            }
            return XslResult.ok();
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
                SupplementTagWrite(commenderList.get(i));
                System.out.println(commenderList.get(i));
                criteria.andNameEqualTo(commenderList.get(i));
                List<XslTag> list_Tag = xslTagMapper.selectByExample(xslTagExample);
                xslTaskTag.setCreatedate(new Date());
                xslTaskTag.setTagid(list_Tag.get(0).getId());
                xslTaskTag.setTaskid(taskId);
                xslTaskTagMapper.insert(xslTaskTag);
            }
            return XslResult.ok(taskId);
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
