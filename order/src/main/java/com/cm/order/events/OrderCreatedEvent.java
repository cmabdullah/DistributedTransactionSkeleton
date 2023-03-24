package com.cm.order.events;

import com.cm.event.publisher.DomainEventPublisher;
import com.cm.order.domain.OrdersInfo;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class OrderCreatedEvent extends OrderEvent {

	private final DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher;

	public OrderCreatedEvent(OrdersInfo ordersInfo, ZonedDateTime createdAt, DomainEventPublisher<OrderCreatedEvent>
			orderCreatedEventDomainEventPublisher) {
		super(ordersInfo, createdAt);
		this.orderCreatedEventDomainEventPublisher = orderCreatedEventDomainEventPublisher;
	}

	@Override
	public void fire() {
		orderCreatedEventDomainEventPublisher.publish(this);
	}
}
