package com.cm.order.ports.input.message.listener.payment;

public interface PaymentRequestMessageListener {

	void completePayment(String paymentRequest);

	void cancelPayment(String paymentRequest);
}
