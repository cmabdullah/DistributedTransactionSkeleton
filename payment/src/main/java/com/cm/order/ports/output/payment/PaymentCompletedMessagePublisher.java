package com.cm.order.ports.output.payment;

import com.cm.event.publisher.DomainEventPublisher;
import com.cm.order.events.PaymentCompletedEvent;

public interface PaymentCompletedMessagePublisher extends DomainEventPublisher<PaymentCompletedEvent> {
}