package com.ms.promotion.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import base.test.base.action.BaseAction;
import base.test.base.util.JsonUtil;

import com.ms.domain.action.front.result.DrawLotteryResult;
import com.ms.domain.business.constant.DrawLotteryResutlDict;
import com.ms.domain.ladderpromotion.bo.LadderPromotionInfoBO;
import com.ms.domain.promotion.bo.PromotionDetailInfoBO;
import com.ms.domian.action.promotion.vo.LadderPromotionInfoVO;
import com.ms.domian.action.promotion.vo.LotteryHtmlVO;
import com.ms.domian.action.promotion.vo.PromotionDetailInfoVO;
import com.ms.service.promotion.face.IPromotionService;

public class PromotionAction extends BaseAction{

	private static final long serialVersionUID = -2104268979984419770L;
	
	private Logger logger = Logger.getLogger(this.getClass());
    
    private IPromotionService iPromotionService;
    
    
    /**
     * 促销id
     */
    public Long promotionId;

    /**
     * 展示促销信息详情
     * @return
     */
    public String showPromotionDetail(){
        try{
        	PromotionDetailInfoVO promotionDetailInfoVO = new PromotionDetailInfoVO();
        	PromotionDetailInfoBO promotionDetailInfo = iPromotionService.queryKillPromotionDetail(promotionId);
        	if(promotionDetailInfo!=null){
        		promotionDetailInfoVO.setAdvert(promotionDetailInfo.getAdvert());
        		promotionDetailInfoVO.setPromotionId(promotionDetailInfo.getPromotionId());
        		promotionDetailInfoVO.setSkuId(promotionDetailInfo.getSkuId());
        		promotionDetailInfoVO.setSkuImgUrl(promotionDetailInfo.getSkuImgUrl());
        		promotionDetailInfoVO.setSkuName(promotionDetailInfo.getSkuName());
        		promotionDetailInfoVO.setStatus(promotionDetailInfo.getStatus());
        		
        		List<LadderPromotionInfoVO> ladderPromotionVOList = new ArrayList<LadderPromotionInfoVO>();
        		if(CollectionUtils.isNotEmpty(promotionDetailInfo.getLadderPromotionList())){
        			for(LadderPromotionInfoBO ladderPromotionInfoBO : promotionDetailInfo.getLadderPromotionList()){
        				LadderPromotionInfoVO ladderPromotionInfoVO  = new LadderPromotionInfoVO();
        				ladderPromotionInfoVO.setDiscount(ladderPromotionInfoBO.getDiscount());
        				ladderPromotionInfoVO.setLadderPromotionId(ladderPromotionInfoBO.getPromotionLadderId());
        				ladderPromotionInfoVO.setPromotionPrice(ladderPromotionInfoBO.getPromotionPrice());
        				ladderPromotionInfoVO.setSkuNum(ladderPromotionInfoBO.getSkuNum());
        				ladderPromotionVOList.add(ladderPromotionInfoVO);
        			}
        			promotionDetailInfoVO.setLadderList(ladderPromotionVOList);
        		}
        	}
        	
	    	Map<String, Object> parmKeyValue = new HashMap<String, Object>();
	    	parmKeyValue.put("promotionDetailInfo",promotionDetailInfoVO);
	    	putParamToVm(parmKeyValue);
        }catch(Exception e){
        	logger.error("PromotionAction.showPromotionDetail方法查询过程中发生异常！！！", e);
        }
        return "showPromotionDetail";
    }
    
