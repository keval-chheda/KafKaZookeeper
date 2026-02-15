package com.kafka.order.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.order.model.OrderEvent;

@Service
public class AnalyticsConsumer {

    @KafkaListener(topics = "order-created", groupId = "analytics-group")
    public void consume(OrderEvent order) {
        System.out.println("ANALYTICS → Order amount " + order.getAmount());
    }
}
