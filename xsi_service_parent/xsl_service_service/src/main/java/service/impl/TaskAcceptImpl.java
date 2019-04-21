package service.impl;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import dao.JedisClient;
import example.XslTaskExample;
import example.XslUserExample;
import mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pojo.*;
import service.*;
import util.Message;
import util.XslResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 消息接收
 *
 * @author 高山潍
 */
@Service
public class TaskAcceptImpl implements TaskAccept {
    @Autowired
    private XslUserMapper xslUserMapper;
    @Autowired
    private XslOrderMapper xslOrderMapper;
    @Autowired
    private XslTaskMapper xslTaskMapper;
    @Autowired
    private XslDatetimeMapper xslDatetimeMapper;
    @Autowired
    private XslTaskUpdate xslTaskUpdate;
    @Autowired
    private XsloldTime xsloldTime;
    @Autowired
    private XslCollecthMapper xslCollecthMapper;
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private OrderPayMq orderPayMq;
    @Autowired
    private XslHistoryhMapper xslHistoryhMapper;
    @Value("${REDIS_USER_SESSION_ACCEPTTASK_KEY}")
    private String REDIS_USER_SESSION_ACCEPTTASK_KEY;
    @Value("${Login_SESSION_EXPIRE_ACCEPTTASK}")
    private Integer Login_SESSION_EXPIRE_ACCEPTTASK;
    /**
     * 接受任务
     * @param hunterId
     * @param taskId
     * @return
     */
    @Override
    public XslResult acceptTask(Integer hunterId, String taskId) {
        Date dateTime = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = df.format(dateTime);
        jedisClient.set(REDIS_USER_SESSION_ACCEPTTASK_KEY + ":" + hunterId + "" + taskId, dateStr);
        //设置session过期时间
        jedisClient.expire(REDIS_USER_SESSION_ACCEPTTASK_KEY + ":" + hunterId + "" + taskId, Login_SESSION_EXPIRE_ACCEPTTASK);
        XslTaskExample xslTaskExample = new XslTaskExample();
        XslTaskExample.Criteria criteria = xslTaskExample.createCriteria();
        criteria.andTaskidEqualTo(taskId);
        System.out.println(taskId);
        List<XslTask> list = xslTaskMapper.selectByExample(xslTaskExample);
        XslTask xslTask = list.get(0);
        if (xslTask.getState() == 0) {
            xslTask.setState((byte) 2);
            xslTaskMapper.updateByExampleSelective(xslTask, xslTaskExample);
            List<XslTask> list1 = xslTaskMapper.selectByExample(xslTaskExample);

            XslUserExample xslUserExample = new XslUserExample();
            XslUserExample.Criteria criteria1 = xslUserExample.createCriteria();
            criteria1.andIdEqualTo(list1.get(0).getSendid());
            List<XslUser> list2 = xslUserMapper.selectByExample(xslUserExample);
            /**
             * 历史猎人
             */
            XslHistoryh xslHistoryh = new XslHistoryh();
            xslHistoryh.setUserid(xslTask.getSendid());
            xslHistoryh.setHunterid(hunterId);
            xslHistoryh.setTaskid(taskId);
            Date date = new Date();
            xslHistoryh.setCreatedate(new Date());
            xslHistoryhMapper.insertSelective(xslHistoryh);
            SendSmsResponse sendSmsResponse = excuteMaster(list2.get(0).getPhone());
            if (sendSmsResponse.getCode().equals("OK")) {
                orderPayMq.orderOut(list1.get(0).getMoney());
                return XslResult.ok("短信通知发送成功");
            }
            return XslResult.build(400, "短信发送失败");
        }
        return XslResult.ok("该任务已经被接");
    }
//    @Override
//    public XslResult acceptTask(Integer hunterId, String taskId) {
//        Date dateTime = new Date();
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateStr = df.format(dateTime);
//        jedisClient.set(REDIS_USER_SESSION_ACCEPTTASK_KEY + ":" + hunterId + "" + taskId, dateStr);
//        //设置session过期时间
//        jedisClient.expire(REDIS_USER_SESSION_ACCEPTTASK_KEY + ":" + hunterId + "" + taskId, Login_SESSION_EXPIRE_ACCEPTTASK);
//        XslTaskExample xslTaskExample = new XslTaskExample();
//        XslTaskExample.Criteria criteria = xslTaskExample.createCriteria();
//        criteria.andTaskidEqualTo(taskId);
//        System.out.println(taskId);
//        List<XslTask> list = xslTaskMapper.selectByExample(xslTaskExample);
//        XslTask xslTask = list.get(0);
//        if (xslTask.getState() == 0) {
//            int i = xslTask.getNumber() + 1;
//            xslTask.setNumber(i);
//            //更新任务接受任务总数
//            xslTaskMapper.updateByExampleSelective(xslTask, xslTaskExample);
//            searchTaskMQ searchTaskMQ = new searchTaskMQImpl();
//            searchTaskMQ.numberTaskJson(JsonUtils.objectToJson(xslTask));
//            List<XslTask> list1 = xslTaskMapper.selectByExample(xslTaskExample);
//            XslUserExample xslUserExample = new XslUserExample();
//            XslUserExample.Criteria criteria1 = xslUserExample.createCriteria();
//            criteria1.andIdEqualTo(hunterId);
//            List<XslUser> list2 = xslUserMapper.selectByExample(xslUserExample);
//            //发送短信和推送
//            System.out.println(list2.get(0).getPhone());
//            System.out.println(list1.get(0).getNumber());
//            if (list1.get(0).getNumber() > 1) {
//                //发送推送
////                String jsonJudge = pushNotice("tag", list1.get(0).getSendid() + "", "昵称为" + list2.get(0).getName() + "正在请求接取您的任务请您同意");
////                //判断是否发送成功
////                System.out.println(jsonJudge);
//                return XslResult.ok();
//            } else {
//                //短信加推送
//                SendSmsResponse sendSmsResponse = excuteMaster(list2.get(0).getPhone());
//
//                if (sendSmsResponse.getCode().equals("OK")) {
//                    //推送
////                    pushNotice("tag", list1.get(0).getSendid() + "", "昵称为" + list2.get(0).getName() + "正在请求接取您的任务请您同意");
//                    //判断是否发送成功
//                    return XslResult.ok();
//                } else {
//                    return XslResult.build(400, "短信发送失败");
//                }
//            }
//        } else {
//            return XslResult.ok("该任务已经被接");
//        }
//    }

