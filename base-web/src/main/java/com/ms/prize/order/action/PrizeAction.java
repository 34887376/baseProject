package com.ms.prize.order.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import base.test.base.action.BaseAction;
import base.test.base.util.JsonUtil;

import com.ms.domain.action.front.result.SubmitPrizeOrderResult;
import com.ms.domain.action.prize.order.vo.PrizeOrderVO;
import com.ms.domain.business.constant.OrderStatusDict;
import com.ms.domain.convert.PrizeOrderConvert;
import com.ms.domain.prize.order.bo.CheckPrizeOrderResult;
import com.ms.domain.prize.order.bo.PrizeOrderBO;
import com.ms.domain.prize.order.bo.QueryPrizeOrderBOResult;
import com.ms.service.prize.face.IPrizeService;

public class PrizeAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private IPrizeService iPrizeService;
	
	//订单号
	private Long orderId;

	//收货人
	private String name;
	
	//收货地址
	private String address;
	
	//电话号码
	private Integer phone;
	
	//促销id
	private Long promotionId;
	
	//订单状态
	private Integer status;
	
	/**
	 * 领奖进入结算页
	 * @return
	 */
	public String takePrize(){
		try{
			QueryPrizeOrderBOResult prizeInfo = iPrizeService.queryTakePrizeDetailInfo(getPin(), promotionId);
			PrizeOrderVO prizeOrderVO = new PrizeOrderVO();
			Map<String, Object> parmKeyValue = new HashMap<String, Object>();
			if(prizeInfo.isSuccess()){
				prizeOrderVO  = PrizeOrderConvert.convertBOTOVO(prizeInfo.getPrizeOrderBO());
				parmKeyValue.put("prizeOrderVO",prizeOrderVO);
			}else{
				parmKeyValue.put("failMsg",prizeInfo.getMsg());
			}
	    	putParamToVm(parmKeyValue);
        }catch(Exception e){
        	logger.error("PrizeAction.takePrize方法查询过程中发生异常！！！", e);
        }
		return "takePrize";
	}
	
	/**
	 * 领奖进入结算页
	 * @return
	 */
	public String submitPrizeOrder(){
		SubmitPrizeOrderResult submitPrizeOrderResult = new SubmitPrizeOrderResult();
		try{
//			PrizeOrderVO prizeOrderVO = new PrizeOrderVO();
			PrizeOrderBO prizeOrderBO = new PrizeOrderBO();
			prizeOrderBO.setAddress(address);
			prizeOrderBO.setName(name);
			prizeOrderBO.setPhone(phone);
			prizeOrderBO.setPin(getPin());
			prizeOrderBO.setPromotionId(promotionId);
			prizeOrderBO.setYn(true);
			prizeOrderBO.setStatus(OrderStatusDict.NEW);
			CheckPrizeOrderResult checkResult = iPrizeService.checkPrizeOrder(prizeOrderBO );
			if(!checkResult.isSuccess()){
				submitPrizeOrderResult.setSuccess(false);
				submitPrizeOrderResult.setMsg(checkResult.getMsg());
			}else{
				long orderId = iPrizeService.addPrizeOrder(prizeOrderBO);
				if(orderId>1000){
					submitPrizeOrderResult.setOrderId(orderId);
					submitPrizeOrderResult.setSuccess(true);
					submitPrizeOrderResult.setMsg("正在努力提交订单中......");
				}else{
					submitPrizeOrderResult.setSuccess(false);
					submitPrizeOrderResult.setMsg("提交过程中发生异常，请稍后重试！！！");
				}
			}
			print(JsonUtil.toJson(submitPrizeOrderResult));
        }catch(Exception e){
        	logger.error("PrizeAction.takePrize方法查询过程中发生异常！！！", e);
        }
		return "takePrize";
	}

	public void setiPrizeService(IPrizeService iPrizeService) {
		this.iPrizeService = iPrizeService;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
}
