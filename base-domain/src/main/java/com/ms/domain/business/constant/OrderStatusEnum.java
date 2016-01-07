package com.ms.domain.business.constant;

import org.apache.commons.lang.StringUtils;

public enum OrderStatusEnum {
	
	新订单(11),支付完成(12),配送中(13),完成(100),失效(400);

	private int status;

	private OrderStatusEnum(int status) {
		this.status = status;
	}
	
	/**
	 * 根据id获取内容
	 * @param status
	 * @return
	 */
	public static String getStatusStrById(int status){
		 for(OrderStatusEnum name :OrderStatusEnum.values()){
			 if(name.getStatus()==status){
				 return name.name();
			 }
    	 }
		 return "订单异常";
	}
	
	
	/**
	 * 根据内容获取id
	 * @param statusName
	 * @return
	 */
	public static int getStatusIdByName(String statusName){
		if(StringUtils.isBlank(statusName)){
			return -1;
		}
		 for(OrderStatusEnum name :OrderStatusEnum.values()){
			 if(statusName.equals(name.name())){
				 return name.getStatus();
			 }
		 }
		 return -1;
	}
	
    public static void main(String[] args){
    	for(OrderStatusEnum name :OrderStatusEnum.values()){
   		 	System.out.println(name+"   "+ name.name() + "   "+name.getStatus());
   	 	}
    }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	

}
