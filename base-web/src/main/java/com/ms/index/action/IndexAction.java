package com.ms.index.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import base.test.base.action.BaseAction;

import com.ms.domain.convert.PromotionBriefConvert;
import com.ms.domain.promotion.bo.PromotionBriefBOInfo;
import com.ms.domian.action.promotion.vo.PromotionBriefVOInfo;
import com.ms.service.promotion.face.IPromotionService;

public class IndexAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private IPromotionService iPromotionService;
	
	
    public String showIndex() {
        try{
        	List<PromotionBriefVOInfo> promotionBriefVOList = new ArrayList<PromotionBriefVOInfo>();
        	List<PromotionBriefBOInfo> promotionInfoList = iPromotionService.queryLastPromotionInfoForIndex(8);
        	
        	if(CollectionUtils.isNotEmpty(promotionInfoList)){
        		for(PromotionBriefBOInfo promotionBriefBOInfo : promotionInfoList){
        			PromotionBriefVOInfo promotionBriefVOInfo = PromotionBriefConvert.convertBOTOVO(promotionBriefBOInfo);
        			promotionBriefVOList.add(promotionBriefVOInfo);
        		}
        	}
        	
    		while(promotionBriefVOList.size()<8){
    			promotionBriefVOList.add(makeDefalutInfo());
    		}
        	
	    	Map<String, Object> parmKeyValue = new HashMap<String, Object>();
	    	parmKeyValue.put("promotionBriefList",promotionBriefVOList);
	    	putParamToVm(parmKeyValue);
        }catch(Exception e){
        	logger.error("IndexAction.showIndex方法查询过程中发生异常！！！", e);
        }
        return SUCCESS;
    }
    
    private PromotionBriefVOInfo makeDefalutInfo(){
    	PromotionBriefVOInfo promotionBriefVOInfo =new PromotionBriefVOInfo();
    	return promotionBriefVOInfo;
    }


	public void setiPromotionService(IPromotionService iPromotionService) {
		this.iPromotionService = iPromotionService;
	}

}
