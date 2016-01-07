package com.ms.prize.order.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import base.test.base.action.BaseAction;
import base.test.base.util.JsonUtil;

import com.ms.domain.action.front.result.SubmitPrizeOrderResult;
import com.ms.domain.action.prize.order.vo.OrderSuccessVO;
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
	private Long phone;
	
	//促销id
	private Long promotionId;
	
	//商品id
	private Long skuId;
	
	//订单价格
	private BigDecimal orderPrice;
	
	//订单状态
	private Integer status;
	
	/**
	 * 领奖进入结算页
	 * @return
	 */
	public String takePrize(){
		try{
			Map<String, Object> parmKeyValue = new HashMap<String, Object>();
			if(promotionId==null|| promotionId<10000){
				parmKeyValue.put("failMsg","请自觉按照规则进行抽奖！！！");
			}
			QueryPrizeOrderBOResult prizeInfo = iPrizeService.queryTakePrizeDetailInfo(getPin(), promotionId);
			PrizeOrderVO prizeOrderVO = new PrizeOrderVO();
			
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
	 * 提交订单
	 * @return
	 */
	public String submitPrizeOrder(){
		SubmitPrizeOrderResult submitPrizeOrderResult = new SubmitPrizeOrderResult();
		submitPrizeOrderResult.setMsg("提交过程中发生异常，请稍后重试！！！");
		try{
//			PrizeOrderVO prizeOrderVO = new PrizeOrderVO();
			PrizeOrderBO prizeOrderBO = new PrizeOrderBO();
			prizeOrderBO.setAddress(address);
			prizeOrderBO.setName(name);
			prizeOrderBO.setPhone(phone);
			prizeOrderBO.setPin(getPin());
			prizeOrderBO.setPromotionId(promotionId);
			prizeOrderBO.setSkuId(skuId);
			prizeOrderBO.setSkuNum(1);
			prizeOrderBO.setPromotionPrice(orderPrice);
			prizeOrderBO.setFrightPrice(new BigDecimal("8.00"));
			prizeOrderBO.setYn(true);
			prizeOrderBO.setStatus(OrderStatusDict.NEW);
			CheckPrizeOrderResult checkResult = iPrizeService.checkPrizeOrder(prizeOrderBO);
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
		return "submitPrizeOrder";
	}
	
	/**
	 * 跳转到成功也
	 * @return
	 */
	public String showSuccessOrder(){
		OrderSuccessVO orderSuccessVO= new OrderSuccessVO();
		orderSuccessVO.setSuccess(false);
		try{
			if(orderId==null || orderId < 1000){
				orderSuccessVO.setMsg("订单信息错误！！！请核对！！！");
			}
			PrizeOrderBO prizeOrderInfo = iPrizeService.queryPrizeOrderByPinOrderId(getPin(), orderId);
			if(prizeOrderInfo!=null){
				orderSuccessVO.setOrderId(orderId);
				orderSuccessVO.setOrderPrice(prizeOrderInfo.getPromotionPrice());
				orderSuccessVO.setSuccess(true);
			}
			Map<String, Object> parmKeyValue = new HashMap<String, Object>();
			parmKeyValue.put("orderSuccessVO",orderSuccessVO);
			putParamToVm(parmKeyValue);
        }catch(Exception e){
        	logger.error("PrizeAction.showSuccessOrder展示成功订单信息方法查询过程中发生异常！！！", e);
        }
		return "showSuccessOrder";
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

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	
}
