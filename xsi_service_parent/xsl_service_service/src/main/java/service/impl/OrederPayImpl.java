package service.impl;

import mapper.IbmCommercialMapper;
import mapper.XslOrderMapper;
import mapper.XslTaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.*;
import service.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static contentApi.appid_key.message;

@Service
public class OrederPayImpl implements OrederPay {
    @Autowired
    private IbmCommercialMapper ibmCommercialMapper;
    @Autowired
    private XslTaskMapper xslTaskMapper;
    @Autowired
    private XslOrderMapper xslOrderMapper;
    @Autowired
    private OrderPayMq orderPayMq;

    Logger logger = LoggerFactory.getLogger(OrederPayImpl.class);

    @Override
    public XslResult payOrder(Integer userId, Integer taskId, String password) {
        XslResult xslResult = null;
        //身份验证 模拟密码
        if (password.length() == 6 && password.equals("123456")) {
            xslResult = payOrderCode(userId, taskId);
            return xslResult;
        } else {
            logger.info("orderPay is logger for password", message());
            return XslResult.build(403, "支付密码错误");
        }
    }

    public XslResult payOrderCode(Integer userId, Integer taskId) {
        //通过商户id去获取商业银行的商户编号
        IbmCommercialExample ibmCommercialExample = new IbmCommercialExample();
        IbmCommercialExample.Criteria criteria = ibmCommercialExample.createCriteria();
        criteria.andUseridEqualTo(userId);
        List<IbmCommercial> list = ibmCommercialMapper.selectByExample(ibmCommercialExample);
        String s = list.get(0).getCommercialcode();
        //通过商业编号获取银行卡余额多少
        //调用工商特定api获取余额查询 为了方便模拟数据
        Integer balanceMoney = 100000;
        //判断余额是否充足
        if (money(taskId) < balanceMoney) {
            //记录信息 插入数据库
            XslResult xslResult = orderNumber(userId, taskId);
            if (xslResult.getStatus() == 200) {
                orderPayMq.orderPay((BigDecimal) xslResult.getData());
            } else {
                return xslResult;
            }
            logger.info("orderPay is logger for money", message());
            return XslResult.ok("成功");
        } else {
            logger.info("orderPay is logger for money", message());
            return XslResult.build(403, "余额不足");
        }
    }

    //获取支付金额 模拟数据
    public Integer money(int taskId) {
        return 100;
    }

    //支付订单信息
    public XslResult orderNumber(Integer userId, Integer taskId) {
        try {
            Date date = new Date();
            String date_time = date.toString();
            String orderId = Md5Utils.digestMds(date_time);
            XslTaskExample xslTaskExample = new XslTaskExample();
            XslTaskExample.Criteria criteria = xslTaskExample.createCriteria();
            criteria.andIdEqualTo(taskId);
            List<XslTask> xslTasks = xslTaskMapper.selectByExample(xslTaskExample);

            XslOrder xslOrder = new XslOrder();
            xslOrder.setOrderid(orderId);
            xslOrder.setMoney(xslTasks.get(0).getMoney());
            xslOrder.setStartdate(new Date());
            xslOrder.setSendid(userId);
            xslOrderMapper.insertSelective(xslOrder);
            logger.info("orderPay is logger for money", message());
            return XslResult.ok(xslTasks.get(0).getMoney());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("orderPay is logger for money", message());
            return XslResult.build(500, "服务器异常！请联系工作人员");
        }

    }
}