    /**
     * 任务接受成功后，通知猎人
     * @param hunterId
     * @param taskId
     * @return
     */
    @Override
    public XslResult decidedTask(Integer hunterId, String taskId) {
        XslTaskExample xslTaskExample = new XslTaskExample();
        XslTaskExample.Criteria criteria = xslTaskExample.createCriteria();
        criteria.andTaskidEqualTo(taskId);
        List<XslTask> list = xslTaskMapper.selectByExample(xslTaskExample);
        XslTask xslTask = list.get(0);
        xslTask.setState((byte) 2);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", xslTask.getId());
        map.put("state", (xslTask.getState()));
        xslTaskUpdate.updataByTaskId(map);
        XslUserExample xslUserExample = new XslUserExample();
        XslUserExample.Criteria criteria1 = xslUserExample.createCriteria();
        criteria1.andHunteridEqualTo(hunterId);
        List<XslUser> list2 = xslUserMapper.selectByExample(xslUserExample);
        SendSmsResponse response = excuteMessage(list2.get(0).getPhone());
        XslHistoryh xslHistoryh = new XslHistoryh();
        /**
         * 历史猎人
         */
        xslHistoryh.setUserid(xslTask.getSendid());
        xslHistoryh.setHunterid(hunterId);
        xslHistoryh.setTaskid(taskId);
        Date date = new Date();
        xslHistoryh.setCreatedate(new Date());
        xslHistoryhMapper.insert(xslHistoryh);
        if (response.getCode().equals("OK")) {
            orderPayMq.orderOut(list.get(0).getMoney());
            return XslResult.ok("发送成功，已经通知猎人");
        } else {
            return XslResult.build(500, "通知猎人失败，请联系工作人员");
        }
    }

    /**
     *获取猎人接收任务时间
     * @param hunterId
     * @param taskId
     * @return
     */
    @Override
    public String oldTime(Integer hunterId, String taskId) {
        String timeDate = jedisClient.get(REDIS_USER_SESSION_ACCEPTTASK_KEY + ":" + hunterId + "" + taskId);
        return timeDate;
    }

    /**
     * 通知雇主
     *
     * @param phone
     * @return
     */
    public static SendSmsResponse excuteMaster(String phone) {
        SendSmsResponse response = Message.sendIdentifyingTempleteMaster(phone);
        return response;
    }

    /**
     * 通知猎人
     *
     * @param phone
     * @return
     */
    public static SendSmsResponse excuteMessage(String phone) {
        SendSmsResponse response = Message.sendIdentifyingTempleteMHunter(phone);
        return response;
    }

    /**
     * 随时返回时间
     *
     * @return
     */
    @Override
    public String timeDate() {

        Date dateTime = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = df.format(dateTime);
        return dateStr;
    }
}
