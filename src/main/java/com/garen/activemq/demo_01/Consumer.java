package com.garen.activemq.demo_01;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Consumer {
    public static final String BROKER_URL = "tcp://127.0.0.1:61616";
    public static final String QUEUE_NAME = "demo_queue_01";

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(destination);

        while (true) {
            TextMessage message = (TextMessage) consumer.receive();
            System.out.println(System.currentTimeMillis() + " --- " + message.getText());
        }
    }
}
