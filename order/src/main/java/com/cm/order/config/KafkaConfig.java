package com.cm.order.config;

import com.cm.config.KafkaConsumerClient;
import com.cm.config.KafkaProducerClient;
import com.cm.kafka.*;
import com.cm.order.ports.input.message.listener.payment.kafka.PaymentResponseKafkaListener;
import com.cm.order.ports.input.message.listener.restaurant.kafka.RestaurantApprovalResponseKafkaListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class KafkaConfig {

	private final KafkaProducerClient kafkaProducerClient;

	private final PaymentResponseKafkaListener paymentResponseKafkaListener;
	private final RestaurantApprovalResponseKafkaListener restaurantApprovalResponseKafkaListener;

	private final KafkaConsumerClient kafkaConsumerClient1;
	private final KafkaConsumerClient kafkaConsumerClient2;

	@Autowired
	public KafkaConfig(KafkaProducerClient kafkaProducerClient,
	                   KafkaConsumerClient kafkaConsumerClient1,
	                   KafkaConsumerClient kafkaConsumerClient2,
	                   PaymentResponseKafkaListener paymentResponseKafkaListener,
	                   RestaurantApprovalResponseKafkaListener restaurantApprovalResponseKafkaListener
	) {
		this.kafkaProducerClient = kafkaProducerClient;
		this.kafkaConsumerClient1 = kafkaConsumerClient1;
		this.kafkaConsumerClient2 = kafkaConsumerClient2;
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

	public KafkaConsumerInitializer getRestaurantApprovalResponseKafkaConsumerInitializer() {
		return new KafkaConsumerInitializer(kafkaConsumerClient2, restaurantApprovalResponseKafkaListener);
	}

}
