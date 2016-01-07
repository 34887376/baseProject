package com.ms.domain.action.backstage;

import java.util.List;

import com.ms.domain.action.base.BaseActionResult;
import com.ms.domain.action.prize.order.vo.PrizeOrderVO;

/**
 * 订单列表后台管理返回结果对象
 * @author zhoushanjie
 *
 */
public class BackOrderListResult extends BaseActionResult{

	private static final long serialVersionUID = 187939573062964070L;
	
	private List<PrizeOrderVO> orderList;

	public BackOrderListResult() {
		super();
	}

	public List<PrizeOrderVO> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<PrizeOrderVO> orderList) {
		this.orderList = orderList;
	}

}
