package com.ms.domain.action.front.result;

import com.ms.domain.action.base.BaseActionResult;

public class SubmitPrizeOrderResult extends BaseActionResult{


	private static final long serialVersionUID = -4564932184296821507L;

	//订单号
	private Long orderId;
	
	public SubmitPrizeOrderResult() {
		super();
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

}
