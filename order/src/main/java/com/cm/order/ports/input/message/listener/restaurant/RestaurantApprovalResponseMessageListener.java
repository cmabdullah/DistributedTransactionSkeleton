package com.cm.order.ports.input.message.listener.restaurant;

public interface RestaurantApprovalResponseMessageListener {
	void orderApproved(String restaurantApprovalResponse);

	void orderRejected(String restaurantApprovalResponse);
}
