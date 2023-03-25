package com.cm.order.events;

import com.cm.event.publisher.DomainEventPublisher;
import com.cm.order.dto.message.OrdersInfoMessage;

import java.time.ZonedDateTime;

public class PaymentCancelledEvent extends PaymentEvent {

	private final DomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher;

	public PaymentCancelledEvent(OrdersInfoMessage ordersInfoMessage, ZonedDateTime createdAt,
	                             DomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher) {
		super(ordersInfoMessage, createdAt);
		this.paymentCancelledEventDomainEventPublisher = paymentCancelledEventDomainEventPublisher;
	}

	@Override
	public void fire() {
		paymentCancelledEventDomainEventPublisher.publish(this);
	}
}
