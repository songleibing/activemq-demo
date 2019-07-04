package com.garen.activemq.demo_03;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 * 持久化Topic
 *
 * @author leibing.song
 */
public class DurableTopicProducter {
    public static final String BROKER_URL = "tcp://127.0.0.1:61616";
    public static final String TOPIC_NAME = "durable_demo_topic";

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = connectionFactory.createConnection();

        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageProducer producer = session.createProducer(topic);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

        connection.start();

        for (int i = 0; i < 3; i++) {
            TextMessage message = session.createTextMessage("durable-topic-message-" + (i + 1));
            producer.send(message);
        }

        producer.close();
        session.close();
        connection.close();
    }
}
