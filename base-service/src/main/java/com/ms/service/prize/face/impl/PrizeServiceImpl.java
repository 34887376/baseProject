package com.ms.service.prize.face.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import base.test.base.util.JsonUtil;

import com.ms.dao.orderid.face.IOrderIdDAO;
import com.ms.dao.prize.order.face.IPrizeOrderDAO;
import com.ms.dao.promotion.redis.IPromotionRedis;
import com.ms.domain.action.prize.order.vo.PrizeOrderVO;
import com.ms.domain.business.constant.OrderStatusDict;
import com.ms.domain.convert.PrizeOrderConvert;
import com.ms.domain.ladder.bo.LadderBO;
import com.ms.domain.ladderpromotion.bo.LadderPromotionBO;
import com.ms.domain.prize.order.bo.CheckPrizeOrderResult;
import com.ms.domain.prize.order.bo.PrizeOrderBO;
import com.ms.domain.prize.order.bo.QueryPrizeOrderBOResult;
import com.ms.domain.prize.order.dao.PrizeOrderDAO;
import com.ms.domain.promotion.bo.PromotionBO;
import com.ms.domain.sku.bo.SkuBO;
import com.ms.redis.constant.RedisKeyPrefixConstant;
import com.ms.service.ladder.face.ILadderService;
import com.ms.service.ladderpromotion.face.ILadderPromotionService;
import com.ms.service.prize.face.IPrizeService;
import com.ms.service.promotion.face.IPromotionService;
import com.ms.service.sku.face.ISkuService;

public class PrizeServiceImpl implements IPrizeService {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	//订单dao层
	public IPrizeOrderDAO iPrizeOrderDAO;
	
	//获取订单号的方法
	public IOrderIdDAO iOrderIdDAO;
	
	//操作redis的方法
	private IPromotionRedis iPromotionRedis;
	
	//查询促销信息service
	private IPromotionService iPromotionService;
	
	//商品信息service
	private ISkuService iSkuService;
	
	//阶梯促销规则service
	private ILadderPromotionService iLadderPromotionService;
	
	//阶梯规则信息service
	private ILadderService iLadderService;

	public CheckPrizeOrderResult checkPrizeOrder(PrizeOrderBO prizeOrderBO) {
		CheckPrizeOrderResult checkPrizeOrderResult = new CheckPrizeOrderResult();
		checkPrizeOrderResult.setSuccess(false);
		try{
			long promotionId = prizeOrderBO.getPromotionId();
			long orderSkuId = prizeOrderBO.getSkuId();
			String pin = prizeOrderBO.getPin();
			BigDecimal orderPrice= prizeOrderBO.getPromotionPrice();
			BigDecimal orderFrightPrice= prizeOrderBO.getFrightPrice();
			
			//检查是否提交过订单
			String submitOrderRecordRedisKey = RedisKeyPrefixConstant.ORDER_SUBMIT_RECORD_PRIFIXE+prizeOrderBO.getPin()+prizeOrderBO.getPromotionId();
			String hasRecord = iPromotionRedis.getValue(submitOrderRecordRedisKey);
			if(StringUtils.isNotBlank(hasRecord)){
				checkPrizeOrderResult.setMsg("你已经提交过订单了，去看看别的商品吧！！！");
				return checkPrizeOrderResult;
			}
			
			//检查是否抽中过奖
			String lotteryLevelRedisKey = RedisKeyPrefixConstant.USER_LOTTERY_LEVEL_PRIFIXE+pin+promotionId;
			String lotteryLevel = iPromotionRedis.getValue(lotteryLevelRedisKey);
			if(StringUtils.isBlank(lotteryLevel)){
				checkPrizeOrderResult.setMsg("中奖信息错误，请核对中奖信息！！！");
				return checkPrizeOrderResult;
			}
			
			PromotionBO promotionInfo = iPromotionService.queryPromotionById(promotionId);
			if(promotionInfo.getSkuId() != orderSkuId){
				checkPrizeOrderResult.setMsg("商品信息错误！！！");
				return checkPrizeOrderResult;
			}
			if(!checkPrice(promotionId, orderSkuId, orderPrice, Integer.valueOf(lotteryLevel))){
				checkPrizeOrderResult.setMsg("价格信息错误，请重刷新页面重新提交！！！");
				return checkPrizeOrderResult;
			}
			checkPrizeOrderResult.setSuccess(true);
			checkPrizeOrderResult.setMsg("订单校验成功！！！");
			return checkPrizeOrderResult;
		}catch(Exception e){
			logger.error("PrizeServiceImpl.checkPrizeOrder检查订单信息合法性时发生异常，入参{prizeOrderBO="+JsonUtil.toJson(prizeOrderBO)+"}", e);
			return checkPrizeOrderResult;
		}
		
	}
	
