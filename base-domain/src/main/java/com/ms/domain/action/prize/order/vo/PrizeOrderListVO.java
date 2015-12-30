package com.ms.domain.action.prize.order.vo;

import java.io.Serializable;
import java.util.List;

public class PrizeOrderListVO  implements Serializable{

	private static final long serialVersionUID = -6434473165070731130L;
	
	//订单列表
	private List<PrizeOrderVO> orderList;

	public List<PrizeOrderVO> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<PrizeOrderVO> orderList) {
		this.orderList = orderList;
	}

}
