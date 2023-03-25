package com.cm.kafka;

import com.cm.config.KafkaProducerClient;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class KafkaProducerImpl implements KafkaProducer {
	Logger logger = LoggerFactory.getLogger(KafkaProducerImpl.class);


	private final KafkaProducerClient kafkaProducerClient;

	public KafkaProducerImpl(KafkaProducerClient kafkaProducerClient) {
		this.kafkaProducerClient = kafkaProducerClient;
	}

	@Override
	public void send(String topicName, String key, String message) {
		logger.info(topicName.toString());
		logger.info(key.toString());
		logger.info(message.toString());
		org.apache.kafka.clients.producer.KafkaProducer<String, String> kafkaProducerClientProducer = kafkaProducerClient.getProducer();
		ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topicName, key, message);

		kafkaProducerClientProducer.send(producerRecord, new Callback() {
			@Override
			public void onCompletion(RecordMetadata metadata, Exception exception) {
				logger.info("Received new metadata/ \n" +
						"Topics: " + metadata.topic() + "\n" +
						"key: " + producerRecord.key() + "\n" +
						"Partitions : " + metadata.partition() + "\n" +
						"Offset: " + metadata.offset() + "\n" +
						"Timestamp: " + metadata.timestamp() + "\n" +
						"");
				if (exception != null)
					logger.error(exception.getLocalizedMessage());
			}
		});
	}
}
