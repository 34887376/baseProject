package com.ms.back.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import base.test.base.action.BaseAction;
import base.test.base.util.JsonUtil;

import com.ms.domain.action.backstage.BackOrderListResult;
import com.ms.domain.action.prize.order.vo.PrizeOrderVO;
import com.ms.domain.convert.PrizeOrderConvert;
import com.ms.domain.prize.order.bo.PrizeOrderBO;
import com.ms.service.prize.face.IPrizeService;

public class OrderListManagerAction extends BaseAction{

	private static final long serialVersionUID = -2039395775655710395L;

	private Logger logger = Logger.getLogger(this.getClass());
	
	private IPrizeService iPrizeService;
	
	//订单号
	private Long orderId;
	
	//订单状态
	private Integer status;
	
	//快递单号
	private String frightOrder;
	
	//快递公司
	private String frightTrader;
	
	//用户pin
	private String pin;
	
	public String index(){
		return "index";
	}
	
	public String changeOrderStatus(){
		BackOrderListResult backOrderListResult = new BackOrderListResult();
		backOrderListResult.setSuccess(false);
		backOrderListResult.setMsg("处理异常！！！");
		try{
			boolean result = iPrizeService.updatePrizeOrderStatusByCondition(pin, orderId, status);
			if(result){
				backOrderListResult.setSuccess(true);
				backOrderListResult.setMsg("更新成功！！！");
			}else{
				backOrderListResult.setSuccess(false);
				backOrderListResult.setMsg("更新失败！！！");
			}
			
		}catch(Exception e){
			logger.error("OrderListManagerAction.changeOrderStatus更新订单状态信息异常！！！", e);
		}
		print(JsonUtil.toJson(backOrderListResult));
		return "changeOrderStatus";
	}
	
	public String updateOrderFrightInfo(){
		BackOrderListResult backOrderListResult = new BackOrderListResult();
		backOrderListResult.setSuccess(false);
		backOrderListResult.setMsg("处理异常！！！");
		try{
			PrizeOrderBO prizeOrderBO = new PrizeOrderBO();
			prizeOrderBO.setPin(pin);
			prizeOrderBO.setStatus(status);
			prizeOrderBO.setOrderId(orderId);
			prizeOrderBO.setFrightOrder(frightOrder);
			prizeOrderBO.setFrightTrader(frightTrader);
			boolean result = iPrizeService.updatePrizeOrderInfoByCondition(prizeOrderBO );
			if(result){
				backOrderListResult.setSuccess(true);
				backOrderListResult.setMsg("更新成功！！！");
			}else{
				backOrderListResult.setSuccess(false);
				backOrderListResult.setMsg("更新失败！！！");
			}
			
		}catch(Exception e){
			logger.error("OrderListManagerAction.updateOrderFrightInfo更新订单状态信息异常！！！", e);
		}
		print(JsonUtil.toJson(backOrderListResult));
		return "updateOrderFrightInfo";
	}
	
	/**
	 * 查询订单列表
	 * @return
	 */
	public String queryOrderList(){
		BackOrderListResult backOrderListResult = new BackOrderListResult();
		backOrderListResult.setSuccess(false);
		backOrderListResult.setMsg("处理异常！！！");
		try{
			PrizeOrderBO prizeOrderBO = iPrizeService.queryPrizeOrderByPinOrderId(pin, orderId);
			
			List<PrizeOrderVO> prizeOrderVOList = new ArrayList<PrizeOrderVO>();
			if(prizeOrderBO!=null){
				prizeOrderVOList.add(PrizeOrderConvert.convertBOTOVO(prizeOrderBO));
				backOrderListResult.setSuccess(true);
				backOrderListResult.setMsg("查询成功！！！");
				backOrderListResult.setOrderList(prizeOrderVOList);
			}
//	    	Map<String, Object> parmKeyValue = new HashMap<String, Object>();
//	    	parmKeyValue.put("ladderList",prizeOrderVOList);
//	    	putParamToVm(parmKeyValue);
        }catch(Exception e){
        	logger.error("PrizeOrderListAction.showOrderList方法查询订单列表过程中发生异常！！！", e);
        }
		print(JsonUtil.toJson(backOrderListResult));
		return "showOrderList";
	}

	public void setiPrizeService(IPrizeService iPrizeService) {
		this.iPrizeService = iPrizeService;
	}

	public String getFrightOrder() {
		return frightOrder;
	}

	public void setFrightOrder(String frightOrder) {
		this.frightOrder = frightOrder;
	}

	public String getFrightTrader() {
		return frightTrader;
	}

	public void setFrightTrader(String frightTrader) {
		this.frightTrader = frightTrader;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
}
