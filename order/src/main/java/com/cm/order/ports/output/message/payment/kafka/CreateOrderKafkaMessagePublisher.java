package com.cm.order.ports.output.message.payment.kafka;

import com.cm.kafka.KafkaProducer;
import com.cm.order.events.OrderCreatedEvent;
import com.cm.order.ports.output.message.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderKafkaMessagePublisher implements OrderCreatedPaymentRequestMessagePublisher {


	private final ObjectMapper objectMapper;


	@Autowired
	public CreateOrderKafkaMessagePublisher(ObjectMapper objectMapper, KafkaProducer kafkaProducer) {
		this.objectMapper = objectMapper;
		this.kafkaProducer = kafkaProducer;
	}

	private final KafkaProducer kafkaProducer;

	@Override
	public void publish(OrderCreatedEvent domainEvent) {
		var orderInfo = domainEvent.getOrderInfo();
		String payLoad = "{}";
		try {
			payLoad = objectMapper.writeValueAsString(orderInfo);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		kafkaProducer.send("payment_request", String.valueOf(orderInfo.getId()), payLoad);
	}
}
