package com.ms.service.backStage.ladder.face.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;

import base.test.base.util.JsonUtil;

import com.ms.dao.ladderpromotion.face.ILadderPromotionDAO;
import com.ms.dao.promotion.redis.IPromotionRedis;
import com.ms.domain.convert.LadderPromotionConvert;
import com.ms.domain.ladderpromotion.bo.LadderPromotionBO;
import com.ms.domain.ladderpromotion.dao.LadderPromotionDAO;
import com.ms.redis.constant.RedisKeyPrefixConstant;
import com.ms.service.backStage.ladder.face.IBackLadderPromotionService;

public class BackLadderPromotionServiceImpl implements IBackLadderPromotionService {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private ILadderPromotionDAO iLadderPromotionDAO;
	
	//操作redis的方法
	private IPromotionRedis iPromotionRedis;

	public List<LadderPromotionBO> queryLadderPromotionByPageNum(int page,
			int pageSize) {
		List<LadderPromotionBO> ladderPromotionBOList = new ArrayList<LadderPromotionBO>();
		try{
			List<LadderPromotionDAO> ladderPromotionDAOList = iLadderPromotionDAO.querySkuListByPageNum(page, pageSize);
			ladderPromotionBOList = LadderPromotionConvert.convertDAOTOBOList(ladderPromotionDAOList);
		}catch(Exception e){
			logger.error("BackLadderPromotionServiceImpl.queryLadderPromotionByPageNum查询阶梯促销信息时发生异常！！！", e);
		}
		return ladderPromotionBOList;
	}

	public List<LadderPromotionBO> queryLadderPromotionByCondition(
			LadderPromotionBO ladderPromotionBO) {
		List<LadderPromotionBO> ladderPromotionBOList = new ArrayList<LadderPromotionBO>();
		try{
			LadderPromotionDAO ladderPromotionDAO = LadderPromotionConvert.convertBOTODAO(ladderPromotionBO);
			List<LadderPromotionDAO> ladderPromotionDAOList = iLadderPromotionDAO.queryLadderPromoitonByCondition(ladderPromotionDAO );
			ladderPromotionBOList = LadderPromotionConvert.convertDAOTOBOList(ladderPromotionDAOList);
		}catch(Exception e){
			logger.error("BackLadderPromotionServiceImpl.queryLadderPromotionByCondition查询阶梯促销信息时发生异常！！！入参ladderPromotionBO={"+JsonUtil.toJson(ladderPromotionBO)+"}", e);
		}
		return ladderPromotionBOList;
	}

	public boolean addLadderPromotion(LadderPromotionBO ladderPromotionBO) {
		try{
			LadderPromotionDAO ladderPromotionDAO = LadderPromotionConvert.convertBOTODAO(ladderPromotionBO);
			long id= iLadderPromotionDAO.addLadderPromotion(ladderPromotionDAO);
			if(id>0){
				return true;
			}
		}catch(Exception e){
			logger.error("BackLadderPromotionServiceImpl.addLadderPromotion添加阶梯促销信息时发生异常！！！入参ladderPromotionBO={"+JsonUtil.toJson(ladderPromotionBO)+"}", e);
		}
		return false;
	}

	public boolean updateLadderPromotion(LadderPromotionBO ladderPromotionBO) {
		try{
			LadderPromotionDAO ladderPromotionDAO = LadderPromotionConvert.convertBOTODAO(ladderPromotionBO);
			boolean updateResult= iLadderPromotionDAO.updateLadderPromotion(ladderPromotionDAO);
			if(updateResult){
				return true;
			}
		}catch(Exception e){
			logger.error("BackLadderPromotionServiceImpl.updateLadderPromotion更新阶梯促销信息时发生异常！！！入参ladderPromotionBO={"+JsonUtil.toJson(ladderPromotionBO)+"}", e);
		}
		return false;
	}

	public boolean delLadderPromotion(List<Long> idList) {
		try{
			boolean updateResult= iLadderPromotionDAO.delLadderPromotion(idList);
			if(updateResult){
				return true;
			}
		}catch(Exception e){
			logger.error("BackLadderPromotionServiceImpl.updateLadderPromotion删除阶梯促销信息时发生异常！！！入参idList={"+JsonUtil.toJson(idList)+"}", e);
		}
		return false;
	}
	

	public void refreshLadderPromotionToRedis() {
		try{
			LadderPromotionBO ladderPromotionBO = new LadderPromotionBO();
			ladderPromotionBO.setYn(true);
			List<LadderPromotionBO> ladderPromotionBOList = queryLadderPromotionByCondition(ladderPromotionBO);
			if(CollectionUtils.isEmpty(ladderPromotionBOList)){
				return;
			}
			Map<Long, List<LadderPromotionBO>> ladderPromotionMap = new HashMap<Long, List<LadderPromotionBO>>();
			for(LadderPromotionBO ladderPromotionFromDB : ladderPromotionBOList){
				List<LadderPromotionBO> ladderPromotionList = ladderPromotionMap.get(ladderPromotionFromDB.getPromotionId());
				if(CollectionUtils.isEmpty(ladderPromotionList)){
					ladderPromotionList = new ArrayList<LadderPromotionBO>();
					ladderPromotionList.add(ladderPromotionFromDB);
					ladderPromotionMap.put(ladderPromotionFromDB.getPromotionId(), ladderPromotionList);
				}else{
					ladderPromotionList.add(ladderPromotionFromDB);
				}
			}
			
			if(MapUtils.isEmpty(ladderPromotionMap)){
				return;
			}
			
			for(Entry<Long,List<LadderPromotionBO>> mapEntry : ladderPromotionMap.entrySet()){
				if(CollectionUtils.isNotEmpty(mapEntry.getValue())){
					String ladderPromotionRedisStr = JsonUtil.toJson(mapEntry.getValue());
					iPromotionRedis.setValue(RedisKeyPrefixConstant.LADDER_PROMOTION_PRIFIXE+String.valueOf(mapEntry.getKey()), ladderPromotionRedisStr, RedisKeyPrefixConstant.LADDER_PROMOTION_TIME);
				}
			}
		}catch(Exception e){
			logger.error("BackLadderPromotionServiceImpl.refreshLadderPromotionToRedis刷新阶梯促销信息到redis时发生异常！！！", e);
		}
	}

	public void setiLadderPromotionDAO(ILadderPromotionDAO iLadderPromotionDAO) {
		this.iLadderPromotionDAO = iLadderPromotionDAO;
	}

	public void setiPromotionRedis(IPromotionRedis iPromotionRedis) {
		this.iPromotionRedis = iPromotionRedis;
	}

}
