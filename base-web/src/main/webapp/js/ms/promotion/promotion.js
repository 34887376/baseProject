
/**
 * 展示抽奖的div
 */
function showLotteryPopDiv(){
	
	$.ajax({
		  url: "/promotion/showLotterHtml.action",
	      type: "POST",
	      data: null,
	      dataType: "json",
	      async:false,
	      success: function(msg){
	    	  if(msg.success){
	    		  $("#lotteryContent").html(msg.htmlCode);
	    		  $("#lotteryContent").append(msg.javaScriptCode);
//	    		  document.head.appendChild(msg.cssCode);
	    		  $($('head')[0]).append(msg.cssCode);
	    		  //document.write(msg.cssCode);
	    	  }else{
	    		  alert(msg.msg);
	    		  $("#tipInfo").text(msg.msg);
	    	  }
	      },
	      error:function(msg){
	    	  alert(msg.msg);
	      }
		 });
	
	$("#showLotteryDiv").attr("class","lotteryDivShowStyle");
}

/**
 * 关闭抽奖div
 */
function closeLotteryPopDiv(){
	$("#showLotteryDiv").attr("class","lotteryDivHiddenStyle");
}