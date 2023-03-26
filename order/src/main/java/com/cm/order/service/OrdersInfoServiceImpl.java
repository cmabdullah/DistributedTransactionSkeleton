package com.cm.order.service;

import com.cm.event.publisher.DomainEventPublisher;
import com.cm.order.domain.OrdersInfo;
import com.cm.order.domain.OrdersInfoRepository;
import com.cm.order.essentials.OrderCreateCommandHandler;
import com.cm.order.events.OrderCancelledEvent;
import com.cm.order.events.OrderPaidEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class OrdersInfoServiceImpl implements OrdersInfoService {


	private final OrderCreateCommandHandler orderCreateCommandHandler;

	@Autowired
	public OrdersInfoServiceImpl(OrderCreateCommandHandler orderCreateCommandHandler, OrdersInfoRepository ordersInfoRepository) {
		this.orderCreateCommandHandler = orderCreateCommandHandler;
	}


	@Override
	public OrdersInfo createOrder(OrdersInfo createOrderCommand) {
		return orderCreateCommandHandler.createOrder(createOrderCommand);
	}

	@Override
	public OrderPaidEvent payOrder(OrdersInfo order, DomainEventPublisher<OrderPaidEvent> orderPaidEventDomainEventPublisher) {
		return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of("UTC")), orderPaidEventDomainEventPublisher);
	}

	@Override
	public OrderCancelledEvent cancelOrderPayment(OrdersInfo order, DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher) {
		return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of("UTC")), orderCancelledEventDomainEventPublisher);
	}
}
