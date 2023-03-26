package com.cm.event;

import com.cm.event.DomainEvent;

public interface SagaStep <T, S extends DomainEvent, U extends DomainEvent> {
	S process(T data);

	U rollback(T data);
}
