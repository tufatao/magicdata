package com.tao.entity;

/**
 * BidCusKey entity. @author MyEclipse Persistence Tools
 */

public class BidCusKey implements java.io.Serializable {

	// Fields

	private Integer ckid;
	private BidCus bidCus;
	private BidKeyword bidKeyword;
	private String name;
	private String brief;
	private String regex;
	private Short state;
	private Short delFlag;
	private String str1;
	private String str2;

	// Constructors

	/** default constructor */
	public BidCusKey() {
	}

	/** full constructor */
	public BidCusKey(BidCus bidCus, BidKeyword bidKeyword, String name,
			String brief, String regex, Short state, Short delFlag,
			String str1, String str2) {
		this.bidCus = bidCus;
		this.bidKeyword = bidKeyword;
		this.name = name;
		this.brief = brief;
		this.regex = regex;
		this.state = state;
		this.delFlag = delFlag;
		this.str1 = str1;
		this.str2 = str2;
	}

	// Property accessors

	public Integer getCkid() {
		return this.ckid;
	}

	public void setCkid(Integer ckid) {
		this.ckid = ckid;
	}

	public BidCus getBidCus() {
		return this.bidCus;
	}

	public void setBidCus(BidCus bidCus) {
		this.bidCus = bidCus;
	}

	public BidKeyword getBidKeyword() {
		return this.bidKeyword;
	}

	public void setBidKeyword(BidKeyword bidKeyword) {
		this.bidKeyword = bidKeyword;
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

	public String getRegex() {
		return this.regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public Short getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(Short delFlag) {
		this.delFlag = delFlag;
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

}