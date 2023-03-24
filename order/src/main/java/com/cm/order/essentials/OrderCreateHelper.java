package com.cm.order.essentials;

import com.cm.order.domain.OrdersInfoRepository;
import com.cm.order.events.OrderCreatedEvent;
import com.cm.order.domain.OrdersInfo;
import com.cm.order.ports.output.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.cm.order.service.OrdersInfoService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class OrderCreateHelper {
	private final OrdersInfoRepository ordersInfoRepository;
	private final OrderCreatedPaymentRequestMessagePublisher orderCreatedEventDomainEventPublisher;


	public OrderCreateHelper(OrdersInfoRepository ordersInfoRepository, OrderCreatedPaymentRequestMessagePublisher orderCreatedEventDomainEventPublisher) {
		this.ordersInfoRepository = ordersInfoRepository;
		this.orderCreatedEventDomainEventPublisher = orderCreatedEventDomainEventPublisher;
	}

	@Transactional
	public OrderCreatedEvent persistOrder(OrdersInfo ordersInfo) {
//		checkCustomer();//call bd
//		checkRestaurant();//call db
		OrdersInfo savedOrder = ordersInfoRepository.save(ordersInfo);
		OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(savedOrder, ZonedDateTime.now(ZoneId.of("UTC")), orderCreatedEventDomainEventPublisher);
		return orderCreatedEvent;
	}
}
