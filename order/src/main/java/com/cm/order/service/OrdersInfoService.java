package com.cm.order.service;

import com.cm.event.publisher.DomainEventPublisher;
import com.cm.order.domain.OrdersInfo;
import com.cm.order.events.OrderPaidEvent;

public interface OrdersInfoService {

	OrdersInfo createOrder(OrdersInfo createOrderCommand);

	OrderPaidEvent payOrder(OrdersInfo order, DomainEventPublisher<OrderPaidEvent> orderPaidEventDomainEventPublisher);
}
