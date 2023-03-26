package com.cm.order.ports.input.message.listener.restaurant.kafka;

import com.cm.kafka.KafkaConsumer;
import com.cm.order.domain.OrdersInfo;
import com.cm.order.ports.input.message.listener.restaurant.RestaurantApprovalResponseMessageListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RestaurantApprovalResponseKafkaListener implements KafkaConsumer {

	private final ObjectMapper objectMapper;
	private final RestaurantApprovalResponseMessageListener restaurantApprovalResponseMessageListener;

	public RestaurantApprovalResponseKafkaListener(ObjectMapper objectMapper, @Lazy RestaurantApprovalResponseMessageListener restaurantApprovalResponseMessageListener) {
		this.objectMapper = objectMapper;
		this.restaurantApprovalResponseMessageListener = restaurantApprovalResponseMessageListener;
	}


	@Override
	public void receive(String key, String message) {
		log.info("received key {} and message {}", key, message);

		try {
			OrdersInfo ordersInfoMessage = objectMapper.readValue(message, new TypeReference<>() {
			});
			log.info("parsed value is {} ", ordersInfoMessage);
			if (ordersInfoMessage.getOrderStatus().equals("APPROVED")) {
				restaurantApprovalResponseMessageListener.orderApproved(message);
			} else if (ordersInfoMessage.getOrderStatus().equals("REJECTED")) {
				restaurantApprovalResponseMessageListener.orderRejected(message);
			}
		} catch (JsonProcessingException e) {
			log.error("parse failed err: {}", e.getLocalizedMessage());
		}
	}
}

