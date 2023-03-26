package com.cm.order.events;

import com.cm.event.DomainEvent;
import com.cm.order.domain.OrdersInfo;

public class EmptyEvent implements DomainEvent  {
	@Override
	public void fire() {

	}
}
