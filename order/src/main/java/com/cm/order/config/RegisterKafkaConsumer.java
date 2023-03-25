package com.cm.order.config;

import com.cm.kafka.KafkaConsumerInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RegisterKafkaConsumer {
	public RegisterKafkaConsumer(KafkaConfig kafkaConfig) {
		KafkaConsumerInitializer getPaymentResponseKafkaConsumerInitializer = kafkaConfig.getPaymentResponseKafkaConsumerInitializer();
		KafkaConsumerInitializer getRestaurantApprovalResponseKafkaConsumerInitializer = kafkaConfig.getRestaurantApprovalResponseKafkaConsumerInitializer();
		getPaymentResponseKafkaConsumerInitializer.spinUp("payment-response");
		getRestaurantApprovalResponseKafkaConsumerInitializer.spinUp("restaurant-approval-response");
		log.info("consumer receiver called");
	}
}
