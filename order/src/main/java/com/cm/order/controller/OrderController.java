package com.cm.order.controller;

import com.cm.kafka.KafkaProducer;
import com.cm.order.domain.OrdersInfo;
import com.cm.order.domain.OrdersInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.Callable;

@Slf4j
@RestController
public class OrderController {

	@Autowired
	private OrdersInfoRepository ordersInfoRepository;

	@Autowired
	private KafkaProducer kafkaProducer;
	Random random = new Random();
	@PostMapping("/orderpost")
	public OrdersInfo ordersInfo(@RequestBody OrdersInfo ordersInfo){
		ordersInfo.setId(random.nextLong());
		log.info("order info "+ ordersInfo.toString());
		OrdersInfo ordersInfo1 = ordersInfoRepository.save(ordersInfo);
		kafkaProducer.send(ordersInfo1.getId().toString(), ordersInfo1.getProductName(), String.valueOf(ordersInfo1.getPrice()), null);
		return ordersInfo1;
	}
}