    public String drawLottery(){
    	DrawLotteryResult drawLotteryResult =  new DrawLotteryResult();
    	try{
    		if(promotionId==null || promotionId.longValue()<100000L){
    			drawLotteryResult.setSuccess(false);
    			drawLotteryResult.setMsg("请刷新后提交！！！");
    		}
    		drawLotteryResult.setPromotionId(promotionId);
    		
    		String takePrizeUrl = "http://www.ms.com/prize/takePrize.action?promotionId="+promotionId+"&t="+System.currentTimeMillis();
    		int drawResult = iPromotionService.drawLottery(getPin(), promotionId, getIpAddr(), getUUID());
    		switch(drawResult){
	    		case DrawLotteryResutlDict.UN_START:
	    			drawLotteryResult.setSuccess(false);
	    			drawLotteryResult.setMsg("活动未进行！！！");
	    			break;
	    		case DrawLotteryResutlDict.END:
	    			drawLotteryResult.setSuccess(false);
	    			drawLotteryResult.setMsg("这次活动的抽奖次数已经用完了，请参与其他活动！！！");
	    			break;
	    		case DrawLotteryResutlDict.SYS_EXCEPTION:
	    			drawLotteryResult.setSuccess(false);
	    			drawLotteryResult.setMsg("非常抱歉！！！抽奖小白开小差了，可以继续重试！！！");
	    			break;
	    		case DrawLotteryResutlDict.UN_HIT_PRIZE:
	    			drawLotteryResult.setSuccess(false);
	    			drawLotteryResult.setMsg("非常遗憾，这次没有抽中，继续加油！！！");
	    			break;
	    		case DrawLotteryResutlDict.HIT_FIRST_PRIZE:
	    			drawLotteryResult.setSuccess(true);
	    			drawLotteryResult.setAcceptLotteryLink(takePrizeUrl);
	    			drawLotteryResult.setLotteryLevel("1");
	    			drawLotteryResult.setMsg("手气大爆发，抽中了一等奖！！！");
	    			break;
	    		case DrawLotteryResutlDict.HIT_SECONDE_PRIZE:
	    			drawLotteryResult.setSuccess(true);
	    			drawLotteryResult.setLotteryLevel("2");
	    			drawLotteryResult.setAcceptLotteryLink(takePrizeUrl);
	    			drawLotteryResult.setMsg("手气很好哦，抽中了二等奖！！！");
	    			break;
	    		case DrawLotteryResutlDict.HIT_THIRD_PRIZE:
	    			drawLotteryResult.setSuccess(true);
	    			drawLotteryResult.setLotteryLevel("3");
	    			drawLotteryResult.setAcceptLotteryLink(takePrizeUrl);
	    			drawLotteryResult.setMsg("手气不错，抽中了三等奖！！！");
	    			break;
    		}
    		print(JsonUtil.toJson(drawLotteryResult));
    	}catch(Exception e){
    		logger.error("PromotionAction.drawLottery方法抽奖过程中发生异常！！！", e);
    	}
    	return "drawLottery";
    }
    
    /**
     * 展示抽奖的html代码
     * @return
     */
    public String showLotterHtml(){
    	try{
    		LotteryHtmlVO lotteryHtmlVO = new LotteryHtmlVO();
			lotteryHtmlVO.setHtmlCode(bulidHtmlCode());
			lotteryHtmlVO.setJavaScriptCode(bulidScriptCode());
			lotteryHtmlVO.setCssCode(buildCssCode());
    		lotteryHtmlVO.setMsg("查询成功！！！");
    		lotteryHtmlVO.setSuccess(true);
			print(JsonUtil.toJson(lotteryHtmlVO));
        }catch(Exception e){
        	logger.error("PromotionAction.showLotterHtml方法执行过程中发生异常！！！", e);
        }
        return "showLotterHtml";
    }
    
