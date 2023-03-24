package com.cm.order.service;

import com.cm.order.domain.OrdersInfo;
import com.cm.order.domain.OrdersInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersInfoServiceImpl implements OrdersInfoService {

	private final OrdersInfoRepository ordersInfoRepository;

	@Autowired
	public OrdersInfoServiceImpl(OrdersInfoRepository ordersInfoRepository) {
		this.ordersInfoRepository = ordersInfoRepository;
	}

	@Override
	public OrdersInfo save(OrdersInfo ordersInfo) {
		return ordersInfoRepository.save(ordersInfo);
	}
}
