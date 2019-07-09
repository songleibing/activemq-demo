package com.garen.activemq.demo_05;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class AcknowledgeNoneTransactionConsumer {
    public static final String BROKER_URL = "tcp://127.0.0.1:61616";
    public static final String QUEUE_NAME = "acknowledge_demo_queue";

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);

        for (int i = 0; i < 3; i++) {
            TextMessage message = (TextMessage) consumer.receive();
            System.out.println("receive: " + message.getText());

            if (i == 1) {
                // 如果签收模式为：Session.CLIENT_ACKNOWLEDGE，则需手动调用message的acknowledge方法
                // 调用message的acknowledge方法会将之前所有消费的message都签收
                message.acknowledge();
            }
        }

        consumer.close();
        session.close();
        connection.close();
    }
}
