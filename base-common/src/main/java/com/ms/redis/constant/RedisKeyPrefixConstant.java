package com.ms.redis.constant;

public class RedisKeyPrefixConstant {
	
	//第一个开始促销的rediskey前缀
	public static final String START_PROMOTION_SEQUENCE_INDEX="startPromotionSequenceIndexKey"; 
	
	//第一个开始促销的rediskey有效时间(单位为秒)
	public static final int START_PROMOTION_TIME=60 * 30;
	
	//促销综合信息的前缀
	public static final String PROMOTION_INFO_INDEX="promotionInfoContentPrefixKey"; 
	
	//促销的前缀有效时间
	public static final int PROMOTION_INFO_INDEX_TIME=60 * 30;
	
	//促销的前缀
	public static final String PROMOTION_PREFIXE="promotionContentPrefixKey"; 
	
	//促销的前缀有效时间
	public static final int PROMOTION_PREFIXE_TIME=60 * 30;
	
	//促销序列redis存储前缀
	public static final String PROMOTION_SEQUENCE_PROMOTIONID_PRIFIXE="promotionSequence_promotionid_content"; 
	
	//促销序列redis存储有效时间
	public static final int PROMOTION_SEQUENCE_PROMOTIONID_TIME=60 * 30;
	
	//促销序列redis存储前缀
	public static final String PROMOTION_SEQUENCE_ID_PRIFIXE="promotionSequence_id_content"; 
	
	//促销序列redis存储有效时间
	public static final int PROMOTION_SEQUENCE_ID_TIME=60 * 30;
	
	//商品信息redis存储前缀
	public static final String SKU_PRIFIXE="sku_content"; 
	
	//商品信息redis存储有效时间
	public static final int SKU_TIME=60 * 30;

	
	//阶梯规则信息redis存储前缀
	public static final String LADDER_PRIFIXE="ladder_content"; 
	
	//阶梯规则信息redis存储有效时间
	public static final int LADDER_TIME=60 * 30;
	
	//阶梯促销信息redis存储前缀
	public static final String LADDER_PROMOTION_PRIFIXE="ladderPromotion_content"; 
	
	//阶梯促销信息redis存储有效时间
	public static final int LADDER_PROMOTION_TIME=60 * 30;
	
	//用户在某个促销下的抽奖次数redis存储前缀
	public static final String USER_LOTTERY_NUM_PRIFIXE="userLottery_num"; 
	
	//用户在某个促销下的抽奖次数redis存储有效时间
	public static final int USER_LOTTERY_NUM_TIME= 60 * 60 * 24;
	
	//用户在某个促销下的抽中将的等级redis存储前缀
	public static final String USER_LOTTERY_LEVEL_PRIFIXE="userLottery_level"; 
	
	//用户在某个促销下的抽中将的等级redis存储有效时间
	public static final int USER_LOTTERY_LEVEL_TIME= 60 * 60 * 24;
	
	
	//用户在某个促销下的抽将的一等奖的分母redis存储前缀
	public static final String PRIZE_FIRST_BASENUM_PROMOTION_PRIFIXE="promotionLottryFirstPrizeBaseNum"; 
	//用户在某个促销下的抽将的一等奖的分子redis存储前缀
	public static final String PRIZE_FIRST_HITNUM_PROMOTION_PRIFIXE="promotionLottryFirstPrizeHitNum"; 
	
	//用户在某个促销下的抽将的一等奖的分母redis存储有效时间
	public static final int PRIZE_FIRST_PROMOTION_TIME= 60 * 50;
	
	//用户在某个促销下的抽将的二等奖的分母redis存储前缀
	public static final String PRIZE_SECONDE_BASENUM_PROMOTION_PRIFIXE="promotionLottrySecondPrizeBaseNum"; 
	//用户在某个促销下的抽将的二等奖的分子redis存储前缀
	public static final String PRIZE_SECONDE_HITNUM_PROMOTION_PRIFIXE="promotionLottrySecondPrizeHitNum"; 
	//用户在某个促销下的抽将的二等奖的分母redis存储有效时间
	public static final int PRIZE_SECONDE_PROMOTION_TIME= 60 * 50;
	
	//用户在某个促销下的抽将的三等奖的分母redis存储前缀
	public static final String PRIZE_THIRD_BASENUM_PROMOTION_PRIFIXE="promotionLottryThirdPrizeBaseNum"; 
	//用户在某个促销下的抽将的三等奖的分子redis存储前缀
	public static final String PRIZE_THIRD_HITNUM_PROMOTION_PRIFIXE="promotionLottryThirdPrizeHitNum"; 
	//用户在某个促销下的抽将的三等奖的分母redis存储有效时间
	public static final int PRIZE_THIRD_PROMOTION_TIME= 60 * 50;
	
	//抽到奖的数量过期时间
	public static final int PRIZE_HITNUM_TIME= 60 * 50; 

	//用户提交订单记录redis存储前缀
	public static final String ORDER_SUBMIT_RECORD_PRIFIXE="orderSubmitRecord"; 
	//用户提交订单记录存储有效时间
	public static final int ORDER_SUBMIT_RECORD_TIME= 60 * 60 * 24;
	
	//用户订单列表redis存储前缀
	public static final String ORDER_LIST_INFO_PRIFIXE="orderListInfo"; 
	//用户订单列表存储有效时间
	public static final int ORDER_LIST_INFO_TIME= 60 * 60 * 24 * 10;
	
}
