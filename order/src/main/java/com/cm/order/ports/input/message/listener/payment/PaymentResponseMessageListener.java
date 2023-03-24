package com.cm.order.ports.input.message.listener.payment;

import com.cm.order.dto.message.PaymentResponse;

public interface PaymentResponseMessageListener {
	void paymentCompleted(PaymentResponse paymentResponse);
}
