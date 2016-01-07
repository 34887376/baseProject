package com.ms.domain.convert;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import base.test.base.util.DateUtils;

import com.ms.domain.action.prize.order.vo.PrizeOrderVO;
import com.ms.domain.business.constant.OrderStatusEnum;
import com.ms.domain.prize.order.bo.PrizeOrderBO;
import com.ms.domain.prize.order.dao.PrizeOrderDAO;

public class PrizeOrderConvert {

	/**
	 * DAO转为BO
	 * @param prizeOrderDAO
	 * @return
	 */
	public static PrizeOrderBO convertDAOTOBO(PrizeOrderDAO prizeOrderDAO){
		PrizeOrderBO prizeOrderBO = new PrizeOrderBO();
		if(prizeOrderDAO==null){
			return prizeOrderBO;
		}
		prizeOrderBO.setId(prizeOrderDAO.getId());
		prizeOrderBO.setAddress(prizeOrderDAO.getAddress());
		prizeOrderBO.setCreateTime(prizeOrderDAO.getCreateTime());
		prizeOrderBO.setFrightOrder(prizeOrderDAO.getFrightOrder());
		prizeOrderBO.setFrightPrice(prizeOrderDAO.getFrightPrice());
		prizeOrderBO.setFrightTrader(prizeOrderDAO.getFrightTrader());
		prizeOrderBO.setInvalidTime(prizeOrderDAO.getInvalidTime());
		prizeOrderBO.setName(prizeOrderDAO.getName());
		prizeOrderBO.setOrderId(prizeOrderDAO.getOrderId());
		prizeOrderBO.setPhone(prizeOrderDAO.getPhone());
		prizeOrderBO.setPin(prizeOrderDAO.getPin());
		prizeOrderBO.setPrizeType(prizeOrderDAO.getPrizeType());
		prizeOrderBO.setPromotionId(prizeOrderDAO.getPromotionId());
		prizeOrderBO.setPromotionPrice(prizeOrderDAO.getPromotionPrice());
		prizeOrderBO.setSkuId(prizeOrderDAO.getSkuId());
		prizeOrderBO.setStatus(prizeOrderDAO.getStatus());
		prizeOrderBO.setYn(prizeOrderDAO.getYn());
		return prizeOrderBO;
	}
	
	/**
	 * BO转为DAO
	 * @param prizeOrderBO
	 * @return
	 */
	public static PrizeOrderDAO convertBOTODAO(PrizeOrderBO prizeOrderBO){
		PrizeOrderDAO prizeOrderDAO = new PrizeOrderDAO();
		if(prizeOrderBO==null){
			return prizeOrderDAO;
		}
		prizeOrderDAO.setId(prizeOrderBO.getId());
		prizeOrderDAO.setAddress(prizeOrderBO.getAddress());
		prizeOrderDAO.setCreateTime(prizeOrderBO.getCreateTime());
		prizeOrderDAO.setFrightOrder(prizeOrderBO.getFrightOrder());
		prizeOrderDAO.setFrightPrice(prizeOrderBO.getFrightPrice());
		prizeOrderDAO.setFrightTrader(prizeOrderBO.getFrightTrader());
		prizeOrderDAO.setInvalidTime(prizeOrderBO.getInvalidTime());
		prizeOrderDAO.setName(prizeOrderBO.getName());
		prizeOrderDAO.setOrderId(prizeOrderBO.getOrderId());
		prizeOrderDAO.setPhone(prizeOrderBO.getPhone());
		prizeOrderDAO.setPin(prizeOrderBO.getPin());
		prizeOrderDAO.setPrizeType(prizeOrderBO.getPrizeType());
		prizeOrderDAO.setPromotionId(prizeOrderBO.getPromotionId());
		prizeOrderDAO.setPromotionPrice(prizeOrderBO.getPromotionPrice());
		prizeOrderDAO.setSkuId(prizeOrderBO.getSkuId());
		prizeOrderDAO.setStatus(prizeOrderBO.getStatus());
		prizeOrderDAO.setYn(prizeOrderBO.getYn());
		return prizeOrderDAO;
	}
	
