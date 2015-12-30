/**
 * 添加商品信息
 */
function addPromotionSequence(){
	$("#promotionSequencePopdiv").attr("class","promotionSequencePopdivshow");
	$('#promotionSequenceId').attr("disabled",true);
}

/**
 * 编辑商品信息
 * @param skuId
 */
function editPromotionSequence(querypromotionSequenceId){
	$("#promotionSequencePopdiv").attr("class","promotionSequencePopdivshow");
	
	var param = "promotionSequenceId="+querypromotionSequenceId+"&r="+(new Date()).getTime();
	$.ajax({
		  url: "/backstage/promotionSequence/queryPromotionSequenceByCondition.action",
	      type: "POST",
	      data: param,
	      dataType: "json",
	      async:false,
	      success: function(msg){
	    	  if(msg.success){
	    		  var promotionSequenceList = msg.promotionSequenceBOList;
	    		  var len =  promotionSequenceList.length;
	    		  if(len>0){
    			  	var promotionSequenceInfo = promotionSequenceList[0];
					$("#promotionSequenceId").val(promotionSequenceInfo.id);
					$("#promotionId").val(promotionSequenceInfo.promotionId);
					$("#previosOrder").val(promotionSequenceInfo.previosOrder);
					$("#nextOrder").val(promotionSequenceInfo.nextOrder);
					$("#startTime").val(promotionSequenceInfo.startTime);
					$("#endTime").val(promotionSequenceInfo.endTime);
					$("#hasLoad").val(promotionSequenceInfo.hasLoad);
					if(promotionSequenceInfo.yn==true){
						$("input[name='isvaliade'][value=1]").attr("checked",true);
					}else{
						$("input[name='isvaliade'][value=0]").attr("checked",true);
					}
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
 * 将商品信息置为无效
 * @param skuId
 */
function makeInvalidePromotionSeq(promotionSequenceId){
	var param = "promotionSequenceId="+promotionSequenceId+"&r="+(new Date()).getTime();
	$.ajax({
		  url: "/backstage/promotionSequence/makeInvalidPromotionSequence.action",
	      type: "POST",
	      data: param,
	      dataType: "json",
	      async:false,
	      success: function(msg){
	    	  if(msg.success){
	    		  window.location.href = "/backstage/promotionSequence/queryPromotionSequenceByPageNum.action?r="+(new Date()).getTime();
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

function delPromotionSequence(promotionSequenceId){
	var param = "promotionSequenceId="+promotionSequenceId+"&r="+(new Date()).getTime();
	$.ajax({
		  url: "/backstage/promotionSequence/physicalDel.action",
	      type: "POST",
	      data: param,
	      dataType: "json",
	      async:false,
	      success: function(msg){
	    	  if(msg.success){
	    		  window.location.href = "/backstage/promotionSequence/queryPromotionSequenceByPageNum.action?r="+(new Date()).getTime();
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
 * 关闭商品信息弹出框
 */
function closePromotionSequencePopDiv(){
	$("#promotionSequencePopdiv").attr("class","promotionSequencePopdivhidden");
}

/**
 * 查询商品信息
 */
function queryPromotionSequenceInfo(){
	var queryPromotionSequenceId=$("#queryPromotionSequenceId").val();
	var queryPromotionId=$("#queryPromotionId").val();
	
	if (isNullValue(queryPromotionSequenceId) && isNullValue(queryPromotionId)) {
		$("#tipInfo").text("请输入促销序列编号或者促销编号");
		$("#queryPromotionSequenceId").focus();
		return;
	}
	
	var param = "promotionSequenceId="+queryPromotionSequenceId+"&promotionId="+queryPromotionId+"&r="+(new Date()).getTime();
	$.ajax({
		  url: "/backstage/promotionSequence/queryPromotionSequenceByCondition.action",
	      type: "POST",
	      data: param,
	      dataType: "json",
	      async:false,
	      success: function(msg){
	    	  if(msg.success){
	    		  var promotionSequenceList = msg.promotionSequenceBOList;
	    		  var len =  promotionSequenceList.length;
	    		  if(len>0){
	    			  var promotionSequenceHtmlStr="";
	    			  for(var index=0;index<len;index++){
	    				  var promotionSequenceInfo = promotionSequenceList[index];
	    				  var skuStr="<tr><td>"+promotionSequenceInfo.id+"</td><td>"+promotionSequenceInfo.promotionId+"</td><td>"+promotionSequenceInfo.previosOrder+"</td><td>"+promotionSequenceInfo.nextOrder+"</td><td>"+skuInfo.startTime+"</td><td>"+skuInfo.endTime+"</td>";
	    					if(promotionSequenceInfo.hasLoad==1){
	    						skuStr+= "<td>已加载</td>";
	    					}else{
	    						skuStr+= "<td>未加载</td>";
	    					}
	    					
	    					if(promotionSequenceInfo.yn==true){
	    						skuStr+="<td>有效</td>";
	    					}else{
	    						skuStr+="<td>无效</td>";
	    					}
	    					
	    					skuStr+="<td><input type='button' onclick='makeInvalidePromotionSeq("+promotionSequenceInfo.id+")' value='置为无效'><input type='button' onclick='editPromotionSequence("+promotionSequenceInfo.id+")' value='修改信息'><input type='button' onclick='delPromotionSequence("+promotionSequenceInfo.id+")' value='删除'/></td></td></tr>";
	    					promotionSequenceHtmlStr+=skuStr;
	    			  }
	    			  $("#promotionSequenceInfoBody").html(promotionSequenceHtmlStr);
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

function updatePromotionSequence(){
	
	var promotionSequenceId=$("#promotionSequenceId").val();
	$('#promotionSequenceId').attr("disabled",true);
	var promotionId=$("#promotionId").val();
	var previosOrder=$("#previosOrder").val();
	var nextOrder=$("#nextOrder").val();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	var hasLoad=$("#hasLoad").val();
	var isvaliade=$("input[name='isvaliade']:checked").val();

	if (isNullValue(promotionSequenceId)) {
		$("#tipInfo").text("请输入促销序列编号");
		$("#promotionSequenceId").focus();
		return;
	}
	
	if (isNullValue(promotionId)) {
		$("#tipInfo").text("请输入促销编号");
		$("#promotionId").focus();
		return;
	}
	
	if (isNullValue(previosOrder)) {
		$("#tipInfo").text("请输入上一个促销序列编号");
		$("#previosOrder").focus();
		return;
	}
	
	
	if (isNullValue(nextOrder)) {
		$("#tipInfo").text("请输入下一个促销序列编号");
		$("#nextOrder").focus();
		return;
	}
	
	if (isNullValue(startTime)) {
		$("#tipInfo").text("请输入开始时间");
		$("#startTime").focus();
		return;
	}
//	
//	if(!isPhone(userPhone)){
//		$("#tipInfo").text("请输入正确的手机号码");
//		$("#phone").focus();
//		return;
//	}
	
	if (isNullValue(endTime)) {
		$("#tipInfo").text("请输入出结束时间");
		$("#endTime").focus();
		return;
	}
	
	if (isNullValue(hasLoad)) {
		$("#tipInfo").text("请输入是否加载");
		$("#hasLoad").focus();
		return;
	}
	
	if (isNullValue(isvaliade)) {
		$("#tipInfo").text("请选择是否有效");
		$("#isvaliade").focus();
		return;
	}
	

	
	var param = "promotionSequenceId="+promotionSequenceId+"&promotionId="+promotionId+"&previosOrder="+previosOrder+"&nextOrder="+nextOrder+"&startTime="+startTime+"&endTime="+endTime+"&hasLoad="+hasLoad+"&isvaliade="+isvaliade+"&r="+(new Date()).getTime();
	$.ajax({
		  url: "/backstage/promotionSequence/updatePromotionSequence.action",
	      type: "POST",
	      data: param,
	      dataType: "json",
	      async:false,
	      success: function(msg){
	    	  if(msg.success){
	    		  window.location.href = "/backstage/promotionSequence/queryPromotionSequenceByPageNum.action?r="+(new Date()).getTime();
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
 * 添加商品信息
 */
function submitPromotionSequence(){
	
	var promotionSequenceId=$("#promotionSequenceId").val();
	$('#promotionSequenceId').attr("disabled",true);
	var promotionId=$("#promotionId").val();
	var previosOrder=$("#previosOrder").val();
	var nextOrder=$("#nextOrder").val();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	var hasLoad=$("#hasLoad").val();
	var isvaliade=$("input[name='isvaliade']:checked").val();

	if (!isNullValue(promotionSequenceId)) {
		updatePromotionSequence();
		return;
	}
	
	if (isNullValue(promotionId)) {
		$("#tipInfo").text("请输入促销编号");
		$("#promotionId").focus();
		return;
	}
	
	if (isNullValue(previosOrder)) {
		$("#tipInfo").text("请输入上一个促销序列编号");
		$("#previosOrder").focus();
		return;
	}
	
	
	if (isNullValue(nextOrder)) {
		$("#tipInfo").text("请输入下一个促销序列编号");
		$("#nextOrder").focus();
		return;
	}
	
	if (isNullValue(startTime)) {
		$("#tipInfo").text("请输入开始时间");
		$("#startTime").focus();
		return;
	}
//	
//	if(!isPhone(userPhone)){
//		$("#tipInfo").text("请输入正确的手机号码");
//		$("#phone").focus();
//		return;
//	}
	
	if (isNullValue(endTime)) {
		$("#tipInfo").text("请输入出结束时间");
		$("#endTime").focus();
		return;
	}
	
	if (isNullValue(hasLoad)) {
		$("#tipInfo").text("请输入是否加载");
		$("#hasLoad").focus();
		return;
	}
	
	if (isNullValue(isvaliade)) {
		$("#tipInfo").text("请选择是否有效");
		$("#isvaliade").focus();
		return;
	}
	

	
	var param = "promotionSequenceId="+promotionSequenceId+"&promotionId="+promotionId+"&previosOrder="+previosOrder+"&nextOrder="+nextOrder+"&startTime="+startTime+"&endTime="+endTime+"&hasLoad="+hasLoad+"&isvaliade="+isvaliade+"&r="+(new Date()).getTime();
	
	$.ajax({
		  url: "/backstage/promotionSequence/addPromotionSequence.action",
	      type: "POST",
	      data: param,
	      dataType: "json",
	      async:false,
	      success: function(msg){
	    	  if(msg.success){
	    		  window.location.href = "/backstage/promotionSequence/queryPromotionSequenceByPageNum.action?r="+(new Date()).getTime();
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
 * 取消添加商品信息
 */
function canclePromotionSequence(){
	$("#promotionSequencePopdiv").attr("class","promotionSequencePopdivhidden");
}


function setStartPromotionSequence(){
	
	var startPromotionSequenceId=$("#startPromotionSequenceId").val();
	
	if (isNullValue(startPromotionSequenceId)) {
		$("#tipInfo").text("请输入起始促销序列编号");
		$("#startPromotionSequenceId").focus();
		return;
	}
	var param = "startPromotionSequenceId="+startPromotionSequenceId+"&r="+(new Date()).getTime();
	
	$.ajax({
		  url: "/backstage/promotionSequence/refreshDateToRedis.action",
	      type: "POST",
	      data: param,
	      dataType: "json",
	      async:false,
	      success: function(msg){
	    	  if(msg.success){
	    		  window.location.href = "/backstage/promotionSequence/queryPromotionSequenceByPageNum.action?r="+(new Date()).getTime();
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