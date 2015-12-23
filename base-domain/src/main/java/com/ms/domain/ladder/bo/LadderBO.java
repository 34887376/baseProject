package com.ms.domain.ladder.bo;

import java.io.Serializable;
import java.math.BigDecimal;

public class LadderBO implements Serializable {

	private static final long serialVersionUID = 3612098432966007895L;

	/**
	 * 阶梯促销规则id
	 */
	private Long id;
	
	/**
	 * 价格折扣比率(商品原价的70%)
	 */
	private BigDecimal priceDiscount;
	
	/**
	 * 商品数量占比(促销商品总数的百分比)
	 */
	private BigDecimal numPercent;
	
	/**
	 * 抽奖时的概率，分母
	 */
	private Long lotteryBaseNum;
	
	/**
	 * 抽奖时的概率，分子
	 */
	private Long lotteryHitNum;
	
	/**
	 * 阶梯类型一等奖，二等奖，三等奖参照字典DrawLotteryResutlDict
	 */
	private Integer type;
	
	/**
	 * 是否有效
	 */
	private Boolean yn;


	public BigDecimal getPriceDiscount() {
		return priceDiscount;
	}

	public void setPriceDiscount(BigDecimal priceDiscount) {
		this.priceDiscount = priceDiscount;
	}

	public BigDecimal getNumPercent() {
		return numPercent;
	}

	public void setNumPercent(BigDecimal numPercent) {
		this.numPercent = numPercent;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getYn() {
		return yn;
	}

	public void setYn(Boolean yn) {
		this.yn = yn;
	}

	public Long getLotteryBaseNum() {
		return lotteryBaseNum;
	}

	public void setLotteryBaseNum(Long lotteryBaseNum) {
		this.lotteryBaseNum = lotteryBaseNum;
	}

	public Long getLotteryHitNum() {
		return lotteryHitNum;
	}

	public void setLotteryHitNum(Long lotteryHitNum) {
		this.lotteryHitNum = lotteryHitNum;
	}
}
