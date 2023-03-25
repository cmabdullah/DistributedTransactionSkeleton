package com.cm.order.ports.input.message.listener.payment;

import com.cm.kafka.KafkaConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerImpl implements KafkaConsumer {

	@Override
	public void receive(String key, String message) {
		log.info("received key {} and message {}",key, message);
	}
}
