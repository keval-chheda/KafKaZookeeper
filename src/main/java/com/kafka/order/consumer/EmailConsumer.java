package com.kafka.order.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.order.model.OrderEvent;

@Service
public class EmailConsumer {

	@KafkaListener(topics = "order-created", groupId = "email-group")
	public void consume(ConsumerRecord<String, OrderEvent> record) {

		OrderEvent order = record.value();

		System.out.println("EMAIL → order " + order.getOrderId() + " partition=" + record.partition() + " offset="
				+ record.offset());
	}
}
