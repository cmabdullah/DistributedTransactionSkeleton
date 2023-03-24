package com.cm.order.service;

import com.cm.order.domain.OrdersInfo;

public interface OrdersInfoService {

	OrdersInfo createOrder(OrdersInfo createOrderCommand);
}
