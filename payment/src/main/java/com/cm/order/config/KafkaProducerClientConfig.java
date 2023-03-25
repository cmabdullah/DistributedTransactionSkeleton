package com.cm.order.config;

import com.cm.config.KafkaProducerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class KafkaProducerClientConfig {

	@Bean
	KafkaProducerClient KafkaProducerClient() {

		Map<String, String> producerConfig = Map.of(
				"bootstrap.servers", "127.0.0.1:9092",
				"key.serializer", "org.apache.kafka.common.serialization.StringSerializer",
				"value.serializer", "org.apache.kafka.common.serialization.StringSerializer"
		);
		return new KafkaProducerClient(producerConfig);
	}

}