    private String bulidHtmlCode(){
    	String htmlCode = "<div class=\"egg\">"+
								"<ul class=\"eggList\">"+
									"<p class=\"hammer\" id=\"hammer\">锤子</p>"+
									"<p class=\"resultTip\" id=\"resultTip\"><b id=\"result\"></b></p>"+
									"<li><span>1</span><sup></sup></li>"+
									"<li><span>2</span><sup></sup></li>"+
									"<li><span>3</span><sup></sup></li>"+
								"</ul>"+
							"</div>";
    	return htmlCode;
    }
//    private String bulidHtmlCode(){
//    	String htmlCode = "<div class=\"htmlDivcontentStyle\"><button type=\"button\" onclick=\"startRandom();\">开始</button> <button type=\"button\" onclick=\"endRandom();\">结束</button>" +
//    			"<div id=\"showText\"></div>" +
//    			"</div>";
//    	return htmlCode;
//    }
    
//    private String bulidScriptCode(){
//    	String scriptCode="<script language=\"javascript\">" +
//    			"function startRandom(){" +
//    				"$(\"#showText\").val('开始');" +
//    				"alert(999);" +
//    			"}" +
//    			"function endRandom(){" +
//    			"$(\"#showText\").val('结束');" +
//    			"}" +
//    			"</script>";
//    	return scriptCode;
//    }
    private String bulidScriptCode(){
    	String scriptCode="<script type=\"text/javascript\">"+
								"function eggClick(obj) {"+
								"var _this = obj;"+
								
									"if(_this.hasClass(\"curr\")){"+
										"alert(\"蛋都碎了，别砸了！刷新再来.\");"+
										"return false;"+
									"}"+
									"$.getJSON(\"/promotion/drawLottery.action?promotionId="+promotionId+"&t="+System.currentTimeMillis()+"\",function(res){	"+
										//_this.unbind('click');
										"$(\".hammer\").css({\"top\":_this.position().top-55,\"left\":_this.position().left+185});"+
										"$(\".hammer\").animate({"+
											"\"top\":_this.position().top-25,"+
											"\"left\":_this.position().left+125"+
											"},30,function(){"+
												"_this.addClass(\"curr\");"+
												"_this.find(\"sup\").show();"+
												"$(\".hammer\").hide();"+
												"$('.resultTip').css({display:'block',top:'100px',left:_this.position().left+45,opacity:0}).animate({top: '50px',opacity:1},300,function(){"+
													"if(res.success){"+
														"$(\"#result\").html(res.msg+\" <br/><a href='\"+res.acceptLotteryLink+\"'>去领奖</a>\");"+	
													"}else{" +
														"$(\"#result\").html(res.msg);"+	
													"}"+
												"});"+	
											"}"+
										");"+
								"});"+
							"}"+
							"$(\".eggList li\").click(function() {"+
								"$(this).children(\"span\").hide();"+
								"eggClick($(this));"+
							"});"+
							"$(\".eggList li\").hover(function() {"+
								"var posL = $(this).position().left + $(this).width();"+
								"$(\"#hammer\").show().css('left', posL);"+
							"})"+
						"</script>";
    	return scriptCode;
    }
    
    private String buildCssCode(){
    	String cssCode="<style type=\"text/css\">" +
    			".egg{width:660px; height:400px; margin:50px auto 20px auto;}" +
    			".egg ul li{z-index:999;}" +
    			".eggList{padding-top:110px;position:relative;width:660px;}" +
    			".eggList li{list-style-type:none;float:left;background:url(../../../img/lottery/egg_1.png) no-repeat bottom;width:158px;height:187px;cursor:pointer;position:relative;margin-left:35px;}" +
    			".eggList li span{position:absolute; width:30px; height:60px; left:68px; top:64px; color:#ff0; font-size:42px; font-weight:bold}" +
    			".eggList li.curr{background:url(../../../img/lottery/egg_2.png) no-repeat bottom;cursor:default;z-index:300;}" +
    			".eggList li.curr sup{position:absolute;background:url(../../../img/lottery/img-4.png) no-repeat;width:232px; height:181px;top:-36px;left:-34px;z-index:800;}" +
    			".hammer{background:url(../../../img/lottery/img-6.png) no-repeat;width:74px;height:87px;position:absolute; text-indent:-9999px;z-index:150;left:168px;top:100px;}" +
    			".resultTip{position:absolute; background:#ffc ;width:148px;padding:6px;z-index:500;top:200px; left:10px; color:#f60; text-align:center;overflow:hidden;display:none;z-index:500;}" +
    			".resultTip b{font-size:14px;line-height:24px;}" +
    			"</style>";
    	return cssCode;
    }
    
	public void setiPromotionService(IPromotionService iPromotionService) {
		this.iPromotionService = iPromotionService;
	}

}
