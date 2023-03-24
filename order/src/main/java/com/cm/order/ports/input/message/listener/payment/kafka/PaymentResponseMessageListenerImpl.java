package com.cm.order.ports.input.message.listener.payment.kafka;

import com.cm.order.dto.message.PaymentResponse;
import com.cm.order.ports.input.message.listener.payment.PaymentResponseMessageListener;

public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {
	@Override
	public void paymentCompleted(PaymentResponse paymentResponse) {
//		OrderPaidEvent orderPaidEvent = orderPaymentSaga.process(paymentResponse);
//		orderPaidEvent.fire();
	}
}
