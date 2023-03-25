package com.cm.order.converter;

import com.cm.order.dto.message.OrdersInfoMessage;
import com.cm.order.events.PaymentCompletedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PayLoadConverter {

	public static String send(ObjectMapper objectMapper, OrdersInfoMessage orderInfo) {
		String payLoad = "{}";
		try {
			payLoad = objectMapper.writeValueAsString(orderInfo);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		return payLoad;
	}
}
