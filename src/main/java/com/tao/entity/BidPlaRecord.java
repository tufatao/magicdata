package com.tao.entity;

/**
 * BidPlaRecord entity. @author MyEclipse Persistence Tools
 */

public class BidPlaRecord implements java.io.Serializable {

	// Fields

	private Integer prid;
	private Integer user;
	private Integer pro;
	private Integer task;
	private Integer cus;
	private Integer keyword;
	private Integer con;
	private Integer conType;
	private String toUrl;
	private String creDate;
	private String str1;
	private String str2;

	// Constructors

	/** default constructor */
	public BidPlaRecord() {
	}

	/** full constructor */
	public BidPlaRecord(Integer user, Integer pro, Integer task, Integer cus,
			Integer keyword, Integer con, Integer conType, String toUrl,
			String creDate, String str1, String str2) {
		this.user = user;
		this.pro = pro;
		this.task = task;
		this.cus = cus;
		this.keyword = keyword;
		this.con = con;
		this.conType = conType;
		this.toUrl = toUrl;
		this.creDate = creDate;
		this.str1 = str1;
		this.str2 = str2;
	}

	// Property accessors

	public Integer getPrid() {
		return this.prid;
	}

	public void setPrid(Integer prid) {
		this.prid = prid;
	}

	public Integer getUser() {
		return this.user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public Integer getPro() {
		return this.pro;
	}

	public void setPro(Integer pro) {
		this.pro = pro;
	}

	public Integer getTask() {
		return this.task;
	}

	public void setTask(Integer task) {
		this.task = task;
	}

	public Integer getCus() {
		return this.cus;
	}

	public void setCus(Integer cus) {
		this.cus = cus;
	}

	public Integer getKeyword() {
		return this.keyword;
	}

	public void setKeyword(Integer keyword) {
		this.keyword = keyword;
	}

	public Integer getCon() {
		return this.con;
	}

	public void setCon(Integer con) {
		this.con = con;
	}

	public Integer getConType() {
		return this.conType;
	}

	public void setConType(Integer conType) {
		this.conType = conType;
	}

	public String getToUrl() {
		return this.toUrl;
	}

	public void setToUrl(String toUrl) {
		this.toUrl = toUrl;
	}

	public String getCreDate() {
		return this.creDate;
	}

	public void setCreDate(String creDate) {
		this.creDate = creDate;
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