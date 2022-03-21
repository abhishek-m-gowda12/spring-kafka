package com.abhishek.springkafka.service;

import com.abhishek.springkafka.kafka.Kafka;

@org.springframework.stereotype.Service
public class Service {

    private final Kafka kafka;

    public Service(Kafka kafka) {
        this.kafka = kafka;
    }

    public void sendMessage(String message) {
        kafka.sendMessage(message);
    }
}
