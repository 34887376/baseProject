package com.ms.domain.orderid.dao;

import java.io.Serializable;

public class OrderIdDAO implements Serializable{

	private static final long serialVersionUID = 722412997680117605L;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
