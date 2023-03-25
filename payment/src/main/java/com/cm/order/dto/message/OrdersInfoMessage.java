package com.cm.order.dto.message;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
public class OrdersInfoMessage {

	private Long id;
	private String productName;
	private String orderStatus;
	private String paymentStatus;
	private int price;
}
