package com.cm.event;

public interface DomainEvent<T> {
	void fire();
}