	/**
	 * 将DAO列表转化为BO列表
	 * @param prizeOrderDAOList
	 * @return
	 */
	public static List<PrizeOrderBO> convertDAOTOBOList(List<PrizeOrderDAO> prizeOrderDAOList){
		List<PrizeOrderBO> prizeOrderBOList = new ArrayList<PrizeOrderBO>();
		if(CollectionUtils.isEmpty(prizeOrderDAOList)){
			return prizeOrderBOList;
		}
		
		for(PrizeOrderDAO prizeOrderDAO : prizeOrderDAOList){
			if(prizeOrderDAO==null){
				continue;
			}
			PrizeOrderBO prizeOrderBO = new PrizeOrderBO();
			prizeOrderBO.setId(prizeOrderDAO.getId());
			prizeOrderBO.setAddress(prizeOrderDAO.getAddress());
			prizeOrderBO.setCreateTime(prizeOrderDAO.getCreateTime());
			prizeOrderBO.setFrightOrder(prizeOrderDAO.getFrightOrder());
			prizeOrderBO.setFrightPrice(prizeOrderDAO.getFrightPrice());
			prizeOrderBO.setFrightTrader(prizeOrderDAO.getFrightTrader());
			prizeOrderBO.setInvalidTime(prizeOrderDAO.getInvalidTime());
			prizeOrderBO.setName(prizeOrderDAO.getName());
			prizeOrderBO.setOrderId(prizeOrderDAO.getOrderId());
			prizeOrderBO.setPhone(prizeOrderDAO.getPhone());
			prizeOrderBO.setPin(prizeOrderDAO.getPin());
			prizeOrderBO.setPrizeType(prizeOrderDAO.getPrizeType());
			prizeOrderBO.setPromotionId(prizeOrderDAO.getPromotionId());
			prizeOrderBO.setPromotionPrice(prizeOrderDAO.getPromotionPrice());
			prizeOrderBO.setSkuId(prizeOrderDAO.getSkuId());
			prizeOrderBO.setStatus(prizeOrderDAO.getStatus());
			prizeOrderBO.setYn(prizeOrderDAO.getYn());
			prizeOrderBOList.add(prizeOrderBO);
		}
		return prizeOrderBOList;
	}
	
	/**
	 * Bo转Vo
	 * @param prizeOrderBO
	 * @return
	 */
	public static PrizeOrderVO convertBOTOVO(PrizeOrderBO prizeOrderBO){
		PrizeOrderVO prizeOrderVO = new PrizeOrderVO();
		if(prizeOrderBO==null){
			return prizeOrderVO;
		}
		prizeOrderVO.setId(prizeOrderBO.getId());
		prizeOrderVO.setAddress(prizeOrderBO.getAddress());
		prizeOrderVO.setCreateTime(DateUtils.getDateStr(prizeOrderBO.getCreateTime()));
		prizeOrderVO.setFrightOrder(prizeOrderBO.getFrightOrder());
		prizeOrderVO.setFrightPrice(prizeOrderBO.getFrightPrice());
		prizeOrderVO.setFrightTrader(prizeOrderBO.getFrightTrader());
		prizeOrderVO.setInvalidTime(DateUtils.getDateStr(prizeOrderBO.getInvalidTime()));
		prizeOrderVO.setName(prizeOrderBO.getName());
		prizeOrderVO.setOrderId(prizeOrderBO.getOrderId());
		prizeOrderVO.setPhone(prizeOrderBO.getPhone());
		prizeOrderVO.setPin(prizeOrderBO.getPin());
		prizeOrderVO.setPrizeType(prizeOrderBO.getPrizeType());
		prizeOrderVO.setPromotionId(prizeOrderBO.getPromotionId());
		prizeOrderVO.setPromotionPrice(prizeOrderBO.getPromotionPrice());
		prizeOrderVO.setSkuId(prizeOrderBO.getSkuId());
		prizeOrderVO.setSkuName(prizeOrderBO.getSkuName());
		prizeOrderVO.setSkuNum(prizeOrderBO.getSkuNum());
		prizeOrderVO.setStatusId(prizeOrderBO.getStatus());
		if(prizeOrderBO.getStatus()!=null){
			prizeOrderVO.setStatusStr(OrderStatusEnum.getStatusStrById(prizeOrderBO.getStatus()));
		}
		prizeOrderVO.setYn(prizeOrderBO.getYn());
		return prizeOrderVO;
	}
	
