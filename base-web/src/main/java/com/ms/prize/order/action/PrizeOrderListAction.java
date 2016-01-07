package com.ms.prize.order.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import base.test.base.action.BaseAction;

import com.ms.domain.action.prize.order.vo.PrizeOrderVO;
import com.ms.domain.convert.PrizeOrderConvert;
import com.ms.domain.prize.order.bo.PrizeOrderBO;
import com.ms.service.prize.face.IPrizeService;

public class PrizeOrderListAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private IPrizeService iPrizeService;

	/**
	 * 展示订单列表
	 * @return
	 */
	public String showOrderList(){
		try{
			List<PrizeOrderBO> prizeOrderBOList = iPrizeService.queryPrizeOrderListByPin(getPin());
			List<PrizeOrderVO> prizeOrderVOList = new ArrayList<PrizeOrderVO>();
			Map<String, Object> parmKeyValue = new HashMap<String, Object>();
			if(CollectionUtils.isNotEmpty(prizeOrderBOList)){
				prizeOrderVOList = PrizeOrderConvert.convertBOTOVOList(prizeOrderBOList);
				parmKeyValue.put("prizeOrderVOList",prizeOrderVOList);
			}
//			for(int i=0;i<10;i++){
//				PrizeOrderVO prizeOrderVO = new PrizeOrderVO();
//				prizeOrderVO.setName("噢巴马啊");
//				prizeOrderVO.setPhone(12345678901L);
//				prizeOrderVO.setAddress("辽宁大连市高新园区GOLF高尔夫衬衫 男装秋款免烫衬衫男士长袖衬衫男 舒适透气印花衬衫 大蓝格 Y135991");
//				prizeOrderVO.setCreateTime(new Date());
//				prizeOrderVO.setFrightOrder("EMS20151225991122");
//				prizeOrderVO.setFrightTrader("中国邮政");
//				prizeOrderVO.setOrderId(201512251633L);
//				prizeOrderVO.setPrizeType(DrawLotteryResutlDict.HIT_FIRST_PRIZE);
//				prizeOrderVO.setPromotionPrice(new BigDecimal("100.12"));
//				prizeOrderVO.setSkuName("GOLF高尔夫衬衫 男装秋款免烫衬衫男士长袖衬衫男 舒适透气印花衬衫 大蓝格 Y135991");
//				prizeOrderVO.setSkuNum(1);
//				prizeOrderVO.setStatus(OrderStatusDict.COMPLETE);
//				prizeOrderVOList.add(prizeOrderVO);
//			}
			
//			parmKeyValue.put("prizeOrderVOList",prizeOrderVOList);
	    	putParamToVm(parmKeyValue);
        }catch(Exception e){
        	logger.error("PrizeOrderListAction.showOrderList方法查询订单列表过程中发生异常！！！", e);
        }
		return "showOrderList";
	}
	
	public void setiPrizeService(IPrizeService iPrizeService) {
		this.iPrizeService = iPrizeService;
	}

}
