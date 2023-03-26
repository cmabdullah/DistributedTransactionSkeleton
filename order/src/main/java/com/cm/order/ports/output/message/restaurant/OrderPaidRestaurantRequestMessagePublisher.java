package com.cm.order.ports.output.message.restaurant;

import com.cm.event.publisher.DomainEventPublisher;
import com.cm.order.events.OrderPaidEvent;

public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
