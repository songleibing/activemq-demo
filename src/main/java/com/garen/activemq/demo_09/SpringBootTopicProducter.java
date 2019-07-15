package com.garen.activemq.demo_09;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

@Component
public class SpringBootTopicProducter {
    @Autowired
    private JmsMessagingTemplate template;

    @Autowired
    private Topic topic;


    public void send() {
        template.convertAndSend(topic, "spring-boot-topic-message-" + System.currentTimeMillis());
    }

    @Scheduled(fixedDelay = 3000L)
    public void sendScheduled() {
        this.send();
    }
}
