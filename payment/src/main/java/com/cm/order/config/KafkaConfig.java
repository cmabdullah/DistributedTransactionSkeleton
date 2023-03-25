package com.cm.order.config;

import com.cm.config.KafkaConsumerClient;
import com.cm.config.KafkaProducerClient;
import com.cm.kafka.*;
import com.cm.order.ports.input.message.listener.payment.kafka.PaymentRequestKafkaListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class KafkaConfig {

	private final KafkaProducerClient kafkaProducerClient;

	private final PaymentRequestKafkaListener paymentRequestKafkaListener;

	private final KafkaConsumerClient kafkaConsumerClient;

	@Autowired
	public KafkaConfig(KafkaProducerClient kafkaProducerClient,
	                   KafkaConsumerClient kafkaConsumerClient,
	                   PaymentRequestKafkaListener paymentRequestKafkaListener) {
		this.kafkaProducerClient = kafkaProducerClient;
		this.kafkaConsumerClient = kafkaConsumerClient;
		this.paymentRequestKafkaListener = paymentRequestKafkaListener;
	}

	@Bean
	KafkaProducer kafkaProducer() {
		return new KafkaProducerImpl(kafkaProducerClient);
	}

	public KafkaConsumerInitializer getPaymentResponseKafkaConsumerInitializer() {
		return new KafkaConsumerInitializer(kafkaConsumerClient, paymentRequestKafkaListener);
	}

}
