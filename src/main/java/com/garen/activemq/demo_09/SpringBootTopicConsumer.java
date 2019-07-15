package com.garen.activemq.demo_09;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class SpringBootTopicConsumer {
    @JmsListener(destination = "spring-boot-topic")
    public void consumer(TextMessage message) throws JMSException {
        System.out.println(message.getText());
    }
}
