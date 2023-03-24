package com.cm.order.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_items")
@Entity
public class OrdersInfo {

	@Id
	private Long id;
	private String productName;
	private String orderStatus;
	private int price;
}
