package com.cm.config;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class KafkaConsumerClient extends KafkaConfig {

	Logger logger = LoggerFactory.getLogger(KafkaConsumerClient.class);

	//create consumer
	KafkaConsumer<String, String> consumer;

	public KafkaConsumerClient(Map<String, String> consumerConfig) {
		super(consumerConfig);
		consumer = new KafkaConsumer<>(super.configProperties);
		logger.info("KafkaConsumerClient config success");
	}

	public KafkaConsumer<String, String> getConsumer() {
		return consumer;
	}
}

