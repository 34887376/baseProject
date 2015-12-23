package com.ms.domain.service.result.base;

import java.io.Serializable;

public class ServiceBaseResult implements Serializable{

	private static final long serialVersionUID = -3636062576833676584L;

	//处理是否成功
	private boolean success;
	
	//返回的信息
	private String msg;
	
	//业务码
	private int resultCode;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
}
