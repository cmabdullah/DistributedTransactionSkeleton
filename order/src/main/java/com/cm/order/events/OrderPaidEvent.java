package com.cm.order.events;

import com.cm.event.publisher.DomainEventPublisher;
import com.cm.order.domain.OrdersInfo;

import java.time.ZonedDateTime;

public class OrderPaidEvent extends OrderEvent {

	private final DomainEventPublisher<OrderPaidEvent> orderPaidEventDomainEventPublisher;

	public OrderPaidEvent(OrdersInfo ordersInfo, ZonedDateTime createdAt,
	                      DomainEventPublisher<OrderPaidEvent> orderPaidEventDomainEventPublisher) {
		super(ordersInfo, createdAt);
		this.orderPaidEventDomainEventPublisher = orderPaidEventDomainEventPublisher;
	}

	@Override
	public void fire() {
		orderPaidEventDomainEventPublisher.publish(this);
	}
}