package com.ms.domain.prize.order.bo;

import com.ms.domain.service.result.base.ServiceBaseResult;

public class QueryPrizeOrderBOResult extends ServiceBaseResult{

	private static final long serialVersionUID = 1L;
	
	//订单信息
	private PrizeOrderBO prizeOrderBO;

	public PrizeOrderBO getPrizeOrderBO() {
		return prizeOrderBO;
	}

	public void setPrizeOrderBO(PrizeOrderBO prizeOrderBO) {
		this.prizeOrderBO = prizeOrderBO;
	}
}
