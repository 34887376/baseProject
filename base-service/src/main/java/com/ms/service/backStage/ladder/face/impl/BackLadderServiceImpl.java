package com.ms.service.backStage.ladder.face.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import base.test.base.util.JsonUtil;

import com.ms.dao.ladder.face.ILadderDAO;
import com.ms.dao.promotion.redis.IPromotionRedis;
import com.ms.domain.convert.LadderConvert;
import com.ms.domain.ladder.bo.LadderBO;
import com.ms.domain.ladder.dao.LadderDAO;
import com.ms.redis.constant.RedisKeyPrefixConstant;
import com.ms.service.backStage.ladder.face.IBackLadderService;

public class BackLadderServiceImpl implements IBackLadderService {

	private Logger logger = Logger.getLogger(this.getClass());
	
	private ILadderDAO iLadderDAO;
	
	//操作redis的方法
	private IPromotionRedis iPromotionRedis;
	
	public List<LadderBO> queryLadderByPageNum(int page, int pageSize) {
		List<LadderBO> ladderBOList = new ArrayList<LadderBO>();
		try{
			List<LadderDAO> ladderList = iLadderDAO.queryLadderListByPageNum(page, pageSize);
			ladderBOList = LadderConvert.convertDAOTOBOList(ladderList);
		}catch(Exception e){
			logger.error("BackLadderServiceImpl.queryLadderByPageNum查询阶梯规则信息时发生异常！！！", e);
		}
		return ladderBOList;
	}

	public List<LadderBO> queryLadderByCondition(LadderBO ladderBO) {
		List<LadderBO> ladderBOList = new ArrayList<LadderBO>();
		try{
			LadderDAO ladderDAO = LadderConvert.convertBOTODAO(ladderBO);
			List<LadderDAO> ladderList = iLadderDAO.queryLadderListByCondition(ladderDAO );
			ladderBOList = LadderConvert.convertDAOTOBOList(ladderList);
		}catch(Exception e){
			logger.error("BackLadderServiceImpl.queryLadderByCondition查询阶梯规则信息时发生异常！！！", e);
		}
		return ladderBOList;
	}

	public boolean delLadder(List<Long> idList) {
		try{
			if(CollectionUtils.isEmpty(idList)){
				return false;
			}
			boolean isOk=true;
			for(Long id: idList){
				if(iLadderDAO.delLadder(id)==false){
					isOk=false;
				}
			}
			return isOk;
		}catch(Exception e){
			logger.error("BackLadderServiceImpl.delLadder删除阶梯规则信息时发生异常！！！", e);
		}
		return false;
	}

	public boolean updateLadder(LadderBO ladderBO) {
		try{
			LadderDAO ladderDAO = LadderConvert.convertBOTODAO(ladderBO);
			return iLadderDAO.updateLadder(ladderDAO);
		}catch(Exception e){
			logger.error("BackLadderServiceImpl.updateLadder更新阶梯规则信息时发生异常！！！", e);
		}
		return false;
	}

	public boolean addLadder(LadderBO ladderBO) {
		try{
			LadderDAO ladderDAO = LadderConvert.convertBOTODAO(ladderBO);
			long id= iLadderDAO.addLadder(ladderDAO);
			if(id>0){
				return true;
			}
		}catch(Exception e){
			logger.error("BackLadderServiceImpl.addLadder添加阶梯规则信息时发生异常！！！", e);
		}
		return false;
	}

	public void refreshLadderToRedis(){
		try{
			LadderBO ladderDAO = new LadderBO();
			ladderDAO.setYn(true);
			//然后查询数据库
			List<LadderBO> ladderBOFromDBList = queryLadderByCondition(ladderDAO );
			if(CollectionUtils.isEmpty(ladderBOFromDBList)){
				return;
			}
			for(LadderBO ladderBOFromDB : ladderBOFromDBList){
				if(ladderBOFromDB!=null){
					String ladderStrFromDB = JsonUtil.toJson(ladderBOFromDB);
					iPromotionRedis.setValue(RedisKeyPrefixConstant.LADDER_PRIFIXE+String.valueOf(ladderBOFromDB.getId()), ladderStrFromDB, RedisKeyPrefixConstant.LADDER_TIME);
				}
			}
		}catch(Exception e){
			logger.error("BackLadderServiceImpl.refreshLadderToRedis往redis中刷新数据的过程中发生异常！！！", e);
		}
	}
	
	public void setiLadderDAO(ILadderDAO iLadderDAO) {
		this.iLadderDAO = iLadderDAO;
	}

	public void setiPromotionRedis(IPromotionRedis iPromotionRedis) {
		this.iPromotionRedis = iPromotionRedis;
	}

}
