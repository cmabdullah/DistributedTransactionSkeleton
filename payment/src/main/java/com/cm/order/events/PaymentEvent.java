package com.cm.order.events;

import com.cm.event.DomainEvent;
import com.cm.order.dto.message.OrdersInfoMessage;

import java.time.ZonedDateTime;

public abstract class PaymentEvent implements DomainEvent<OrdersInfoMessage> {

	private final OrdersInfoMessage odersInfoMessage;
	private final ZonedDateTime createdAt;


	public PaymentEvent(OrdersInfoMessage odersInfoMessage, ZonedDateTime createdAt) {
		this.odersInfoMessage = odersInfoMessage;
		this.createdAt = createdAt;
	}


	public OrdersInfoMessage getOrdersInfoMessage() {
		return odersInfoMessage;
	}

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}
}
