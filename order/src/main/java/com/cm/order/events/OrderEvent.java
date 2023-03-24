package com.cm.order.events;

import com.cm.event.DomainEvent;
import com.cm.order.domain.OrdersInfo;

import java.time.ZonedDateTime;

public abstract class OrderEvent implements DomainEvent<OrdersInfo> {

	private final OrdersInfo ordersInfo;
	private final ZonedDateTime createdAt;


	public OrderEvent(OrdersInfo ordersInfo, ZonedDateTime createdAt) {
		this.ordersInfo = ordersInfo;
		this.createdAt = createdAt;
	}


	public OrdersInfo getOrderInfo() {
		return ordersInfo;
	}

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}
}
