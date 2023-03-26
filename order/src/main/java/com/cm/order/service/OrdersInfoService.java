package com.cm.order.service;

import com.cm.event.publisher.DomainEventPublisher;
import com.cm.order.domain.OrdersInfo;
import com.cm.order.events.OrderCancelledEvent;
import com.cm.order.events.OrderPaidEvent;

import java.util.List;

public interface OrdersInfoService {

	OrdersInfo createOrder(OrdersInfo createOrderCommand);

	OrderPaidEvent payOrder(OrdersInfo order, DomainEventPublisher<OrderPaidEvent> orderPaidEventDomainEventPublisher);

	OrderCancelledEvent cancelOrderPayment(OrdersInfo order, DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher);
}
