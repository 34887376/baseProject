package com.ms.service.prize.face;

import java.util.List;

import com.ms.domain.prize.order.bo.CheckPrizeOrderResult;
import com.ms.domain.prize.order.bo.PrizeOrderBO;
import com.ms.domain.prize.order.bo.QueryPrizeOrderBOResult;

/**
 * 领奖后台服务
 * @author zhoushanjie
 *
 */
public interface IPrizeService {
	
	/**
	 * 校验订单信息是否正确，是否可以提交
	 * @param prizeOrderBO
	 * @return
	 */
	CheckPrizeOrderResult checkPrizeOrder(PrizeOrderBO prizeOrderBO);
	
	/**
	 * 添加中奖订单
	 * @param prizeOrderBO
	 * @return
	 */
	long addPrizeOrder(PrizeOrderBO prizeOrderBO);
	
	/**
	 * 根据用户pin按分页查询订单信息列表
	 * @param pin
	 * @return
	 */
	List<PrizeOrderBO> queryPrizeOrderListByPin(String pin);
	
	/**
	 * 根据用户pin和订单号修改订单状态
	 * @param pin
	 * @param orderId
	 * @return
	 */
	boolean updatePrizeOrderStatusByCondition(String pin, long orderId, int status);
	
	/**
	 * 根据用户pin和订单号修改订单信息
	 * @param pin
	 * @param orderId
	 * @return
	 */
	boolean updatePrizeOrderInfoByCondition(PrizeOrderBO prizeOrderBO);
	
	/**
	 * 根据用户pin和促销id查询用户下单页面信息
	 * @param pin
	 * @param promotionId
	 * @return
	 */
	QueryPrizeOrderBOResult queryTakePrizeDetailInfo(String pin,long promotionId);

}
