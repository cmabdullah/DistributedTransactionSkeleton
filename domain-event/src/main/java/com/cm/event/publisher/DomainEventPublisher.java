package com.cm.event.publisher;

import com.cm.event.DomainEvent;

public interface DomainEventPublisher <T extends DomainEvent> {
	void publish(T domainEvent);
}