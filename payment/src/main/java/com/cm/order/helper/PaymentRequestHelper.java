package com.cm.order.helper;

import com.cm.order.dto.message.OrdersInfoMessage;
import com.cm.order.events.PaymentCompletedEvent;
import com.cm.order.events.PaymentEvent;
import com.cm.order.ports.output.payment.PaymentCompletedMessagePublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Configuration
@Slf4j
public class PaymentRequestHelper {

	private final ObjectMapper objectMapper;

	private final PaymentCompletedMessagePublisher paymentCompletedEventDomainEventPublisher;

	public PaymentRequestHelper(ObjectMapper objectMapper, PaymentCompletedMessagePublisher paymentCompletedEventDomainEventPublisher) {
		this.objectMapper = objectMapper;
		this.paymentCompletedEventDomainEventPublisher = paymentCompletedEventDomainEventPublisher;
	}

	@Transactional
	public PaymentEvent persistPayment(String paymentRequest) {
		//getCreditEntry() db call
		//getCreditHistory() db call
		//persistDbObjects(payment, creditEntry, creditHistories) db call update 3 tables
		OrdersInfoMessage ordersInfoMessage = null;
		try {
			ordersInfoMessage = objectMapper.readValue(paymentRequest, new TypeReference<>() {
			});
			ordersInfoMessage.setPaymentStatus("PAID");//dummy data
			log.info("ordersInfoMessage status is {} ", ordersInfoMessage);
		} catch (JsonProcessingException e) {
			log.error("parse failed err: {}", e.getLocalizedMessage());
		}
		return new PaymentCompletedEvent(ordersInfoMessage, ZonedDateTime.now(ZoneId.of("UTC")), paymentCompletedEventDomainEventPublisher);
	}
}
