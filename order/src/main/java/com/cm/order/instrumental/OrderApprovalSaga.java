package com.cm.order.instrumental;

import com.cm.event.SagaStep;
import com.cm.order.domain.OrdersInfo;
import com.cm.order.domain.OrdersInfoRepository;
import com.cm.order.events.EmptyEvent;
import com.cm.order.events.OrderCancelledEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OrderApprovalSaga implements SagaStep<String, EmptyEvent, OrderCancelledEvent>  {

	private final OrdersInfoRepository ordersInfoRepository;
	private final ObjectMapper objectMapper;

	public OrderApprovalSaga(OrdersInfoRepository ordersInfoRepository, ObjectMapper objectMapper) {
		this.ordersInfoRepository = ordersInfoRepository;
		this.objectMapper = objectMapper;
	}

	@Override
	@Transactional
	public EmptyEvent process(String data) {
		//findOrder db call
		OrdersInfo ordersInfo = getOrder( data);
		ordersInfo.setOrderStatus("APPROVED-RESTAURANT");
		//APPROVED
		ordersInfoRepository.save(ordersInfo);//saved the approved state
		log.info("restaurant approval request success, order new status saved {} ", ordersInfo);
		return new EmptyEvent();
	}

	@Override
	@Transactional
	public OrderCancelledEvent rollback(String data) {
		//findOrder
		return null;
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
