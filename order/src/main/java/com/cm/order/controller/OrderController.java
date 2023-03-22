package com.cm.order.controller;

import com.cm.order.domain.OrdersInfo;
import com.cm.order.domain.OrdersInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@Slf4j
@RestController
public class OrderController {

	@Autowired
	private OrdersInfoRepository ordersInfoRepository;
	Random random = new Random();
	@PostMapping("/orderpost")
	public OrdersInfo ordersInfo(@RequestBody OrdersInfo ordersInfo){
		ordersInfo.setId(random.nextLong(100000000));
		log.info("order info "+ ordersInfo.toString());
		return ordersInfoRepository.save(ordersInfo);
	}
}
