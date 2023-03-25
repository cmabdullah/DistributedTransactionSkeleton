package com.cm.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class KafkaProducerClient extends KafkaConfig {

	Logger logger = LoggerFactory.getLogger(KafkaProducerClient.class);

	private KafkaProducer<String, String> producer;

	public KafkaProducerClient(Map<String, String> producerConfig) {
		setProducerConfigProperties(producerConfig);
		initializeKafkaProducer();
		logger.info("KafkaProducerClient config success");
	}

	public void initializeKafkaProducer() {
		producer = new KafkaProducer<>(producerConfigProperties);
	}


	public KafkaProducer<String, String> getProducer() {
		return producer;
	}
}
