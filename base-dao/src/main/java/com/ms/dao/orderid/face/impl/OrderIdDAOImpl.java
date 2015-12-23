package com.ms.dao.orderid.face.impl;

import com.ms.dao.base.dao.BaseMysqlDAO;
import com.ms.dao.orderid.face.IOrderIdDAO;
import com.ms.domain.orderid.dao.OrderIdDAO;

public class OrderIdDAOImpl extends BaseMysqlDAO implements IOrderIdDAO {
	
	private static final String namespace="orderIDTable.";

	public long obtainOrderId() throws Exception {
		Object effectObj = (Object)this.insert(namespace+"queryNewOrderId", new OrderIdDAO());
		if(effectObj==null){
			return -1L;
		}
		if((Long)effectObj<=0){
			return -1L;
		}
		return ((Long)effectObj).longValue();
	}

}
