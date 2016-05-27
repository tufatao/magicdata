package com.tao.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * BidArea entity. @author MyEclipse Persistence Tools
 */

public class BidArea implements java.io.Serializable {

	// Fields

	private Integer aid;
	private BidProvince bidProvince;
	private String name;
	private String brief;
	private String str1;
	private String str2;
	private Set bidCuses = new HashSet(0);
	private Set bidContents = new HashSet(0);
	private Set bidUers = new HashSet(0);

	// Constructors

	/** default constructor */
	public BidArea() {
	}

	/** full constructor */
	public BidArea(BidProvince bidProvince, String name, String brief,
			String str1, String str2, Set bidCuses, Set bidContents, Set bidUers) {
		this.bidProvince = bidProvince;
		this.name = name;
		this.brief = brief;
		this.str1 = str1;
		this.str2 = str2;
		this.bidCuses = bidCuses;
		this.bidContents = bidContents;
		this.bidUers = bidUers;
	}

	// Property accessors

	public Integer getAid() {
		return this.aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public BidProvince getBidProvince() {
		return this.bidProvince;
	}

	public void setBidProvince(BidProvince bidProvince) {
		this.bidProvince = bidProvince;
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

	public Set getBidCuses() {
		return this.bidCuses;
	}

	public void setBidCuses(Set bidCuses) {
		this.bidCuses = bidCuses;
	}

	public Set getBidContents() {
		return this.bidContents;
	}

	public void setBidContents(Set bidContents) {
		this.bidContents = bidContents;
	}

	public Set getBidUers() {
		return this.bidUers;
	}

	public void setBidUers(Set bidUers) {
		this.bidUers = bidUers;
	}

}