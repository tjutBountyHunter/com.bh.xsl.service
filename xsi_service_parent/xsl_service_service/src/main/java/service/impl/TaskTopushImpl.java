package service.impl;

import mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.*;
import service.*;
//import service.searchTaskMQ;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 任务
 *
 * @author 高山潍
 */
@Service
public class TaskTopushImpl implements TaskTopush {
    @Autowired
    private XslTagClassMapper xslTagClassMapper;
    @Autowired
    private XslTaskClassMapper xslTaskClassMapper;
    @Autowired
    private XslTaskCategoryMapper xslTaskCategoryMapper;
    @Autowired
    private XslHunterMapper xslHunterMapper;
    @Autowired
    private XslTaskMapper xslTaskMapper;
    @Autowired
    private XslTagMapper xslTagMapper;
    @Autowired
    private XslTaskTagMapper xslTaskTagMapper;
    @Autowired
    private XslTaskshowMapper xslTaskshowMapper;
    @Autowired
    private XslTaskTagShopMapper xslTaskTagShopMapper;
    /**
     * 任务种类
     *
     * @return
     */
    @Override
    public String taskClassied() {
        List<String> list = xslTagClassMapper.selectByXslTag();
        return JsonUtils.objectToJson(list);
    }

    /**
     * //标签种类
     *
     * @return
     */
    @Override
    public String tagClassied() {
        List<String> list = xslTaskClassMapper.selectByXslTagCategory(new XslTag());
        Random rd = new Random();
        List<String> list_tag = new ArrayList<>(6);
        int sum = list.size();
        int r = 0;
        int random = rd.nextInt(sum);
        for (int i = random; i < sum; i++) {
            if (i == (sum - 1)) {
                i = 0;
            }
            if (r <= 5) {
                list_tag.add(list.get(i));
                r++;
            } else {
                break;
            }
        }
        return JsonUtils.objectToJson(list_tag);
    }

    /**
     * 任务和任务种类初始化
     * @param xslTask
     * @return
     */
    @Override
    public XslResult accertdata(String xslTask) {
        try {
            xslTask = new String(xslTask.getBytes("iso-8859-1"), "utf-8");
            searchTaskMQ searchTaskMQ = new searchTaskMQImpl();
            searchTaskMQ.addTaskJson(xslTask);
            return XslResult.ok(xslTask);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 任务大厅展示
     * @param flagid
     * @param type
     * @param rows
     * @return
     */
    @Override
    public XslResult searchPage(Integer flagid, Integer type, Integer rows) throws ParseException {
        try {
            Map<String, Object> map = new HashMap<>(2);
            map.put("flagid", flagid);
            map.put("rows", rows);
            List<XslTaskPosh> masterlevelList = null;
            if (type == 0) {
                masterlevelList = xslTaskshowMapper.getXslTaskListfirst(rows);
                XslResult xslResult = findPage(masterlevelList);
                return xslResult;
            } else if (type == 1) {
                masterlevelList = xslTaskshowMapper.getXslTaskOld(map);
                XslResult xslResult = findPage(masterlevelList);
                return xslResult;
            } else {
                masterlevelList = xslTaskshowMapper.getXslTaskNew(map);
                XslResult xslResult = findPage(masterlevelList);
                return xslResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 获取查询值
     *
     * @param masterlevelList
     * @return
     */
    public XslResult findPage(List<XslTaskPosh> masterlevelList) {
        int userid = 0;
        List<String> tag_list = new ArrayList<>();
        for (int i = 0; i < masterlevelList.size(); i++) {
            String data_string = masterlevelList.get(i).getCreatedate();
            List<String> tagList = xslTaskTagShopMapper.selectById(masterlevelList.get(i).getTaskId());
            String json = JsonUtils.objectToJson(tagList);
            tag_list.add(json);
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(data_string);
                Date date1 = new Date();
                long firstDateMilliSeconds = date.getTime();
                long secondDateMilliSeconds = date1.getTime();
                long firstMinusSecond = secondDateMilliSeconds - firstDateMilliSeconds;
                //毫秒转为秒
                long milliSeconds = firstMinusSecond;
                int totalSeconds = (int) (milliSeconds / 1000);
                //得到总天数
                int days = totalSeconds / (3600 * 24);
//                int days_remains = totalSeconds % (3600 * 24);
//                //得到总小时数
//                int hours = days_remains / 3600;
//                int remains_hours = days_remains % 3600;
//                //得到分种数
//                int minutes = remains_hours / 60;
//                //得到总秒数
//                int seconds = remains_hours % 60;
                masterlevelList.get(i).setCreatedate("前" + days + "天");
                if (i == masterlevelList.size() - 1) {
                    userid = masterlevelList.get(i).getId();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return XslResult.build(500, "服务器异常");
            }
        }
        return changeFormat(masterlevelList, userid, tag_list);
    }

    /**
     * 整合内容
     *
     * @param masterlevelPage
     * @param userId
     * @param tag_List
     * @return
     */
    public XslResult changeFormat(List<XslTaskPosh> masterlevelPage, Integer userId, List<String> tag_List) {
        try {
            for (int i = 0; i < masterlevelPage.size(); i++) {
                masterlevelPage.get(i).setTag_name(tag_List.get(i));
            }
            return XslResult.ok(masterlevelPage);
        } catch (Exception e) {
            return XslResult.build(500, "服务器异常");
        }
    }
}

