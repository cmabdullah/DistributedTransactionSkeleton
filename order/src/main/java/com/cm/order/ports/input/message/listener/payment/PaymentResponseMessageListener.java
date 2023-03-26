package com.cm.order.ports.input.message.listener.payment;

public interface PaymentResponseMessageListener {
	void paymentCompleted(String paymentResponse);
	void paymentCancelled(String paymentResponse);
}
