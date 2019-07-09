package com.garen.activemq.demo_05;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class AcknowledgeTransactionConsumer {
    public static final String BROKER_URL = "tcp://127.0.0.1:61616";
    public static final String QUEUE_NAME = "acknowledge_demo_queue";

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.TRUE, Session.CLIENT_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);

        for (int i = 0; i < 3; i++) {
            TextMessage message = (TextMessage) consumer.receive();
            System.out.println("receive:" + message.getText());
        }

        // 在事物提交时会将所有消费的消息都签收，无论是否调用了message的acknowledge方法
        session.commit();

        consumer.close();
        session.close();
        connection.close();
    }
}
