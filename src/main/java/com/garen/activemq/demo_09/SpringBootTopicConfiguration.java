package com.garen.activemq.demo_09;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Topic;

/**
 * Topic和Queue的区别：配置中的spring.jms.pub-sub-domain=true是topic模式
 */
@Configuration
public class SpringBootTopicConfiguration {

    @Bean
    public Topic topic() {
        return new ActiveMQTopic("spring-boot-topic");
    }
}
