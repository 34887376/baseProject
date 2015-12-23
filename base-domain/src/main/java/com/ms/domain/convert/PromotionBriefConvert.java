package com.ms.domain.convert;

import com.ms.domain.promotion.bo.PromotionBriefBOInfo;
import com.ms.domian.action.promotion.vo.PromotionBriefVOInfo;

public class PromotionBriefConvert {

	/**
	 * BO转为VO
	 * @param promotionBriefBOInfo
	 * @return
	 */
	public static final PromotionBriefVOInfo convertBOTOVO(PromotionBriefBOInfo promotionBriefBOInfo){
		PromotionBriefVOInfo promotionBriefVOInfo = new PromotionBriefVOInfo();
		if(promotionBriefBOInfo==null){
			return promotionBriefVOInfo;
		}
		promotionBriefVOInfo.setAdvert(promotionBriefBOInfo.getAdvert());
		promotionBriefVOInfo.setCheapestPromotionPrice(promotionBriefBOInfo.getCheapestPromotionPrice());
		promotionBriefVOInfo.setPromotionId(promotionBriefBOInfo.getPromotionId());
		promotionBriefVOInfo.setSkuImgUrl(promotionBriefBOInfo.getSkuImgUrl());
		promotionBriefVOInfo.setSkuName(promotionBriefBOInfo.getSkuName());
		promotionBriefVOInfo.setSkuNum(promotionBriefBOInfo.getSkuNum());
		promotionBriefVOInfo.setStatus(promotionBriefBOInfo.getStatus());
		promotionBriefVOInfo.setTopestDisconuntPercent(promotionBriefBOInfo.getTopestDisconuntPercent());
		return promotionBriefVOInfo;
	}
	
	/**
	 * VO转为BO
	 * @param promotionBriefVOInfo
	 * @return
	 */
	public static final PromotionBriefBOInfo convertBOTOVO(PromotionBriefVOInfo promotionBriefVOInfo){
		PromotionBriefBOInfo promotionBriefBOInfo = new PromotionBriefBOInfo();
		if(promotionBriefVOInfo==null){
			return promotionBriefBOInfo;
		}
		promotionBriefBOInfo.setAdvert(promotionBriefVOInfo.getAdvert());
		promotionBriefBOInfo.setCheapestPromotionPrice(promotionBriefVOInfo.getCheapestPromotionPrice());
		promotionBriefBOInfo.setPromotionId(promotionBriefVOInfo.getPromotionId());
		promotionBriefBOInfo.setSkuImgUrl(promotionBriefVOInfo.getSkuImgUrl());
		promotionBriefBOInfo.setSkuName(promotionBriefVOInfo.getSkuName());
		promotionBriefBOInfo.setSkuNum(promotionBriefVOInfo.getSkuNum());
		promotionBriefBOInfo.setStatus(promotionBriefVOInfo.getStatus());
		promotionBriefBOInfo.setTopestDisconuntPercent(promotionBriefVOInfo.getTopestDisconuntPercent());
		return promotionBriefBOInfo;
	}
}
