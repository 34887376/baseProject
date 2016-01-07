function submitOrder(){
	
	var addressName=$("#addressName").val();
	var addressPhone=$("#addressPhone").val();
	var addressAddress=$("#addressAddress").val();
	var promotionId=$("#promotionId").val();
	var orderPrice=$("#orderPrice").val();
	var skuId=$("#skuId").val();
	
	if (isNullValue(addressName)) {
//		$("#tipInfo").text("请输入商品数量");
		alert("请输入收货人姓名");
		$("#addressName").focus();
		return;
	}
	
	if (isNullValue(addressPhone)) {
//		$("#tipInfo").text("请输入商品数量");
		alert("请输入收货人电话");
		$("#addressPhone").focus();
		return;
	}
	
	if (isNullValue(addressAddress)) {
//		$("#tipInfo").text("请输入商品数量");
		alert("请输入收货人地址");
		$("#addressAddress").focus();
		return;
	}
	
	if (isNullValue(promotionId)) {
//		$("#tipInfo").text("请输入商品数量");
		alert("请刷新页面重新提交");
		return;
	}
	
	if (isNullValue(orderPrice)) {
//		$("#tipInfo").text("请输入商品数量");
		alert("请刷新页面重新提交");
		return;
	}
	
	if (isNullValue(skuId)) {
//		$("#tipInfo").text("请输入商品数量");
		alert("请刷新页面重新提交");
		return;
	}
	
	var param = "name="+addressName+"&phone="+addressPhone+"&address="+addressAddress+"&promotionId="+promotionId+"&orderPrice="+orderPrice+"&skuId="+skuId+"&r="+(new Date()).getTime();
	$.ajax({
		  url: "/prize/submitPrizeOrder.action?r="+(new Date()).getTime(),
	      type: "POST",
	      data: param,
	      dataType: "json",
	      async:false,
	      success: function(msg){
	    	  if(msg.success){
	    		  window.location.href = "/orderlist/showOrderList.action?r="+(new Date()).getTime();
	    	  }else{
	    		  alert(msg.msg);
	    		  $("#tipInfo").text(msg.msg);
	    	  }
	    	  
	      },
	      error:function(msg){
	    	  alert(msg.msg);
	      }
		 });
}