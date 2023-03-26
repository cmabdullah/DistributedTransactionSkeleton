package com.cm.order.ports.input.message.listener.payment.kafka;

import com.cm.kafka.KafkaConsumer;
import com.cm.order.domain.OrdersInfo;
import com.cm.order.ports.input.message.listener.payment.PaymentResponseMessageListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentResponseKafkaListener implements KafkaConsumer {

	private final ObjectMapper objectMapper;

	private final PaymentResponseMessageListener paymentResponseMessageListener;

	public PaymentResponseKafkaListener(ObjectMapper objectMapper, @Lazy PaymentResponseMessageListener paymentResponseMessageListener) {
		this.objectMapper = objectMapper;
		this.paymentResponseMessageListener = paymentResponseMessageListener;
	}

	@Override
	public void receive(String key, String message) {
		log.info("received key {} and message {}",key, message);

		try {
			OrdersInfo ordersInfoMessage = objectMapper.readValue(message, new TypeReference<>() {
			});
			log.info("parsed value is {} ", ordersInfoMessage);
			if (ordersInfoMessage.getPaymentStatus().equals("PAID")) {
				paymentResponseMessageListener.paymentCompleted(message);
			} else if (ordersInfoMessage.getPaymentStatus().equals("CANCELLED-PAYMENT")) {
				paymentResponseMessageListener.paymentCancelled(message);
			}
		} catch (JsonProcessingException e) {
			log.error("parse failed err: {}", e.getLocalizedMessage());
		}
	}
}
