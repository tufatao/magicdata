package com.tao.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * BidProvince entity. @author MyEclipse Persistence Tools
 */

public class BidProvince implements java.io.Serializable {

	// Fields

	private Integer pid;
	private String name;
	private String brief;
	private String str1;
	private String str2;
	private Set bidAreas = new HashSet(0);

	// Constructors

	/** default constructor */
	public BidProvince() {
	}

	/** full constructor */
	public BidProvince(String name, String brief, String str1, String str2,
			Set bidAreas) {
		this.name = name;
		this.brief = brief;
		this.str1 = str1;
		this.str2 = str2;
		this.bidAreas = bidAreas;
	}

	// Property accessors

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrief() {
		return this.brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getStr1() {
		return this.str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public String getStr2() {
		return this.str2;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}

	public Set getBidAreas() {
		return this.bidAreas;
	}

	public void setBidAreas(Set bidAreas) {
		this.bidAreas = bidAreas;
	}

}