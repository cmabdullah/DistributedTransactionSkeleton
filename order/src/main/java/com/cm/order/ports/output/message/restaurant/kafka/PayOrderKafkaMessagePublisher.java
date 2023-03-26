package com.cm.order.ports.output.message.restaurant.kafka;

import com.cm.kafka.KafkaProducer;
import com.cm.order.events.OrderPaidEvent;
import com.cm.order.ports.output.message.restaurant.OrderPaidRestaurantRequestMessagePublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PayOrderKafkaMessagePublisher implements OrderPaidRestaurantRequestMessagePublisher {

	private final ObjectMapper objectMapper;
	private final KafkaProducer kafkaProducer;

	@Autowired
	public PayOrderKafkaMessagePublisher(ObjectMapper objectMapper, KafkaProducer kafkaProducer) {
		this.objectMapper = objectMapper;
		this.kafkaProducer = kafkaProducer;
	}

	@Override
	public void publish(OrderPaidEvent domainEvent) {
		var orderInfo = domainEvent.getOrderInfo();
		String payLoad = "{}";
		try {
			payLoad = objectMapper.writeValueAsString(orderInfo);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		log.info("publishing order paid event to restaurant");
		kafkaProducer.send("restaurant-approval-request", String.valueOf(orderInfo.getId()), payLoad);
	}
}
