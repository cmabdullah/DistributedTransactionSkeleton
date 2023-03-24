package com.cm.order.service;

import com.cm.order.domain.OrdersInfo;
import com.cm.order.domain.OrdersInfoRepository;
import com.cm.order.essentials.OrderCreateCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersInfoServiceImpl implements OrdersInfoService {


	private final OrderCreateCommandHandler orderCreateCommandHandler;

	@Autowired
	public OrdersInfoServiceImpl(OrderCreateCommandHandler orderCreateCommandHandler) {
		this.orderCreateCommandHandler = orderCreateCommandHandler;
	}


	@Override
	public OrdersInfo createOrder(OrdersInfo createOrderCommand) {
		return orderCreateCommandHandler.createOrder(createOrderCommand);
	}
}
