package com.ms.domain.action.front.result;

import com.ms.domain.action.base.BaseActionResult;

/**
 * 抽奖结果，返回给前台用户
 * @author zhoushanjie
 *
 */
public class DrawLotteryResult extends BaseActionResult{

	private static final long serialVersionUID = 1L;
	
	//抽中将的等级
	private String lotteryLevel;
	
	//促销id
	private Long promotionId;
	
	//领奖的链接地址
	private String acceptLotteryLink;

	public String getLotteryLevel() {
		return lotteryLevel;
	}

	public void setLotteryLevel(String lotteryLevel) {
		this.lotteryLevel = lotteryLevel;
	}

	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public String getAcceptLotteryLink() {
		return acceptLotteryLink;
	}

	public void setAcceptLotteryLink(String acceptLotteryLink) {
		this.acceptLotteryLink = acceptLotteryLink;
	}

}
