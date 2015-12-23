package com.ms.dao.prize.order.face;

import java.util.List;

import com.ms.domain.prize.order.dao.PrizeOrderDAO;

public interface IPrizeOrderDAO {
	
	/**
	 * 添加订单信息
	 * @param prizeOrderDAO
	 * @return
	 */
	long addPrizeOrder(PrizeOrderDAO prizeOrderDAO) throws Exception;
	
	/**
	 * 更新订单信息
	 * @return
	 */
	boolean updatePrizeOrder(PrizeOrderDAO prizeOrderDAO) throws Exception;
	
	/**
	 * 物理删除订单信息(需要根据pin和订单号，缺一不可)
	 * @return
	 */
	boolean delPrizeOrder(PrizeOrderDAO prizeOrderDAO) throws Exception;
	
	/**
	 * 根据条件批量
	 * @param prizeOrderDAO
	 * @return
	 */
	List<PrizeOrderDAO> queryPrizeOrderByCondition(PrizeOrderDAO prizeOrderDAO) throws Exception;
	
}
