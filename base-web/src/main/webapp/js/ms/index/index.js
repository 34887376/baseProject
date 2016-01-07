function findDetail(promotionId){
	window.location.href="/promotion/showPromotionDetail.action?promotionId="+promotionId+"&t="+(new Date()).getTime();
}
