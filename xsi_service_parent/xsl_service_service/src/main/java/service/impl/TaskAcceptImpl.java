package service.impl;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.*;
import service.JsonUtils;
import service.TaskAccept;
import service.XslResult;

import java.util.*;

import static service.JiGuangPushUtil.pushNotice;


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

    /**
     * 接受任务
     *
     * @param json
     * @return
     */
    @Override
    public XslResult acceptTask(String json) {
        Date date = new Date();
        XslDatetime xslDatetime = JsonUtils.jsonToPojo(json, XslDatetime.class);
        xslDatetime.setCreatedate(date);
        //插入任务时间
        xslDatetimeMapper.insert(xslDatetime);
        XslTaskExample xslTaskExample = new XslTaskExample();
        XslTaskExample.Criteria criteria = xslTaskExample.createCriteria();
        criteria.andIdEqualTo(xslDatetime.getTaskid());
        List<XslTask> list = xslTaskMapper.selectByExample(xslTaskExample);
        XslTask xslTask = list.get(0);
        if (xslTask.getState() == 0) {
            int i = xslTask.getNumber() + 1;
            xslTask.setNumber(i);
            //更新任务接受任务总数
            xslTaskMapper.updateByExampleSelective(xslTask, xslTaskExample);
            List<XslTask> list1 = xslTaskMapper.selectByExample(xslTaskExample);
            XslUserExample xslUserExample = new XslUserExample();
            XslUserExample.Criteria criteria1 = xslUserExample.createCriteria();
            criteria1.andHunteridEqualTo(xslDatetime.getHunterid());
            List<XslUser> list2 = xslUserMapper.selectByExample(xslUserExample);
            //发送短信和推送
            System.out.println(list1.get(0).getNumber());
            if (list1.get(0).getNumber() > 1) {
                //发送推送
                pushNotice("tag", list1.get(0).getSendid() + "", "昵称为" + list2.get(0).getName() + "正在请求接取您的任务请您同意");
                //判断是否发送成功

                return XslResult.ok();
            } else {
                //短信加推送
                SendSmsResponse sendSmsResponse = excuteMaster(list2.get(0).getPhone());
                System.out.println(sendSmsResponse.getCode());
                if (sendSmsResponse.getCode().equals("OK")) {
                    //推送
                    pushNotice("tag", list1.get(0).getSendid() + "", "昵称为" + list2.get(0).getName() + "正在请求接取您的任务请您同意");
                    //判断是否发送成功


                    return XslResult.ok();
                } else {
                    return XslResult.build(400, "短信发送失败");
                }
            }
        } else {
            return XslResult.ok("该任务已经被接");
        }

    }

    /**
     * 任务接受成功后，通知猎人
     *
     * @param json
     * @return
     */
    @Override
    public XslResult decidedTask(String json) {
        XslDatetime xslDatetime = JsonUtils.jsonToPojo(json, XslDatetime.class);
        XslTaskExample xslTaskExample = new XslTaskExample();
        XslTaskExample.Criteria criteria = xslTaskExample.createCriteria();
        criteria.andIdEqualTo(xslDatetime.getTaskid());
        List<XslTask> list = xslTaskMapper.selectByExample(xslTaskExample);
        XslTask xslTask = list.get(0);
        xslTask.setState((byte) 2);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", xslTask.getId());
        map.put("state", (xslTask.getState()));
        xslTaskUpdate.updataByTaskId(map);
        XslUserExample xslUserExample = new XslUserExample();
        XslUserExample.Criteria criteria1 = xslUserExample.createCriteria();
        criteria1.andHunteridEqualTo(xslDatetime.getHunterid());
        List<XslUser> list2 = xslUserMapper.selectByExample(xslUserExample);
        SendSmsResponse response = excuteMessage(list2.get(0).getPhone());
        XslCollecth xslCollecth = new XslCollecth();
        /**
         * 历史猎人
         */
        xslCollecth.setUserid(xslTask.getSendid());
        xslCollecth.setHunterid(xslDatetime.getHunterid());
        Date date = new Date();
        xslCollecth.setCollectdate(date);
        xslCollecthMapper.insert(xslCollecth);
        if (response.getCode().equals("OK")) {
            return XslResult.ok("发送成功，已经通知猎人");
        } else {
            return XslResult.build(500, "通知猎人失败，请联系工作人员");
        }
    }

    /**
     * 获取猎人在接任务时的时间
     *
     * @param json
     * @return
     */
    @Override
    public String oldTime(String json) {
        Date date = new Date();
        XslDatetime xslDatetime = JsonUtils.jsonToPojo(json, XslDatetime.class);
        Map<String, Object> map = new HashMap<>();
        map.put("hunterId", xslDatetime.getHunterid());
        map.put("taskId", xslDatetime.getTaskid());
        date = xsloldTime.selectbyMap(map);
        String timeDate = JsonUtils.objectToJson(date);
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
    public Date timeDate() {
        return new Date();
    }
}
