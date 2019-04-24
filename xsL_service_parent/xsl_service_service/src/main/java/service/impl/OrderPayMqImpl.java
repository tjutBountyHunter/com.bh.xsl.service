package service.impl;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import service.OrderPayMq;

import javax.annotation.Resource;
import javax.jms.*;
import java.math.BigDecimal;

@Service
public class OrderPayMqImpl implements OrderPayMq {
    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    public void taskCommon(String destination, BigDecimal money) {
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://47.93.230.61:61616");
            Connection connection = null;
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(destination);
            MessageProducer producer = session.createProducer(topic);
            TextMessage textMessage = session.createTextMessage(String.valueOf(money));
            producer.send(textMessage);
            producer.close();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void orderPay(BigDecimal money) {
        String destination = "orderPay";
        taskCommon(destination, money);
    }

    @Override
    public void orderOut(BigDecimal money) {
        String destination = "orderOut";
        taskCommon(destination, money);
    }
}
