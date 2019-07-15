package com.garen.activemq.demo_08;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class SpringBootQueueConsumer {

    @JmsListener(destination = "spring-boot-queue")
    public void consumer(TextMessage message) throws JMSException {
        System.out.println(message.getText());
    }
}
