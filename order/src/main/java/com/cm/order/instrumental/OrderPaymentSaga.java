package com.cm.order.instrumental;

import com.cm.event.DomainEvent;
import com.cm.event.SagaStep;
import com.cm.order.domain.OrdersInfo;
import com.cm.order.domain.OrdersInfoRepository;
import com.cm.order.events.EmptyEvent;
import com.cm.order.events.OrderPaidEvent;
import com.cm.order.ports.output.payment.OrderPaidRestaurantRequestMessagePublisher;
import com.cm.order.service.OrdersInfoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OrderPaymentSaga implements SagaStep<String, OrderPaidEvent, EmptyEvent> {

	private final OrdersInfoService ordersInfoService;
	private final ObjectMapper objectMapper;
	private final OrdersInfoRepository ordersInfoRepository;

	private final OrderPaidRestaurantRequestMessagePublisher orderPaidRestaurantRequestMessagePublisher;

	public OrderPaymentSaga(OrdersInfoService ordersInfoService, ObjectMapper objectMapper, OrdersInfoRepository ordersInfoRepository, OrderPaidRestaurantRequestMessagePublisher orderPaidRestaurantRequestMessagePublisher) {
		this.ordersInfoService = ordersInfoService;
		this.objectMapper = objectMapper;
		this.ordersInfoRepository = ordersInfoRepository;
		this.orderPaidRestaurantRequestMessagePublisher = orderPaidRestaurantRequestMessagePublisher;
	}

	@Override
	@Transactional
	public OrderPaidEvent process(String data) {
//		findOrder()
//		saveOrder(order)
		OrdersInfo ordersInfo = getOrder(data);
		OrderPaidEvent orderPaidEvent = ordersInfoService.payOrder(ordersInfo, orderPaidRestaurantRequestMessagePublisher);
		ordersInfoRepository.save(ordersInfo);// update payment status as paid
		log.info("saved paid status {}", ordersInfo);
		return orderPaidEvent;
	}

	@Override
	@Transactional
	public EmptyEvent rollback(String data) {

		//validate
		//findOrder
		//
		OrdersInfo ordersInfo = getOrder(data);
		ordersInfo.setOrderStatus("CANCELLED");
		ordersInfoRepository.save(ordersInfo);
		log.info("payment failed, updated order status {} no event need to be fired", ordersInfo);
		return new EmptyEvent();
	}



	private OrdersInfo getOrder(String message) {
		OrdersInfo ordersInfo = null;
		try {
			ordersInfo = objectMapper.readValue(message, new TypeReference<>() {
			});

		} catch (
				JsonProcessingException e) {
			log.error("parse failed err: {}", e.getLocalizedMessage());
		}

		return ordersInfo;
	}
}
