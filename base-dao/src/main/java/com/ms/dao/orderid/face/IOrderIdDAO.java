package com.ms.dao.orderid.face;

public interface IOrderIdDAO {
	
	/**
	 * 获取订单号
	 * @return
	 */
	long obtainOrderId()  throws Exception;

}
