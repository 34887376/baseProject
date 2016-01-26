window.onload=myfun;
function myfun()
{
    var u=window.location.pathname;
    var parent = $("#mine").parent().children().attr("class",null);
    if(u.indexOf('/home/')>-1||u.indexOf('/discuss/')>-1){
    	$("#mine").attr("class","footer-cur");
    }else if(u.indexOf('/interest/')>-1){
    	$("#interest").attr("class","footer-cur");
    }else{
    	$("#togther").attr("class","footer-cur");
    }
}
	
function changeItem(obj){
	var parent = $(obj).parent().children().attr("class",null);
	$(obj).attr("class","footer-cur");
	
	var u=window.location.pathname;
	alert(u);
}

//正则
function trimTxt(txt) {
    return txt.replace(/(^\s*)|(\s*$)/g, "");
}
function is_forbid(temp_str) {
    temp_str = trimTxt(temp_str);
    temp_str = temp_str.replace('*', "@");
    temp_str = temp_str.replace('--', "@");
    temp_str = temp_str.replace('/', "@");
    temp_str = temp_str.replace('+', "@");
    temp_str = temp_str.replace('\'', "@");
    temp_str = temp_str.replace('\\', "@");
    temp_str = temp_str.replace('$', "@");
    temp_str = temp_str.replace('^', "@");
    temp_str = temp_str.replace('.', "@");
    temp_str = temp_str.replace(';', "@");
    temp_str = temp_str.replace('<', "@");
    temp_str = temp_str.replace('>', "@");
    temp_str = temp_str.replace('"', "@");
    temp_str = temp_str.replace('=', "@");
    temp_str = temp_str.replace('{', "@");
    temp_str = temp_str.replace('}', "@");
    var forbid_str = new String('@,%,~,&');
    var forbid_array = new Array();
    forbid_array = forbid_str.split(',');
    var arlen = forbid_array.length;
    for (var i = 0; i < arlen;  i++) {
        if (temp_str.search(new RegExp(forbid_array[i])) != -1)
            return false;
    }
    return true;
}