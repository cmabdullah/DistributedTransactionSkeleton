package com.cm.order.ports.output.message.payment;

import com.cm.event.publisher.DomainEventPublisher;
import com.cm.order.events.OrderCreatedEvent;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {
}