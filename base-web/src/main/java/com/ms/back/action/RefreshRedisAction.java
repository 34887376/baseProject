package com.ms.back.action;

import org.apache.log4j.Logger;

import com.ms.service.backStage.ladder.face.IBackLadderPromotionService;
import com.ms.service.backStage.ladder.face.IBackLadderService;
import com.ms.service.backStage.promotion.face.IBackPromotionSeqSerivce;
import com.ms.service.backStage.promotion.face.IBackPromotionService;
import com.ms.service.backStage.sku.face.IBackSkuService;

import base.test.base.action.BaseAction;

public class RefreshRedisAction extends BaseAction{

	private static final long serialVersionUID = 5004269485729656269L;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private IBackSkuService iBackSkuService;
	
	private IBackLadderService iBackLadderService;
	
	private IBackLadderPromotionService iBackLadderPromotionService;
	
	private IBackPromotionService iBackPromotionService;
	
	private IBackPromotionSeqSerivce iBackPromotionSeqSerivce;
	
	public String refreshAllInfoToRedis(){
		refreshSku();
		refreshLadder();
		refreshPromotion();
		refreshLadderPromotion();
		refreshPromotionSequence();
		return "refreshAllInfoToRedis";
	}
	
	public String refreshPromotionSequence(){
		try{
			iBackPromotionSeqSerivce.refreshPromotionSequenceToRedis();
		}catch(Exception e){
			logger.error("RefreshRedisAction.refreshPromotion刷新促销信息时发生异常！！！", e);
		}
		return "refreshPromotionSequence";
	}

	public String refreshPromotion(){
		try{
			iBackPromotionService.refreshPromotionInfoToRedis();
		}catch(Exception e){
			logger.error("RefreshRedisAction.refreshPromotion刷新促销信息时发生异常！！！", e);
		}
		return "refreshPromotion";
	}
	
	public String refreshLadder(){
		try{
			iBackLadderService.refreshLadderToRedis();
		}catch(Exception e){
			logger.error("RefreshRedisAction.refreshLadder刷新阶梯信息时发生异常！！！", e);
		}
		return "refreshLadder";
	}
	
	public String refreshLadderPromotion(){
		try{
			iBackLadderPromotionService.refreshLadderPromotionToRedis();
		}catch(Exception e){
			logger.error("RefreshRedisAction.refreshLadderPromotion刷新阶梯促销信息时发生异常！！！", e);
		}
		return "refreshLadderPromotion";
	}
	
	public String refreshSku(){
		try{
			iBackSkuService.refreshSkuToRedis();
		}catch(Exception e){
			logger.error("RefreshRedisAction.refreshSku刷新商品信息时发生异常！！！", e);
		}
		return "refreshSku";
	}

	public void setiBackSkuService(IBackSkuService iBackSkuService) {
		this.iBackSkuService = iBackSkuService;
	}

	public void setiBackLadderService(IBackLadderService iBackLadderService) {
		this.iBackLadderService = iBackLadderService;
	}

	public void setiBackLadderPromotionService(
			IBackLadderPromotionService iBackLadderPromotionService) {
		this.iBackLadderPromotionService = iBackLadderPromotionService;
	}

	public void setiBackPromotionService(IBackPromotionService iBackPromotionService) {
		this.iBackPromotionService = iBackPromotionService;
	}

	public void setiBackPromotionSeqSerivce(
			IBackPromotionSeqSerivce iBackPromotionSeqSerivce) {
		this.iBackPromotionSeqSerivce = iBackPromotionSeqSerivce;
	}
}
