package com.garen.activemq.demo_01;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

public class Producter {
    public static final String BROKER_URL = "tcp://127.0.0.1:61616";
    public static final String QUEUE_NAME = "demo_queue_01";

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(QUEUE_NAME);
        MessageProducer producer = session.createProducer(destination);

        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime <= 1000) {
            producer.send(session.createTextMessage(Long.toString(System.currentTimeMillis())));
        }
        producer.close();
        session.close();
        connection.close();
    }
}
