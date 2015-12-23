package com.ms.dao.prize.order.face.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.ms.dao.base.dao.BaseMysqlDAO;
import com.ms.dao.prize.order.face.IPrizeOrderDAO;
import com.ms.domain.prize.order.dao.PrizeOrderDAO;
import com.ms.domain.promotion.dao.PromotionDAO;

public class PrizeOrderDAOImpl extends BaseMysqlDAO implements IPrizeOrderDAO {

	private static final String namespace="orderTable.";
	
	public long addPrizeOrder(PrizeOrderDAO prizeOrderDAO)  throws Exception{
		if(prizeOrderDAO==null || prizeOrderDAO.getSkuId()==null){
			return -1L;
		}
		Object effectObj = (Object)this.insert(namespace+"addPrizeOrder", prizeOrderDAO);
		if(effectObj==null){
			return -1L;
		}
		if((Long)effectObj<=0){
			return -1L;
		}
		return ((Long)effectObj).longValue();
	}

	public boolean updatePrizeOrder(PrizeOrderDAO prizeOrderDAO)  throws Exception {
		if(prizeOrderDAO==null){
			return false;
		}
		int effectRow = this.update(namespace+"updatePrizeOrder", prizeOrderDAO);
		if(effectRow>0){
			return true;
		}
		return false;
	}

	public boolean delPrizeOrder(PrizeOrderDAO prizeOrderDAO) throws Exception{
		if(prizeOrderDAO==null || prizeOrderDAO.getOrderId()==null || prizeOrderDAO.getPin()==null){
			return false;
		}
		int effectRow = this.delete(namespace+"delPrizeOrder", prizeOrderDAO);
		if(effectRow>0){
			return true;
		}
		return false;
	}

	public List<PrizeOrderDAO> queryPrizeOrderByCondition(PrizeOrderDAO prizeOrderDAO)  throws Exception {
		if(prizeOrderDAO==null){
			return new ArrayList<PrizeOrderDAO>();
		}
		List<PrizeOrderDAO> prizeOrderList = this.queryForList(namespace+"queryPrizeOrderByCondition", prizeOrderDAO);
		return prizeOrderList;
	}
	
	public List<PrizeOrderDAO> queryPrizeOrderByCondition(int page, int pageSize,String pin)  throws Exception {
		if(pin==null){
			return new ArrayList<PrizeOrderDAO>();
		}
		if(page< 0 || pageSize<0){
			return new ArrayList<PrizeOrderDAO>();
		}
		Map<String,Integer> paramMap = new HashMap<String,Integer>();
		paramMap.put("startIndex", (page-1)*pageSize);
		paramMap.put("endIndex", page*pageSize);
		List<PrizeOrderDAO> prizeOrderList = this.queryForList(namespace+"queryPrizeOrderListByPageNum", paramMap);
		return prizeOrderList;
	}

}
