package com.cm.order.config;

import com.cm.config.KafkaConsumerClient;
import com.cm.config.KafkaProducerClient;
import com.cm.kafka.*;
import com.cm.order.ports.input.message.listener.payment.kafka.PaymentResponseKafkaListener;
import com.cm.order.ports.input.message.listener.payment.kafka.RestaurantApprovalResponseKafkaListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class KafkaConfig {

	private final KafkaProducerClient kafkaProducerClient;

	private final PaymentResponseKafkaListener paymentResponseKafkaListener;
	private final RestaurantApprovalResponseKafkaListener restaurantApprovalResponseKafkaListener;

	private final KafkaConsumerClient kafkaConsumerClient1;

	@Autowired
	public KafkaConfig(KafkaProducerClient kafkaProducerClient,
	                   KafkaConsumerClient kafkaConsumerClient1,
	                   PaymentResponseKafkaListener paymentResponseKafkaListener,
	                   RestaurantApprovalResponseKafkaListener restaurantApprovalResponseKafkaListener
	) {
		this.kafkaProducerClient = kafkaProducerClient;
		this.kafkaConsumerClient1 = kafkaConsumerClient1;
		this.paymentResponseKafkaListener = paymentResponseKafkaListener;
		this.restaurantApprovalResponseKafkaListener = restaurantApprovalResponseKafkaListener;
	}

	@Bean
	KafkaProducer kafkaProducer() {
		return new KafkaProducerImpl(kafkaProducerClient);
	}

	public KafkaConsumerInitializer getPaymentResponseKafkaConsumerInitializer() {
		return new KafkaConsumerInitializer(kafkaConsumerClient1, paymentResponseKafkaListener);
	}

}
