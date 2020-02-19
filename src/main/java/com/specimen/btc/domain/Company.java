package com.specimen.btc.domain;

import java.util.Date;

/**
 * 矿场单位
 * @author bianrenwei
 * @date 2020-02-08 11:48
 * Copyright © SSY All Rights Reserved.
 **/
public class Company {

	private Long id;
	
	private String name;
	
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
