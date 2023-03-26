package com.cm.order.ports.input.message.listener.payment;

import com.cm.order.events.OrderPaidEvent;
import com.cm.order.instrumental.OrderPaymentSaga;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {
	private final OrderPaymentSaga orderPaymentSaga;

	public PaymentResponseMessageListenerImpl(OrderPaymentSaga orderPaymentSaga) {
		this.orderPaymentSaga = orderPaymentSaga;
	}

	@Override
	public void paymentCompleted(String paymentResponse) {
		OrderPaidEvent orderPaidEvent = orderPaymentSaga.process(paymentResponse);
		orderPaidEvent.fire();
	}

	@Override
	public void paymentCancelled(String paymentResponse) {
		orderPaymentSaga.rollback(paymentResponse);
	}
}
