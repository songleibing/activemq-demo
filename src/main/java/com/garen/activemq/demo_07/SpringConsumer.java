package com.garen.activemq.demo_07;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;
import javax.jms.TextMessage;

public class SpringConsumer {
    // 第一种消费方式：通过JmsTemplate的receive方法主动拉去消息
    public static void main(String[] args) throws JMSException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-spring-activemq.xml");
        JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);

        for (int i = 0; i < 3; i++) {
            TextMessage message = (TextMessage) jmsTemplate.receive();
            System.out.println(message.getText());
        }
    }
}
