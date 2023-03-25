package com.cm.order.ports.output.payment.kafka;

import com.cm.kafka.KafkaProducer;
import com.cm.order.converter.PayLoadConverter;
import com.cm.order.events.PaymentCancelledEvent;
import com.cm.order.ports.output.payment.PaymentCancelledMessagePublisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentCancelledKafkaMessagePublisher implements PaymentCancelledMessagePublisher {

	private final ObjectMapper objectMapper;

	private final KafkaProducer kafkaProducer;

	@Autowired
	public PaymentCancelledKafkaMessagePublisher(ObjectMapper objectMapper, @Lazy KafkaProducer kafkaProducer) {
		this.objectMapper = objectMapper;
		this.kafkaProducer = kafkaProducer;
	}

	@Override
	public void publish(PaymentCancelledEvent domainEvent) {
		String payLoad = PayLoadConverter.send(objectMapper, domainEvent.getOrdersInfoMessage());
		kafkaProducer.send("payment-response", String.valueOf(domainEvent.getOrdersInfoMessage().getId()), payLoad);
	}
}
