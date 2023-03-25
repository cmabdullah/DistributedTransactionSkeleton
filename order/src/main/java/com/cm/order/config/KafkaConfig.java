package com.cm.order.config;

import com.cm.config.KafkaConsumerClient;
import com.cm.config.KafkaProducerClient;
import com.cm.kafka.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class KafkaConfig {

	private final KafkaProducerClient kafkaProducerClient;

	private final KafkaConsumerClient kafkaConsumerClient;
	private final KafkaConsumer kafkaConsumer;

	@Autowired
	public KafkaConfig(KafkaProducerClient kafkaProducerClient, KafkaConsumerClient kafkaConsumerClient,
	                   KafkaConsumer kafkaConsumer) {
		this.kafkaProducerClient = kafkaProducerClient;
		this.kafkaConsumerClient = kafkaConsumerClient;
		this.kafkaConsumer = kafkaConsumer;
	}

	@Bean
	KafkaProducer kafkaProducer() {
		return new KafkaProducerImpl(kafkaProducerClient);
	}

	@Bean
	KafkaConsumerInitializer KafkaConsumerInitializer() {
		return new KafkaConsumerInitializer(kafkaConsumerClient, kafkaConsumer);
	}

}
