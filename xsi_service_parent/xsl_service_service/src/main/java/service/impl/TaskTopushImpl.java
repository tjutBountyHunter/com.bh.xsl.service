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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<String> list = xslTaskClassMapper.selectByXslTaskCategory(new XslTaskCategory());
        return JsonUtils.objectToJson(list);
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
     * 点击任务大厅展示
     *
     * @param pageno
     * @param pagesize
     * @return
     */
    @Override
    public PageDataResult searchPage(Integer pageno, Integer pagesize) throws ParseException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", (pageno - 1) * pagesize);
        map.put("size", pagesize);
        List<XslTaskPosh> masterlevelList = xslTaskshowMapper.getXslTaskListC(map);
        for (int i = 0; i < masterlevelList.size(); i++) {
            String data_string = masterlevelList.get(i).getCreatedate();
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
                int days_remains = totalSeconds % (3600 * 24);
                //得到总小时数
                int hours = days_remains / 3600;
                int remains_hours = days_remains % 3600;
                //得到分种数
                int minutes = remains_hours / 60;
                //得到总秒数
                int seconds = remains_hours % 60;
                masterlevelList.get(i).setCreatedate("前" + days + "天");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 当前页码
        // 总的数据条数
        int totalsize = xslTaskshowMapper.getXslTaskCountC(map);
        // 最大页码（总页码）
        int totalno = 0;
        if (totalsize % pagesize == 0) {
            totalno = totalsize / pagesize;
        } else {
            totalno = totalsize / pagesize + 1;
        }
        PageDataResult<XslTaskPosh> masterlevelPage = new PageDataResult<XslTaskPosh>();
        masterlevelPage.setDatas(masterlevelList);
        masterlevelPage.setTotalno(totalno);
        masterlevelPage.setTotalsize(totalsize);
        masterlevelPage.setPageno(pageno);
        return masterlevelPage;
    }
}

