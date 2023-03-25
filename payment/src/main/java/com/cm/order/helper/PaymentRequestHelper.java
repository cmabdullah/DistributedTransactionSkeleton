package com.cm.order.helper;

import com.cm.order.dto.message.OrdersInfoMessage;
import com.cm.order.events.PaymentCancelledEvent;
import com.cm.order.events.PaymentCompletedEvent;
import com.cm.order.events.PaymentEvent;
import com.cm.order.ports.output.payment.PaymentCancelledMessagePublisher;
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
	private final PaymentCancelledMessagePublisher paymentCancelledMessagePublisher;

	public PaymentRequestHelper(ObjectMapper objectMapper, PaymentCompletedMessagePublisher paymentCompletedEventDomainEventPublisher, PaymentCancelledMessagePublisher paymentCancelledMessagePublisher) {
		this.objectMapper = objectMapper;
		this.paymentCompletedEventDomainEventPublisher = paymentCompletedEventDomainEventPublisher;
		this.paymentCancelledMessagePublisher = paymentCancelledMessagePublisher;
	}

	@Transactional
	public PaymentEvent persistPayment(String paymentRequest) {
		//getCreditEntry() db call
		//getCreditHistory() db call
		//persistDbObjects(payment, creditEntry, creditHistories) db call update 3 tables
		OrdersInfoMessage ordersInfoMessage = getOrdersInfoMessage(paymentRequest, "PAID");
		return new PaymentCompletedEvent(ordersInfoMessage, ZonedDateTime.now(ZoneId.of("UTC")), paymentCompletedEventDomainEventPublisher);
	}

	@Transactional
	public PaymentEvent persistCancelPayment(String paymentRequest) {
		//findByOrderId()
		//getCreditEntry()
		//getCreditHistory()
		//persistDbObjects(payment, creditEntry, creditHistories) db call update 3 tables

		OrdersInfoMessage ordersInfoMessage = getOrdersInfoMessage(paymentRequest, "CANCELLED-PAYMENT");
		return new PaymentCancelledEvent(ordersInfoMessage, ZonedDateTime.now(ZoneId.of("UTC")), paymentCancelledMessagePublisher);
	}


	private OrdersInfoMessage getOrdersInfoMessage(String paymentRequest, String status) {
		OrdersInfoMessage ordersInfoMessage = null;
		try {
			ordersInfoMessage = objectMapper.readValue(paymentRequest, new TypeReference<>() {
			});
			ordersInfoMessage.setPaymentStatus(status);//dummy data
			log.info("ordersInfoMessage status is {} ", ordersInfoMessage);
		} catch (JsonProcessingException e) {
			log.error("parse failed err: {}", e.getLocalizedMessage());
		}
		return ordersInfoMessage;
	}
}
