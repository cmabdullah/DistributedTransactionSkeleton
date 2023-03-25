package com.cm.order.events;

import com.cm.event.publisher.DomainEventPublisher;
import com.cm.order.dto.message.OrdersInfoMessage;

import java.time.ZonedDateTime;

public class PaymentCompletedEvent extends PaymentEvent {

	private final DomainEventPublisher<PaymentCompletedEvent> paymentCompletedEventDomainEventPublisher;

	public PaymentCompletedEvent(OrdersInfoMessage ordersInfoMessage, ZonedDateTime createdAt, DomainEventPublisher<PaymentCompletedEvent>
			paymentCompletedEventDomainEventPublisher) {
		super(ordersInfoMessage, createdAt);
		this.paymentCompletedEventDomainEventPublisher = paymentCompletedEventDomainEventPublisher;
	}

	@Override
	public void fire() {
		paymentCompletedEventDomainEventPublisher.publish(this);
	}
}
