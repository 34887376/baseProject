package com.ms.domain.action.prize.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ms.domain.action.base.BaseActionResult;

public class OrderSuccessVO extends BaseActionResult implements Serializable{

	private static final long serialVersionUID = -5211972843454530331L;
	
	private Long orderId;
	
	private BigDecimal orderPrice;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}
	

}
