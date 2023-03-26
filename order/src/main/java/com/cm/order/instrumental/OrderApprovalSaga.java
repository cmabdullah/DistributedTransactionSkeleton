package com.cm.order.instrumental;

import com.cm.event.SagaStep;
import com.cm.order.domain.OrdersInfo;
import com.cm.order.domain.OrdersInfoRepository;
import com.cm.order.events.EmptyEvent;
import com.cm.order.events.OrderCancelledEvent;
import com.cm.order.ports.output.message.payment.OrderCancelledPaymentRequestMessagePublisher;
import com.cm.order.service.OrdersInfoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OrderApprovalSaga implements SagaStep<String, EmptyEvent, OrderCancelledEvent>  {

	private final OrdersInfoRepository ordersInfoRepository;
	private final OrdersInfoService ordersInfoService;
	private final ObjectMapper objectMapper;

	private final OrderCancelledPaymentRequestMessagePublisher orderCancelledPaymentRequestMessagePublisher;

	public OrderApprovalSaga(OrdersInfoRepository ordersInfoRepository, OrdersInfoService ordersInfoService, ObjectMapper objectMapper, OrderCancelledPaymentRequestMessagePublisher orderCancelledPaymentRequestMessagePublisher) {
		this.ordersInfoRepository = ordersInfoRepository;
		this.ordersInfoService = ordersInfoService;
		this.objectMapper = objectMapper;
		this.orderCancelledPaymentRequestMessagePublisher = orderCancelledPaymentRequestMessagePublisher;
	}

	@Override
	@Transactional
	public EmptyEvent process(String data) {
		//findOrder db call
		OrdersInfo ordersInfo = getOrder( data);
		ordersInfo.setOrderStatus("SUCCESS");
		//APPROVED
		ordersInfoRepository.save(ordersInfo);//saved the approved state
		log.info("restaurant approval request success, order new status saved {} ", ordersInfo);
		return new EmptyEvent();
	}

	@Override
	@Transactional
	public OrderCancelledEvent rollback(String data) {
		//findOrder
		OrdersInfo ordersInfo = getOrder( data);
		ordersInfo.setOrderStatus("CANCELLING");
		//CANCELLING

		ordersInfoRepository.save(ordersInfo);//saved the CANCELLING state
		return ordersInfoService.cancelOrderPayment(ordersInfo, orderCancelledPaymentRequestMessagePublisher);
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
