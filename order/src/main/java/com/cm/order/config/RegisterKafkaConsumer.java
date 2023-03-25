package com.cm.order.config;

import com.cm.kafka.KafkaConsumer;
import com.cm.kafka.KafkaConsumerInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RegisterKafkaConsumer {

	public RegisterKafkaConsumer(KafkaConsumerInitializer kafkaConsumerInitializer) {
		new Thread(() -> kafkaConsumerInitializer.spinUp("demo_java")).start();
		log.info("consumer receiver called");
	}
}
