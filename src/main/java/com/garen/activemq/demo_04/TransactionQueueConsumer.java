package com.garen.activemq.demo_04;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class TransactionQueueConsumer {

    public static final String BROKER_URL = "tcp://127.0.0.1:61616";
    public static final String QUEUE_NAME = "transaction_demo_queue";

    public static void main(String[] args) throws JMSException, InterruptedException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);

        for (int i = 0; i < 3; i++) {
            TextMessage message = (TextMessage) consumer.receive();
            System.out.println("receive:" + message.getText());
        }

        Thread.sleep(10000);
        // 如果session是支持事物的，那么必须显式的调用commit方法，不然消息不会被消费，存在多次消费的可能
         session.commit();

        consumer.close();
        session.close();
        connection.close();

    }
}
