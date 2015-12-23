package com.ms.domian.action.promotion.vo;

import java.io.Serializable;

import com.ms.domain.action.base.BaseActionResult;

public class LotteryHtmlVO extends BaseActionResult implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//html代码
	private String htmlCode;
	
	//js代码
	private String javaScriptCode;
	
	//css样式
	private String cssCode;

	public String getHtmlCode() {
		return htmlCode;
	}

	public void setHtmlCode(String htmlCode) {
		this.htmlCode = htmlCode;
	}

	public String getJavaScriptCode() {
		return javaScriptCode;
	}

	public void setJavaScriptCode(String javaScriptCode) {
		this.javaScriptCode = javaScriptCode;
	}

	public String getCssCode() {
		return cssCode;
	}

	public void setCssCode(String cssCode) {
		this.cssCode = cssCode;
	}
	
	

}
