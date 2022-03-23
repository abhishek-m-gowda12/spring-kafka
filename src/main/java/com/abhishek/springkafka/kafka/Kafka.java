package com.abhishek.springkafka.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class Kafka {
    @Autowired
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final String topicName;

    public Kafka(KafkaTemplate<String, Object> kafkaTemplate,
                 @Value("${kafka.name}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    public ListenableFuture<SendResult<String, Object>> sendMessage(String message) {
        return kafkaTemplate.send(topicName, "A", message);
    }

    @KafkaListener(topics = "${kafka.name}", groupId = "a")
    public void listen(ConsumerRecord<String, String> message) {
        System.out.println("Received Message " + message);
    }
}
