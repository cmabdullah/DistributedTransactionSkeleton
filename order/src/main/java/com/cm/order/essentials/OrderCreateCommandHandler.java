package com.cm.order.essentials;

import com.cm.order.domain.OrdersInfo;
import com.cm.order.events.OrderCreatedEvent;
import com.cm.order.ports.output.payment.OrderCreatedPaymentRequestMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderCreateCommandHandler {
	private final OrderCreateHelper orderCreateHelper;
	private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;

	@Autowired
	public OrderCreateCommandHandler(OrderCreateHelper orderCreateHelper, OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher) {
		this.orderCreateHelper = orderCreateHelper;
		this.orderCreatedPaymentRequestMessagePublisher = orderCreatedPaymentRequestMessagePublisher;
	}

	public OrdersInfo createOrder(OrdersInfo createOrderCommand) {
		OrderCreatedEvent orderCreatedEvent = orderCreateHelper.persistOrder(createOrderCommand);
		orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);
		return orderCreatedEvent.getOrderInfo();
	}
}
