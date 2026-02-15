package com.kafka.order.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.*;
import com.kafka.order.model.OrderEvent;
import com.kafka.order.producer.OrderProducer;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderProducer producer;

    public OrderController(OrderProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public String createOrder() {
        OrderEvent order = new OrderEvent(
                UUID.randomUUID().toString(),
                "USER101",
                1500
        );
        producer.sendOrder(order);
        return "Order created successfully";
    }
}
