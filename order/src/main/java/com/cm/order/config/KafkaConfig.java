package com.cm.order.config;

import com.cm.config.KafkaProducerClient;
import com.cm.kafka.KafkaProducer;
import com.cm.kafka.KafkaProducerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class KafkaConfig {

	@Autowired
	private KafkaProducerClient kafkaProducerClient;


	@Bean
	KafkaProducer kafkaProducer() {
		return new KafkaProducerImpl(kafkaProducerClient);
	}

}
