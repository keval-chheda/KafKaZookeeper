package com.kafka.order.consumer;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.order.model.OrderEvent;

@Service
public class EmailConsumer {

    @KafkaListener(topics = "order-created", groupId = "email-group")
    public void consume(OrderEvent order) {
        System.out.println("EMAIL → Sending email for order " + order.getOrderId());
    }
}
