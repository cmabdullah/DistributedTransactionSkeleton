package com.cm.order.config;

import com.cm.kafka.KafkaProducer;
import com.cm.kafka.KafkaProducerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class KafkaConfig {


	@Bean
	KafkaProducer kafkaProducer() {
		return new KafkaProducerImpl();
	}

}
