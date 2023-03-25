package com.cm.kafka;

import com.cm.config.KafkaConsumerClient;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;

public class KafkaConsumerInitializer {

	Logger logger = LoggerFactory.getLogger(KafkaConsumerInitializer.class);

	private final KafkaConsumerClient kafkaConsumerClient;
	private final KafkaConsumer KafkaConsumer;

	public KafkaConsumerInitializer(KafkaConsumerClient kafkaConsumerClient, com.cm.kafka.KafkaConsumer kafkaConsumer) {
		this.kafkaConsumerClient = kafkaConsumerClient;
		KafkaConsumer = kafkaConsumer;
	}


	public void spinUp(String topicName) {
		new Thread(() -> spinUpV2(topicName)).start();
	}

	public void spinUpV2(String topicName) {
		org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer =
				kafkaConsumerClient.getConsumer();
		//subscribe consumer to our topic
		consumer.subscribe(Collections.singletonList(topicName));
		logger.info("consuming new {} ", topicName);

		//poll consumer data
		while (true) {
			logger.debug("polling");
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
			for (ConsumerRecord<String, String> record : records) {
				logger.info("key : " + record.key() + " value : " + record.value());
				logger.info("Partition : " + record.partition() + " offset : " + record.offset());
				KafkaConsumer.receive(record.key(), record.value());
			}
		}
	}
}
