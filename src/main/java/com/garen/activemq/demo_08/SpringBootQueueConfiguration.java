package com.garen.activemq.demo_08;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.jms.Queue;


@EnableJms
@EnableScheduling
@Configuration
public class SpringBootQueueConfiguration {

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("spring-boot-queue");
    }
}
