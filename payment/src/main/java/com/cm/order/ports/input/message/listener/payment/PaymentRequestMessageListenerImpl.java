package com.cm.order.ports.input.message.listener.payment;

import com.cm.order.events.PaymentEvent;
import com.cm.order.helper.PaymentRequestHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentRequestMessageListenerImpl implements PaymentRequestMessageListener {

	private final PaymentRequestHelper paymentRequestHelper;

	public PaymentRequestMessageListenerImpl(PaymentRequestHelper paymentRequestHelper) {
		this.paymentRequestHelper = paymentRequestHelper;
	}

	@Override
	public void completePayment(String paymentRequest) {
		PaymentEvent paymentEvent = paymentRequestHelper.persistPayment(paymentRequest);
		paymentEvent.fire();
		log.info("payment success event fired");
	}

	@Override
	public void cancelPayment(String paymentRequest) {
		PaymentEvent paymentEvent = paymentRequestHelper.persistCancelPayment(paymentRequest);
		paymentEvent.fire();
		log.info("payment cancelled event fired");
	}
}
