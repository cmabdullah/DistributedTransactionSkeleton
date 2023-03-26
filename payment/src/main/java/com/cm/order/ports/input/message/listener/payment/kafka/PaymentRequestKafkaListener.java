package com.cm.order.ports.input.message.listener.payment.kafka;

import com.cm.kafka.KafkaConsumer;
import com.cm.order.dto.message.OrdersInfoMessage;
import com.cm.order.ports.input.message.listener.payment.PaymentRequestMessageListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentRequestKafkaListener implements KafkaConsumer {


	private final PaymentRequestMessageListener paymentRequestMessageListener;
	private final ObjectMapper objectMapper;

	public PaymentRequestKafkaListener(PaymentRequestMessageListener paymentRequestMessageListener, ObjectMapper objectMapper) {
		this.paymentRequestMessageListener = paymentRequestMessageListener;
		this.objectMapper = objectMapper;
	}

	@Override
	public void receive(String key, String message) {

		try {
			OrdersInfoMessage ordersInfoMessage = objectMapper.readValue(message, new TypeReference<>() {
			});
			log.info("parsed value is {} ", ordersInfoMessage);
			if (ordersInfoMessage.getPaymentStatus().equals("PENDING")) {
				paymentRequestMessageListener.completePayment(message);
			} else if (ordersInfoMessage.getOrderStatus().equals("CANCELLING")) {
				paymentRequestMessageListener.cancelPayment(message);
			}
		} catch (JsonProcessingException e) {
			log.error("parse failed err: {}", e.getLocalizedMessage());
		}


		log.info("received key {} and message {}", key, message);
	}
}