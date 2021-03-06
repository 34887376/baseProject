package com.ms.domain.action.prize.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PrizeOrderVO implements Serializable{

	private static final long serialVersionUID = 8326720758207796194L;

	//主键
	private Long id;
	
	//订单号
	private Long orderId;
	
	//用户名
	private String pin;

	//收货人
	private String name;
	
	//收货地址
	private String address;
	
	//电话号码
	private Long phone;
	
	//商品id
	private Long skuId;
	
	//商品数量
	private Integer skuNum;
	
	//商品名称
	private String skuName;
	
	//促销id
	private Long promotionId;
	
	//中奖等级
	private Integer prizeType;
	
	//促销价格
	private BigDecimal promotionPrice;
	
	//运费
	private BigDecimal frightPrice;
	
	//创建时间
	private String createTime;
	
	//过期时间
	private String invalidTime;
	
	//订单承运单号
	private String frightOrder;
	
	//订单承运商
	private String frightTrader;
	
	//订单状态id
	private Integer statusId;
	
	//订单状态文字描述
	private String statusStr;
	
	//订单是否有效
	private Boolean yn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
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


	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public Integer getPrizeType() {
		return prizeType;
	}

	public void setPrizeType(Integer prizeType) {
		this.prizeType = prizeType;
	}

	public BigDecimal getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(BigDecimal promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public BigDecimal getFrightPrice() {
		return frightPrice;
	}

	public void setFrightPrice(BigDecimal frightPrice) {
		this.frightPrice = frightPrice;
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


	public Boolean getYn() {
		return yn;
	}

	public void setYn(Boolean yn) {
		this.yn = yn;
	}

	public Integer getSkuNum() {
		return skuNum;
	}

	public void setSkuNum(Integer skuNum) {
		this.skuNum = skuNum;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}


	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(String invalidTime) {
		this.invalidTime = invalidTime;
	}

}
