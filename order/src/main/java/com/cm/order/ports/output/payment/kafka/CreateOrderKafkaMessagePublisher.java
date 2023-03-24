package com.cm.order.ports.output.payment.kafka;

import com.cm.kafka.KafkaProducer;
import com.cm.order.events.OrderCreatedEvent;
import com.cm.order.ports.output.payment.OrderCreatedPaymentRequestMessagePublisher;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderKafkaMessagePublisher implements OrderCreatedPaymentRequestMessagePublisher {

	public CreateOrderKafkaMessagePublisher(KafkaProducer kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}

	private final KafkaProducer kafkaProducer;

	@Override
	public void publish(OrderCreatedEvent domainEvent) {
		var orderInfo = domainEvent.getOrderInfo();

		kafkaProducer.send("order", orderInfo.getProductName(),
				String.valueOf(orderInfo.getPrice()), null);
	}
}
