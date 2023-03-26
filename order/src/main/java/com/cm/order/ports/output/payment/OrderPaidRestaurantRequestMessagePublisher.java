package com.cm.order.ports.output.payment;

import com.cm.event.publisher.DomainEventPublisher;
import com.cm.order.events.OrderCreatedEvent;
import com.cm.order.events.OrderPaidEvent;

public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
