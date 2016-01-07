package com.ms.domain.business.constant;

/**
 * 订单状态字典
 * @author zhoushanjie
 *
 */
public class OrderStatusDict {

	//刚提交的新订单
	public static final int NEW = 11;
	
	//已经支付完成的订单
	public static final int PAYED = 12;
	
	//配送中的订单
	public static final int SENDING = 13;
	
	//完成的订单
	public static final int COMPLETE = 100;
	
	//无效的订单
	public static final int INVALID = 400;
}
