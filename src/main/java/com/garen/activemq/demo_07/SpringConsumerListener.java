package com.garen.activemq.demo_07;

import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;

@Component
public class SpringConsumerListener implements MessageListener {
    @Override
    public void onMessage(Message message) {

    }
}
