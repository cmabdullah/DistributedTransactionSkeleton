package com.cm.order.ports.output.payment.kafka;

import com.cm.kafka.KafkaProducer;
import com.cm.order.events.PaymentCompletedEvent;
import com.cm.order.ports.output.payment.PaymentCompletedMessagePublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentCompletedKafkaMessagePublisher implements PaymentCompletedMessagePublisher {

	private final ObjectMapper objectMapper;

	private final KafkaProducer kafkaProducer;

	@Autowired
	public PaymentCompletedKafkaMessagePublisher(ObjectMapper objectMapper,@Lazy KafkaProducer kafkaProducer) {
		this.objectMapper = objectMapper;
		this.kafkaProducer = kafkaProducer;
	}
	@Override
	public void publish(PaymentCompletedEvent domainEvent) {
		var orderInfo = domainEvent.getOrdersInfoMessage();
		String payLoad = "{}";
		try {
			payLoad = objectMapper.writeValueAsString(orderInfo);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		kafkaProducer.send("payment-response", String.valueOf(orderInfo.getId()), payLoad);
	}
}
