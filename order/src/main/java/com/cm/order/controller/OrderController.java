package com.cm.order.controller;

import com.cm.kafka.KafkaProducer;
import com.cm.order.domain.OrdersInfo;
import com.cm.order.service.OrdersInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.Callable;

@Slf4j
@RestController
public class OrderController {

	private final OrdersInfoService ordersInfoService;

	private final KafkaProducer kafkaProducer;

	public OrderController(OrdersInfoService ordersInfoService, KafkaProducer kafkaProducer) {
		this.ordersInfoService = ordersInfoService;
		this.kafkaProducer = kafkaProducer;
	}

	Random random = new Random();
	@PostMapping("/orderpost")
	public ResponseEntity<OrdersInfo> ordersInfo(@RequestBody OrdersInfo createOrderCommand){
		createOrderCommand.setId(random.nextLong());
		log.info("order info "+ createOrderCommand);
		OrdersInfo ordersInfo1 = ordersInfoService.createOrder(createOrderCommand);
		return ResponseEntity.ok(ordersInfo1);
	}
}
