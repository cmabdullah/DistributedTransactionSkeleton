package com.cm.order.config;

import com.cm.config.KafkaConsumerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Map;

@Configuration
public class KafkaConsumerClientConfig {

	@Bean
	@Scope("prototype")
	KafkaConsumerClient kafkaConsumerClient() {
		Map<String, String> producerConfig = Map.of(
				"bootstrap.servers", "127.0.0.1:9092",
				"key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer",
				"value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer",
				"group.id", "my-second-application",
				"auto.offset.reset", "earliest"
		);
		return new KafkaConsumerClient(producerConfig);
	}
}
