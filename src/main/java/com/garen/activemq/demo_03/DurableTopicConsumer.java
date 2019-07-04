package com.garen.activemq.demo_03;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

public class DurableTopicConsumer {
    public static final String BROKER_URL = "tcp://127.0.0.1:61616";
    public static final String TOPIC_NAME = "durable_demo_topic";

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = connectionFactory.createConnection();

        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        TopicSubscriber subscriber = session.createDurableSubscriber(topic, "remark");
        connection.start();

        for (Message message = subscriber.receive(); message != null; message = subscriber.receive()) {
            TextMessage textMessage = (TextMessage) message;
            System.out.println("收到消息：" + textMessage.getText());
        }

        subscriber.close();
        session.close();
        connection.close();
    }
}
