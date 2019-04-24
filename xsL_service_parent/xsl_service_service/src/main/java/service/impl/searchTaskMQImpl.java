package service.impl;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.zookeeper.server.SessionTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import service.searchTaskMQ;

import javax.annotation.Resource;
import javax.jms.*;

@Service
public class searchTaskMQImpl implements searchTaskMQ {
    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    public void taskCommon(String destination, String json) {
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://47.93.230.61:61616");
            Connection connection = null;
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(destination);
            MessageProducer producer = session.createProducer(topic);
            TextMessage textMessage = session.createTextMessage(json);
            producer.send(textMessage);
            producer.close();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTaskJson(final String json) {
        String destination = "addTaskMQ";
        taskCommon(destination, json);
    }

    @Override
    public void deleteTaskJson(final String json) {
        String destination = "deleteTaskMQ";
        taskCommon(destination, json);
    }

    @Override
    public void alterTaskJson(final String json) {
        String destination = "alterTaskMQ";
        taskCommon(destination, json);
    }

    @Override
    public void numberTaskJson(final String json) {
        String destination = "numberTaskMQ";
        taskCommon(destination, json);
    }
}
