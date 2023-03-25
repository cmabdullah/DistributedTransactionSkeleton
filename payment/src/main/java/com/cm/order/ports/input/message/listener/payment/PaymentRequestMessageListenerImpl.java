package com.cm.order.ports.input.message.listener.payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentRequestMessageListenerImpl implements PaymentRequestMessageListener {
	@Override
	public void completePayment(String paymentRequest) {

	}

	@Override
	public void cancelPayment(String paymentRequest) {

	}
}
