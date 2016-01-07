/**
 * 添加阶梯规则信息
 */
/*function addLadder(){
	$("#ladderPopdiv").attr("class","ladderPopdivshow");
	$('#ladderId').attr("disabled",true);
}*/

/**
 * 编辑促销信息
 * @param skuId
 */
function editOrder(queryOrderId,pin){
	$("#orderPopdiv").attr("class","orderPopdivshow");
	
	var param = "orderId="+queryOrderId+"&pin="+pin+"&r="+(new Date()).getTime();
	$.ajax({
		  url: "/backstage/managerOrderList/queryOrderList.action",
	      type: "POST",
	      data: param,
	      dataType: "json",
	      async:false,
	      success: function(msg){
	    	  if(msg.success){
	    		  var orderInfoList = msg.orderList;
	    		  var len =  orderInfoList.length;
	    		  if(len>0){
    			  	var orderInfo = orderInfoList[0];
					$("#orderUniqueId").val(orderInfo.id);
					$('#orderUniqueId').attr("disabled",true);
					$("#orderId").val(orderInfo.orderId);
					$('#orderId').attr("disabled",true);
					$("#userPin").val(orderInfo.pin);
					$('#userPin').attr("disabled",true);
					
					$("#skuId").val(orderInfo.skuId);
					$('#skuId').attr("disabled",true);
//					$("#skuName").val(orderInfo.pin);
//					$('#skuName').attr("disabled",true);
					
					$("#orderPrizeLevel").val(orderInfo.prizeType);
					$('#orderPrizeLevel').attr("disabled",true);
					
					$("#orderPromotionPrice").val(orderInfo.promotionPrice);
					$('#orderPromotionPrice').attr("disabled",true);
					$("#orderFrightPrice").val(orderInfo.frightPrice);
					$('#orderFrightPrice').attr("disabled",true);
					
					$("#name").val(orderInfo.name);
					$('#name').attr("disabled",true);
					$("#phone").val(orderInfo.phone);
					$('#phone').attr("disabled",true);
					$("#address").val(orderInfo.address);
					$('#address').attr("disabled",true);
					
					$("#frightTrade").val(orderInfo.frightTrader);
					$("#frightOrder").val(orderInfo.frightOrder);
					
					$("#status").val(orderInfo.statusId);
					
					$("#orderCreatTime").val(orderInfo.createTime);
					$('#orderCreatTime').attr("disabled",true);
					
					if(orderInfo.yn==true){
						$("input[name='isvaliade'][value=1]").attr("checked",true);
					}else{
						$("input[name='isvaliade'][value=0]").attr("checked",true);
					}
					$("input[name='isvaliade']").attr("disabled",true);
	    		  }
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


/**
 * 关闭促销信息弹出框
 */
function closeOrderPopDiv(){
	$("#orderPopdiv").attr("class","orderPopdivhidden");
}

/**
 * 查询促销信息
 */
function queryOrderInfo(){
	var queryOrderId=$("#queryOrderId").val();
	var queryUserpin=$("#queryUserpin").val();
	
	if (isNullValue(queryOrderId) && isNullValue(queryUserpin)) {
		alert("请输入订单号和用户pin");
		$("#queryOrderId").focus();
		return;
	}
	
	var param = "orderId="+queryOrderId+"&pin="+queryUserpin+"&r="+(new Date()).getTime();
	$.ajax({
		  url: "/backstage/managerOrderList/queryOrderList.action",
	      type: "POST",
	      data: param,
	      dataType: "json",
	      async:false,
	      success: function(msg){
	    	  if(msg.success){
	    		  var orderInfoList = msg.orderList;
	    		  var len =  orderInfoList.length;
	    		  if(len>0){
	    			  var orderHtmlStr="";
	    			  for(var index=0;index<len;index++){
	    				  var orderInfo = orderInfoList[index];
	    				  var orderStr="<tr><td>"+orderInfo.id+"</td><td>"+orderInfo.orderId+"</td><td>"+orderInfo.pin+"</td><td>商品编号:"+orderInfo.skuId+"商品名称"+orderInfo.skuName+"商品数量:"+orderInfo.skuNum+"</td>";
	    				  if(orderInfo.prizeType ==101){
	    					  orderStr+="<td>一等奖</td>";
	    				  }else if(orderInfo.prizeType ==102){
	    					  orderStr+="<td>二等奖</td>";
	    				  }else if(orderInfo.prizeType ==103){
	    					  orderStr+="<td>三等奖</td>";
	    				  }else{
	    					  orderStr+="<td>未知</td>";
	    				  }
	    				  var orderPriceInfo ="<td>订单价格:"+orderInfo.promotionPrice+"</br>运费:"+orderInfo.frightPrice+"</td>" +
	    				  		"<td>收货人:"+orderInfo.name+"</br>手机号:"+orderInfo.phone+"</br>收货地址:"+orderInfo.address+"</td>" +
				  				"<td>快递:"+orderInfo.frightTrader+"</br>单号: "+orderInfo.frightOrder+"</td>" +
		  						"<td>"+orderInfo.statusStr+"</td><td>"+orderInfo.createTime+"</td>";
	    				  orderStr+=orderPriceInfo;
	    					if(orderInfo.yn==true){
	    						orderStr+="<td>有效</td>";
	    					}else{
	    						orderStr+="<td>无效</td>";
	    					}
	    					orderStr+="<td><input type=\"button\" onclick=\"editOrder('"+orderInfo.orderId+"','"+orderInfo.pin+"')\" value=\"修改信息\"></td></tr>";
	    					orderHtmlStr+=orderStr;
	    			  }
	    			  $("#orderInfoBody").html(orderHtmlStr);
	    		  }
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

function submitOrder(){
	var frightTrade=$("#frightTrade").val();
	var frightOrder=$("#frightOrder").val();
	var status=$("#status").val();
	
	var orderId=$("#orderId").val();
	var userPin=$("#userPin").val();
	
	var param = "orderId="+orderId+"&status="+status+"&frightOrder="+frightOrder+"&frightTrader="+frightTrade+"&pin="+userPin+"&r="+(new Date()).getTime();
	$.ajax({
		  url: "/backstage/managerOrderList/updateOrderFrightInfo.action",
	      type: "POST",
	      data: param,
	      dataType: "json",
	      async:false,
	      success: function(msg){
	    	  if(msg.success){
	    		  
	    		  alert(msg.msg);
	    	  }else{
	    		  alert(msg.msg);
	    	  }
	      },
	      error:function(msg){
	    	  alert(msg.msg);
	      }
		 });
}

/**
 * 添加商品信息
 */
/*function submitLadder(){
	var ladderId=$("#ladderId").val();
	$('#ladderId').attr("disabled",true);
	var priceDiscount=$("#priceDiscount").val();
	var numPercent=$("#numPercent").val();
	var lotteryBaseNum=$("#lotteryBaseNum").val();
	var lotteryHitNum=$("#lotteryHitNum").val();
	var type=$("#type").val();
	var isvaliade=$("input[name='isvaliade']:checked").val();

	if (!isNullValue(ladderId)) {
		updateLadder();
		return;
	}
	
	if (isNullValue(priceDiscount)) {
		$("#tipInfo").text("请输入价格折扣率");
		$("#priceDiscount").focus();
		return;
	}
	
	if (isNullValue(numPercent)) {
		$("#tipInfo").text("请输入数量折扣率");
		$("#numPercent").focus();
		return;
	}
	if (isNullValue(type)) {
		$("#tipInfo").text("请输入阶梯规则类型");
		$("#type").focus();
		return;
	}
	if (isNullValue(isvaliade)) {
		$("#tipInfo").text("请选择是否有效");
		$("#isvaliade").focus();
		return;
	}
	
	if (isNullValue(lotteryBaseNum)) {
		$("#tipInfo").text("请输入分母的值");
		$("#lotteryBaseNum").focus();
		return;
	}
	
	if (isNullValue(lotteryHitNum)) {
		$("#tipInfo").text("请输入分子的值");
		$("#lotteryHitNum").focus();
		return;
	}
	

	
	var param = "ladderId="+ladderId+"&priceDiscount="+priceDiscount+"&numPercent="+numPercent+"&type="+type+"&isvaliade="+isvaliade+"&lotteryBaseNum="+lotteryBaseNum+"&lotteryHitNum="+lotteryHitNum+"&r="+(new Date()).getTime();
	$.ajax({
		  url: "/backstage/ladder/addLadder.action",
	      type: "POST",
	      data: param,
	      dataType: "json",
	      async:false,
	      success: function(msg){
	    	  if(msg.success){
	    		  window.location.href = "/backstage/ladder/queryLadderByPageNum.action?r="+(new Date()).getTime();
	    	  }else{
	    		  alert(msg.msg);
	    		  $("#tipInfo").text(msg.msg);
	    	  }
	      },
	      error:function(msg){
	    	  alert(msg.msg);
	      }
		 });
	
}*/

/**
 * 取消添加商品信息
 */
function cancleOrder(){
	$("#orderPopdiv").attr("class","orderPopdivhidden");
}


function checkImgName(imgName){
	//正则表达式，校验图片格式
	var syn=new RegExp("\.(jpg|jpeg|ico|bmp|png)$","i");
	//var syn=/\.(jpg|jpeg|ico|bmp|png)$/i;  注意这里不是字符串
	var r = syn.test(imgName);
	if(imgName == "") return false;
	if (!syn.test(imgName)){
		alert("请上传jpg/png/ico/bmp格式的图片！");
		return false;
	}
	return true;
}
var preivew = function(file){
    try{ 
    	var imgNames = file.value;
    	var uid = "#" + file.id;

    	//校验图片格式
    	if(checkImgName(imgNames)==false){
    		$(uid).remove();
    	}
    	return;
    }catch(e){ 
        alert(e); 
    } 
}