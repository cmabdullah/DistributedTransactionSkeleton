package com.cm.order.ports.output.payment;

import com.cm.event.publisher.DomainEventPublisher;
import com.cm.order.events.PaymentCancelledEvent;

public interface PaymentCancelledMessagePublisher extends DomainEventPublisher<PaymentCancelledEvent> {
}