package com.ms.kill.action;

import org.apache.log4j.Logger;

import base.test.base.action.BaseAction;

public class KillAction extends BaseAction{

	private static final long serialVersionUID = -143671418571563550L;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	public String showLottr(){
		
		return "showLottr";
	}
	
	public String kill(){
		return "kill";
	}

}
