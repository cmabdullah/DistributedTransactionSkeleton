package com.cm.order.ports.input.message.listener.restaurant;

import com.cm.order.events.OrderCancelledEvent;
import com.cm.order.instrumental.OrderApprovalSaga;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RestaurantApprovalResponseMessageListenerImpl implements RestaurantApprovalResponseMessageListener {

	private final OrderApprovalSaga orderApprovalSaga;

	public RestaurantApprovalResponseMessageListenerImpl(OrderApprovalSaga orderApprovalSaga) {
		this.orderApprovalSaga = orderApprovalSaga;
	}

	@Override
	public void orderApproved(String data) {
		orderApprovalSaga.process(data);
		log.info("Order is approved");
	}

	@Override
	public void orderRejected(String data) {
		OrderCancelledEvent orderCancelledEvent = orderApprovalSaga.rollback(data);
		orderCancelledEvent.fire();
		log.info("order cancelling");
	}
}
