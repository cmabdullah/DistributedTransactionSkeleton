package com.cm.order.ports.input.message.listener.payment.kafka;

import com.cm.kafka.KafkaConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RestaurantApprovalResponseKafkaListener implements KafkaConsumer {
	@Override
	public void receive(String key, String message) {
		log.info("received key {} and message {}", key, message);
	}
}