	/**
	 * VO转bo
	 * @param prizeOrderVO
	 * @return
	 */
	public static PrizeOrderBO convertVOTOBO(PrizeOrderVO prizeOrderVO){
		PrizeOrderBO prizeOrderBO = new PrizeOrderBO();
		if(prizeOrderVO==null){
			return prizeOrderBO;
		}
		prizeOrderBO.setId(prizeOrderVO.getId());
		prizeOrderBO.setAddress(prizeOrderVO.getAddress());
		prizeOrderBO.setCreateTime(DateUtils.getDate(prizeOrderVO.getCreateTime()));
		prizeOrderBO.setFrightOrder(prizeOrderVO.getFrightOrder());
		prizeOrderBO.setFrightPrice(prizeOrderVO.getFrightPrice());
		prizeOrderBO.setFrightTrader(prizeOrderVO.getFrightTrader());
		prizeOrderBO.setInvalidTime(DateUtils.getDate(prizeOrderVO.getInvalidTime()));
		prizeOrderBO.setName(prizeOrderVO.getName());
		prizeOrderBO.setOrderId(prizeOrderVO.getOrderId());
		prizeOrderBO.setPhone(prizeOrderVO.getPhone());
		prizeOrderBO.setPin(prizeOrderVO.getPin());
		prizeOrderBO.setPrizeType(prizeOrderVO.getPrizeType());
		prizeOrderBO.setPromotionId(prizeOrderVO.getPromotionId());
		prizeOrderBO.setPromotionPrice(prizeOrderVO.getPromotionPrice());
		prizeOrderBO.setSkuId(prizeOrderVO.getSkuId());
		prizeOrderBO.setSkuName(prizeOrderVO.getSkuName());
		prizeOrderBO.setSkuNum(prizeOrderVO.getSkuNum());
		prizeOrderBO.setStatus(prizeOrderVO.getStatusId());
		prizeOrderBO.setYn(prizeOrderVO.getYn());
		return prizeOrderBO;
	}
	
	/**
	 * BOlist转为VoList
	 * @param prizeOrderBOList
	 * @return
	 */
	public static List<PrizeOrderVO> convertBOTOVOList(List<PrizeOrderBO> prizeOrderBOList){
		List<PrizeOrderVO> prizeOrderVOList = new ArrayList<PrizeOrderVO>();
		if(CollectionUtils.isEmpty(prizeOrderBOList)){
			return prizeOrderVOList;
		}
		
		for(PrizeOrderBO prizeOrderBO : prizeOrderBOList){
			if(prizeOrderBO==null){
				continue;
			}
			PrizeOrderVO prizeOrderVO = new PrizeOrderVO();
			prizeOrderVO.setId(prizeOrderBO.getId());
			prizeOrderVO.setAddress(prizeOrderBO.getAddress());
			prizeOrderVO.setCreateTime(DateUtils.getDateStr(prizeOrderBO.getCreateTime()));
			prizeOrderVO.setFrightOrder(prizeOrderBO.getFrightOrder());
			prizeOrderVO.setFrightPrice(prizeOrderBO.getFrightPrice());
			prizeOrderVO.setFrightTrader(prizeOrderBO.getFrightTrader());
			prizeOrderVO.setInvalidTime(DateUtils.getDateStr(prizeOrderBO.getInvalidTime()));
			prizeOrderVO.setName(prizeOrderBO.getName());
			prizeOrderVO.setOrderId(prizeOrderBO.getOrderId());
			prizeOrderVO.setPhone(prizeOrderBO.getPhone());
			prizeOrderVO.setPin(prizeOrderBO.getPin());
			prizeOrderVO.setPrizeType(prizeOrderBO.getPrizeType());
			prizeOrderVO.setPromotionId(prizeOrderBO.getPromotionId());
			prizeOrderVO.setPromotionPrice(prizeOrderBO.getPromotionPrice());
			prizeOrderVO.setSkuId(prizeOrderBO.getSkuId());
			prizeOrderVO.setSkuName(prizeOrderBO.getSkuName());
			prizeOrderVO.setSkuNum(prizeOrderBO.getSkuNum());
			prizeOrderVO.setStatusId(prizeOrderBO.getStatus());
			prizeOrderVO.setStatusStr(OrderStatusEnum.getStatusStrById(prizeOrderBO.getStatus()));
			prizeOrderVO.setYn(prizeOrderBO.getYn());
			prizeOrderVOList.add(prizeOrderVO);
		}
		return prizeOrderVOList;
	}
	
}
