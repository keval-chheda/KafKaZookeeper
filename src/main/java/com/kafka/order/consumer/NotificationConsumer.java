package com.kafka.order.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.order.model.OrderEvent;

@Service
public class NotificationConsumer {

    @KafkaListener(topics = "order-created", groupId = "notification-group")
    public void consume(OrderEvent order) {
        System.out.println("NOTIFICATION → User notified " + order.getUserId());
    }
}
