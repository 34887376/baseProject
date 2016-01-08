package com.ms.domain.prize.order.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ms.domain.business.constant.OrderStatusDict;

public class PrizeOrderBO implements Serializable{

	private static final long serialVersionUID = 3188180757720941853L;
	
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
		private Date createTime;
		
		//过期时间
		private Date invalidTime;
		
		//订单承运单号
		private String frightOrder;
		
		//订单承运商
		private String frightTrader;
		
		//订单状态
		private Integer status;
		
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

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public Date getInvalidTime() {
			return invalidTime;
		}

		public void setInvalidTime(Date invalidTime) {
			this.invalidTime = invalidTime;
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
			if(invalidTime!=null && invalidTime.before(new Date())){
				return OrderStatusDict.INVALID;
			}else{
				return status;
			}
		}

		public void setStatus(Integer status) {
			this.status = status;
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


}
