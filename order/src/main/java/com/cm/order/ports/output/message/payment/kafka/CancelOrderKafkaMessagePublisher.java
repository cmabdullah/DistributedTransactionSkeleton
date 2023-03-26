package com.cm.order.ports.output.message.payment.kafka;

import com.cm.kafka.KafkaProducer;
import com.cm.order.events.OrderCancelledEvent;
import com.cm.order.ports.output.message.payment.OrderCancelledPaymentRequestMessagePublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CancelOrderKafkaMessagePublisher implements OrderCancelledPaymentRequestMessagePublisher {

	private final ObjectMapper objectMapper;
	private final KafkaProducer kafkaProducer;

	@Autowired
	public CancelOrderKafkaMessagePublisher(ObjectMapper objectMapper, KafkaProducer kafkaProducer) {
		this.objectMapper = objectMapper;
		this.kafkaProducer = kafkaProducer;
	}

	@Override
	public void publish(OrderCancelledEvent domainEvent) {
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
