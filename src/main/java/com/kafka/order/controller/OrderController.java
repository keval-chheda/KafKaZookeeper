package com.kafka.order.controller;

import com.kafka.order.model.OrderEvent;
import com.kafka.order.producer.OrderProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final OrderProducer producer;

	public OrderController(OrderProducer producer) {
		this.producer = producer;
	}

	@PostMapping
	public String createOrder(@RequestBody OrderEvent order) {
		producer.sendOrder(order);
		return "Order sent to Kafka successfully";
	}
}