	/**
	 * 核对中奖订单的价格是否正确
	 * @param promotionId
	 * @param skuId
	 * @param orderPrice
	 * @param lotteryLevel
	 * @return
	 */
	private boolean checkPrice(long promotionId, long skuId, BigDecimal orderPrice, int lotteryLevel){
		try{
			//查询基本价格
			SkuBO skuInfo = iSkuService.querySkuById(skuId);
			//查询折扣率
			List<LadderPromotionBO> ladderPromotionList = iLadderPromotionService.queryLadderPromotionByPromotionId(promotionId);
			if(CollectionUtils.isEmpty(ladderPromotionList)){
				return false;
			}
			List<Long> ladderIdList= new ArrayList<Long>();
			for(LadderPromotionBO ladderPromotionBO : ladderPromotionList){
				if(ladderPromotionBO!=null){
					ladderIdList.add(ladderPromotionBO.getLadderId());
				}
			}
			List<LadderBO> ladderList = iLadderService.queryLadderByIds(ladderIdList);
			if(CollectionUtils.isEmpty(ladderList)){
				return false;
			}
			//核对中奖的价格和实际价格是否相符
			for(LadderBO ladderBO : ladderList){
				if(ladderBO!=null){
					if(lotteryLevel == ladderBO.getType()){
						if(orderPrice.compareTo((ladderBO.getPriceDiscount().multiply(skuInfo.getOutprice()).divide(new BigDecimal("100")))) ==0){
							return true;
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("PrizeServiceImpl.checkPrizeOrder添加订单价格时发生异常，入参{promotionId="+promotionId+"}", e);
		}
		return false;
	}
	
	
	public long addPrizeOrder(PrizeOrderBO prizeOrderBO) {
		
		try {
			PrizeOrderDAO prizeOrderDAO = PrizeOrderConvert.convertBOTODAO(prizeOrderBO);
			long orderId = iOrderIdDAO.obtainOrderId();
			if(orderId<1000){
				throw new RuntimeException("提交时遇到交通堵塞无法通行，请稍后再重试！！！");
			}
			prizeOrderDAO.setOrderId(orderId);
			prizeOrderDAO.setStatus(OrderStatusDict.NEW);
			prizeOrderDAO.setYn(true);
			long id = iPrizeOrderDAO.addPrizeOrder(prizeOrderDAO);
			if(id < 0){
				throw new RuntimeException("提交遇到雾霾看不清道路，请稍后再重试！！！");
			}
			String submitOrderRecordRedisKey = RedisKeyPrefixConstant.ORDER_SUBMIT_RECORD_PRIFIXE+prizeOrderBO.getPin()+prizeOrderBO.getPromotionId();
			iPromotionRedis.setValue(submitOrderRecordRedisKey, "1", RedisKeyPrefixConstant.ORDER_SUBMIT_RECORD_TIME);
			return id;
		} catch (Exception e) {
			logger.error("PrizeServiceImpl.checkPrizeOrder添加订单信息时发生异常，入参{prizeOrderBO="+JsonUtil.toJson(prizeOrderBO)+"}", e);
		}
		return -1L;
	}

	public List<PrizeOrderBO> queryPrizeOrderListByPin(String pin) {
		List<PrizeOrderBO> prizeOrderList = new ArrayList<PrizeOrderBO>();
		try{
			String orderListRedisStr = iPromotionRedis.getValue(RedisKeyPrefixConstant.ORDER_LIST_INFO_PRIFIXE+pin);
			if(StringUtils.isNotBlank(orderListRedisStr)){
				prizeOrderList = JsonUtil.readJson(orderListRedisStr, List.class, PromotionBO.class);
			}
			if(CollectionUtils.isEmpty(prizeOrderList)){
				PrizeOrderDAO prizeOrderDAO = new PrizeOrderDAO();
				prizeOrderDAO.setPin(pin);
				List<PrizeOrderDAO> prizeOrderDAOList = iPrizeOrderDAO.queryPrizeOrderByCondition(prizeOrderDAO);
				if(CollectionUtils.isEmpty(prizeOrderDAOList)){
					//如果为空则缓存1分钟
					iPromotionRedis.setValue(RedisKeyPrefixConstant.ORDER_LIST_INFO_PRIFIXE+pin, "", 60);
				}else{
					prizeOrderList = PrizeOrderConvert.convertDAOTOBOList(prizeOrderDAOList);
					String orderListStr = JsonUtil.toJson(prizeOrderList);
					iPromotionRedis.setValue(RedisKeyPrefixConstant.ORDER_LIST_INFO_PRIFIXE+pin, orderListStr, RedisKeyPrefixConstant.ORDER_LIST_INFO_TIME);
				}
			}
		}catch (Exception e) {
			logger.error("PrizeServiceImpl.queryPrizeOrderListByPin根据用户查询订单列表时发生异常，入参{pin="+pin+"}", e);
		}
		return prizeOrderList;
	}

	public boolean updatePrizeOrderStatusByCondition(String pin, long orderId, int status) {
		
		try{
			PrizeOrderDAO prizeOrderDAO = new PrizeOrderDAO();
			prizeOrderDAO.setPin(pin);
			prizeOrderDAO.setOrderId(orderId);
			//设置订单状态为完成
			prizeOrderDAO.setStatus(status);
			boolean updateOk = iPrizeOrderDAO.updatePrizeOrder(prizeOrderDAO);
			
			//更新数据库后更新缓存
			if(updateOk){
				List<PrizeOrderBO> prizeOrderList = new ArrayList<PrizeOrderBO>();
				String orderListRedisStr = iPromotionRedis.getValue(RedisKeyPrefixConstant.ORDER_LIST_INFO_PRIFIXE+pin);
				if(StringUtils.isNotBlank(orderListRedisStr)){
					prizeOrderList = JsonUtil.readJson(orderListRedisStr, List.class, PromotionBO.class);
					if(CollectionUtils.isNotEmpty(prizeOrderList)){
						for(PrizeOrderBO prizeOrderBO : prizeOrderList){
							if(prizeOrderBO.getOrderId() == orderId){
								prizeOrderBO.setStatus(status);
								break;
							}
						}
					}
					String orderListStr = JsonUtil.toJson(prizeOrderList);
					iPromotionRedis.setValue(RedisKeyPrefixConstant.ORDER_LIST_INFO_PRIFIXE+pin, orderListStr, RedisKeyPrefixConstant.ORDER_LIST_INFO_TIME);
				}
			}
			return true;
		}catch (Exception e) {
			logger.error("PrizeServiceImpl.updatePrizeOrderStatusByCondition跟新用户订单状态时发生异常，入参{pin="+pin+",  orderId="+orderId+" }", e);
			return false;
		}
	}
	

	public boolean updatePrizeOrderInfoByCondition(PrizeOrderBO prizeOrderBO) {
		try{
			if(prizeOrderBO == null || StringUtils.isBlank(prizeOrderBO.getPin()) || prizeOrderBO.getOrderId()<0){
				return false;
			}
			PrizeOrderDAO prizeOrderDAO = new PrizeOrderDAO();
			prizeOrderDAO = PrizeOrderConvert.convertBOTODAO(prizeOrderBO);
			boolean updateOk = iPrizeOrderDAO.updatePrizeOrder(prizeOrderDAO);
			//更新数据库后更新缓存
			if(updateOk){
				List<PrizeOrderBO> prizeOrderList = new ArrayList<PrizeOrderBO>();
				String orderListRedisStr = iPromotionRedis.getValue(RedisKeyPrefixConstant.ORDER_LIST_INFO_PRIFIXE+prizeOrderBO.getPin());
				if(StringUtils.isNotBlank(orderListRedisStr)){
					prizeOrderList = JsonUtil.readJson(orderListRedisStr, List.class, PromotionBO.class);
					if(CollectionUtils.isNotEmpty(prizeOrderList)){
						for(PrizeOrderBO prizeOrderBOFromRedis : prizeOrderList){
							if(prizeOrderBOFromRedis.getOrderId() == prizeOrderBO.getOrderId()){
								prizeOrderBOFromRedis = prizeOrderBO;
								break;
							}
						}
					}
					String orderListStr = JsonUtil.toJson(prizeOrderList);
					iPromotionRedis.setValue(RedisKeyPrefixConstant.ORDER_LIST_INFO_PRIFIXE+prizeOrderBO.getPin(), orderListStr, RedisKeyPrefixConstant.ORDER_LIST_INFO_TIME);
				}
			}
			return true;
		}catch (Exception e) {
			logger.error("PrizeServiceImpl.updatePrizeOrderInfoByCondition更新用户订单信息时发生异常，入参{prizeOrderBO="+JsonUtil.toJson(prizeOrderBO)+"}", e);
			return false;
		}
	}
	
	public QueryPrizeOrderBOResult queryTakePrizeDetailInfo(String pin, long promotionId) {
		QueryPrizeOrderBOResult queryPrizeOrderBOResult = new QueryPrizeOrderBOResult();
		PrizeOrderBO prizeOrderBO = new PrizeOrderBO();
		try{
			//检查奖品数量
			PromotionBO promotionInfo = iPromotionService.queryPromotionById(promotionId);
			if(promotionInfo.getSkuId()==null){
				queryPrizeOrderBOResult.setSuccess(false);
				queryPrizeOrderBOResult.setMsg("奖品已经过期，欢迎下次光临！！！");
				return queryPrizeOrderBOResult;
			}
			//查询基本价格
			SkuBO skuInfo = iSkuService.querySkuById(promotionInfo.getSkuId());
			if(skuInfo!=null && StringUtils.isBlank(skuInfo.getName())){
				queryPrizeOrderBOResult.setSuccess(false);
				queryPrizeOrderBOResult.setMsg("奖品已经过期，欢迎下次光临！！！");
				return queryPrizeOrderBOResult;
			}
			prizeOrderBO.setSkuId(skuInfo.getId());
			prizeOrderBO.setSkuName(skuInfo.getName());
			prizeOrderBO.setSkuNum(1);
			
			//查询折扣率
			List<LadderPromotionBO> ladderPromotionList = iLadderPromotionService.queryLadderPromotionByPromotionId(promotionId);
			if(CollectionUtils.isEmpty(ladderPromotionList)){
				queryPrizeOrderBOResult.setSuccess(false);
				queryPrizeOrderBOResult.setMsg("奖品已经过期，欢迎下次光临！！！");
				return queryPrizeOrderBOResult;
			}
			List<Long> ladderIdList= new ArrayList<Long>();
			for(LadderPromotionBO ladderPromotionBO : ladderPromotionList){
				if(ladderPromotionBO!=null){
					ladderIdList.add(ladderPromotionBO.getLadderId());
				}
			}
			List<LadderBO> ladderList = iLadderService.queryLadderByIds(ladderIdList);
			if(CollectionUtils.isEmpty(ladderList)){
				queryPrizeOrderBOResult.setSuccess(false);
				queryPrizeOrderBOResult.setMsg("奖品已经过期，欢迎下次光临！！！");
				return queryPrizeOrderBOResult;
			}
			String lotteryLevelRedisKey = RedisKeyPrefixConstant.USER_LOTTERY_LEVEL_PRIFIXE+pin+promotionId;
			String lotteryLevelStr = iPromotionRedis.getValue(lotteryLevelRedisKey);
			if(StringUtils.isBlank(lotteryLevelStr)){
				queryPrizeOrderBOResult.setSuccess(false);
				queryPrizeOrderBOResult.setMsg("奖品信息错误，请核对中奖信息！！！");
				return queryPrizeOrderBOResult;
			}
			
			int lotteryLevel = Integer.valueOf(lotteryLevelStr);
			//核对中奖的价格和实际价格是否相符
			for(LadderBO ladderBO : ladderList){
				if(ladderBO!=null){
					if(lotteryLevel == ladderBO.getType()){
						prizeOrderBO.setPromotionPrice((ladderBO.getPriceDiscount().multiply(skuInfo.getOutprice()).divide(new BigDecimal("100"))));
						prizeOrderBO.setFrightPrice(new BigDecimal("8.00"));
					}
				}
			}
			queryPrizeOrderBOResult.setSuccess(true);
			queryPrizeOrderBOResult.setPrizeOrderBO(prizeOrderBO);
			return queryPrizeOrderBOResult;
		}catch (Exception e) {
			logger.error("PrizeServiceImpl.queryTakePrizeDetailInfo更新用户订单信息时发生异常，入参{prizeOrderBO="+JsonUtil.toJson(prizeOrderBO)+"}", e);
			queryPrizeOrderBOResult.setSuccess(false);
			queryPrizeOrderBOResult.setMsg("系统运行过程中遇到到了牛魔王，正在快速逃离中！！！");
			return queryPrizeOrderBOResult;
		}
	}

	public IPrizeOrderDAO getiPrizeOrderDAO() {
		return iPrizeOrderDAO;
	}

	public void setiPrizeOrderDAO(IPrizeOrderDAO iPrizeOrderDAO) {
		this.iPrizeOrderDAO = iPrizeOrderDAO;
	}

	public IPromotionRedis getiPromotionRedis() {
		return iPromotionRedis;
	}

	public void setiPromotionRedis(IPromotionRedis iPromotionRedis) {
		this.iPromotionRedis = iPromotionRedis;
	}

	public IPromotionService getiPromotionService() {
		return iPromotionService;
	}

	public void setiPromotionService(IPromotionService iPromotionService) {
		this.iPromotionService = iPromotionService;
	}

	public ISkuService getiSkuService() {
		return iSkuService;
	}

	public void setiSkuService(ISkuService iSkuService) {
		this.iSkuService = iSkuService;
	}

	public ILadderPromotionService getiLadderPromotionService() {
		return iLadderPromotionService;
	}

	public void setiLadderPromotionService(
			ILadderPromotionService iLadderPromotionService) {
		this.iLadderPromotionService = iLadderPromotionService;
	}

	public ILadderService getiLadderService() {
		return iLadderService;
	}

	public void setiLadderService(ILadderService iLadderService) {
		this.iLadderService = iLadderService;
	}

	public void setiOrderIdDAO(IOrderIdDAO iOrderIdDAO) {
		this.iOrderIdDAO = iOrderIdDAO;
	}


}
