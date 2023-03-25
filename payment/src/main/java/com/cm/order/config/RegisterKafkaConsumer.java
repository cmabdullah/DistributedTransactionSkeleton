package com.cm.order.config;

import com.cm.kafka.KafkaConsumerInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RegisterKafkaConsumer {
	public RegisterKafkaConsumer(KafkaConfig kafkaConfig) {
		KafkaConsumerInitializer getPaymentResponseKafkaConsumerInitializer = kafkaConfig.getPaymentResponseKafkaConsumerInitializer();
		getPaymentResponseKafkaConsumerInitializer.spinUp("payment_request");
		log.info("consumer receiver called");
	}
}
