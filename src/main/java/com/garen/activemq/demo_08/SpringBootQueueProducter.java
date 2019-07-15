package com.garen.activemq.demo_08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
public class SpringBootQueueProducter {

    @Autowired
    private JmsMessagingTemplate template;

    @Autowired
    private Queue queue;

    public void send() {
        template.convertAndSend(queue, "spring-boot-message-" + System.currentTimeMillis());
    }

//    @Scheduled(fixedDelay = 3000L)
    public void sendScheduled() {
        this.send();
    }
}
