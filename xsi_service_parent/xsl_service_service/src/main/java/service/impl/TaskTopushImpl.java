package service.impl;

import mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.*;
import service.JsonUtils;
import service.PageDataResult;
import service.TaskTopush;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
     *
     * @param xslTask
     * @param xslTag
     * @param request
     * @param response
     * @return
     */
    @Override
    public String accertdata(XslTask xslTask, XslTag xslTag, HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("类别");
        XslTaskCategoryExample xslTaskCategoryExample = new XslTaskCategoryExample();
        XslTaskCategoryExample.Criteria criteria1 = xslTaskCategoryExample.createCriteria();
        criteria1.andNameEqualTo(name);
        List<XslTaskCategory> list1 = xslTaskCategoryMapper.selectByExample(xslTaskCategoryExample);
        XslTaskCategory xslTaskCategory = list1.get(0);
        //任务
        xslTask.setCreatedate(new Date());
        xslTask.setUpdatedate(new Date());
        xslTask.setCid(xslTaskCategory.getId());
        XslHunterExample xslHunterExample = new XslHunterExample();
        XslHunterExample.Criteria criteria = xslHunterExample.createCriteria();
        List<XslHunter> list = xslHunterMapper.selectByExample(xslHunterExample);
        XslHunter xslHunter = list.get(0);
        xslTask.setDeadline(xslHunter.getLastaccdate());
        xslTaskMapper.insert(xslTask);
        //类别
        xslTaskCategory.setCreatedate(new Date());
        xslTaskCategory.setTasknum(xslTaskCategory.getTasknum() + 1);
        xslTaskCategoryMapper.updateByPrimaryKey(xslTaskCategory);
        //标签
        XslTagExample xslTagExample = new XslTagExample();
        XslTagExample.Criteria criteria2 = xslTagExample.createCriteria();
        criteria2.andNameEqualTo(xslTag.getName());
        List<XslTag> list2 = xslTagMapper.selectByExample(xslTagExample);
        xslTag = list2.get(0);
        xslTag.setUsenum((short) (xslTag.getUsenum() + 1));
        xslTag.setCreatedate(new Date());
        xslTagMapper.insert(xslTag);
        //任务标签id
        XslTaskExample xslTaskExample = new XslTaskExample();
        XslTaskExample.Criteria criterion = xslTaskExample.createCriteria();
        criterion.andDescrEqualTo(xslTask.getDescr());
        List<XslTask> list3 = xslTaskMapper.selectByExample(xslTaskExample);
        XslTaskTag xslTaskTag = new XslTaskTag();
        xslTaskTag.setTagid(xslTag.getId());
        xslTaskTag.setTaskid(list3.get(0).getId());
        xslTaskTag.setCreatedate(new Date());
        //插入数据库
        xslTaskTagMapper.insert(xslTaskTag);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("XslTaskSendId", xslTask.getSendid());
        map.put("XslTaskdescr", xslTask.getDescr());
        map.put("XslTaskMoney", xslTask.getMoney());
        map.put("XslTaskname", xslTaskCategory.getName());
        map.put("XslTagname", xslTag.getName());
        map.put("XslTaskstate", xslTask.getState());
        String json = JsonUtils.objectToJson(map);
        HttpSession session = request.getSession();
        session.setAttribute("任务", json);
        return json;
    }


    /**
     * 分页查询
     *
     * @param queryText
     * @param pageno
     * @param pagesize
     * @return
     */
    @Override
    public PageDataResult searchPage(String queryText, Integer pageno, Integer pagesize) {
        if (queryText == null) {
            List<String> list = xslTaskClassMapper.selectByXslTaskCategory(new XslTaskCategory());
            int i = list.size();
            int random = (int) (Math.random() * i);
            queryText = list.get(random);

        }
        //取到分页信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", (pageno - 1) * pagesize);
        map.put("size", pagesize);
        map.put("queryText", queryText);
        List<XslTask> masterlevelList = xslTaskshowMapper.getXslTaskList(map);
        // 当前页码
        // 总的数据条数
        int totalsize = xslTaskshowMapper.getXslTaskCount(map);
        // 最大页码（总页码）
        int totalno = 0;
        if (totalsize % pagesize == 0) {
            totalno = totalsize / pagesize;
        } else {
            totalno = totalsize / pagesize + 1;
        }
        PageDataResult<XslTask> masterlevelPage = new PageDataResult<XslTask>();
        masterlevelPage.setDatas(masterlevelList);
        masterlevelPage.setTotalno(totalno);
        masterlevelPage.setTotalsize(totalsize);
        masterlevelPage.setPageno(pageno);
        return masterlevelPage;
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

