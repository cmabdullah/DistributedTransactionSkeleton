package com.cm.order.events;

import com.cm.event.publisher.DomainEventPublisher;
import com.cm.order.domain.OrdersInfo;

import java.time.ZonedDateTime;

public class OrderCancelledEvent extends OrderEvent {
	private final DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher;

	public OrderCancelledEvent(OrdersInfo ordersInfo, ZonedDateTime createdAt, DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher) {
		super(ordersInfo, createdAt);
		this.orderCancelledEventDomainEventPublisher = orderCancelledEventDomainEventPublisher;
	}

	@Override
	public void fire() {
		orderCancelledEventDomainEventPublisher.publish(this);
	}
}
