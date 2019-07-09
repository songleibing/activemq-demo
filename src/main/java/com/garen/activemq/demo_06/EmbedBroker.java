package com.garen.activemq.demo_06;

import org.apache.activemq.broker.BrokerService;

public class EmbedBroker {
    public static final String BROKER_URL = "tcp://127.0.0.1:61616";

    public static void main(String[] args) throws Exception {
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector(BROKER_URL);
        brokerService.start();
    }
}
