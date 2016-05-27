package com.tao.entity;

/**
 * BidCusCon entity. @author MyEclipse Persistence Tools
 */

public class BidCusCon implements java.io.Serializable {

	// Fields

	private Integer ccid;
	private BidContent bidContent;
	private BidCus bidCus;
	private String name;
	private String brief;
	private Short state;
	private Short delFlag;

	private String str1;
	private String str2;

	// Constructors

	/** default constructor */
	public BidCusCon() {
	}

	public BidCusCon(Integer ccid, BidContent bidContent, BidCus bidCus,
			String name, String brief, Short state, Short delFlag, String str1,
			String str2) {
		super();
		this.ccid = ccid;
		this.bidContent = bidContent;
		this.bidCus = bidCus;
		this.name = name;
		this.brief = brief;
		this.state = state;
		this.delFlag = delFlag;
		this.str1 = str1;
		this.str2 = str2;
	}

	public Integer getCcid() {
		return ccid;
	}

	public void setCcid(Integer ccid) {
		this.ccid = ccid;
	}

	public BidContent getBidContent() {
		return bidContent;
	}

	public void setBidContent(BidContent bidContent) {
		this.bidContent = bidContent;
	}

	public BidCus getBidCus() {
		return bidCus;
	}

	public void setBidCus(BidCus bidCus) {
		this.bidCus = bidCus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public Short getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Short delFlag) {
		this.delFlag = delFlag;
	}

	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public String getStr2() {
		return str2;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}
}