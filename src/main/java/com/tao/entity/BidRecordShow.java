package com.tao.entity;

/**
 * BidRecordShow entity. @author MyEclipse Persistence Tools
 */

public class BidRecordShow implements java.io.Serializable {

	// Fields

	private Integer rsid;
	private Integer user;
	private Integer proNum;
	private Integer taskNum;
	private Integer cusNum;
	private Integer keyNum;
	private Integer conNum;
	private Integer pushConNum;
	private Integer typeNum;
	private Integer preNum;
	private Integer normalNum;
	private Integer changeNum;
	private Integer winNum;
	private String creDate;
	private Short state;
	private String str1;
	private String str2;

	// Constructors

	/** default constructor */
	public BidRecordShow() {
	}

	/** full constructor */
	public BidRecordShow(Integer user, Integer proNum, Integer taskNum,
			Integer cusNum, Integer keyNum, Integer conNum, Integer typeNum,
			Integer preNum, Integer normalNum, Integer changeNum, Integer winNum, 
			Integer pushConNum, String creDate, Short state, String str1, String str2) {
		this.user = user;
		this.proNum = proNum;
		this.taskNum = taskNum;
		this.cusNum = cusNum;
		this.keyNum = keyNum;
		this.conNum = conNum;
		this.typeNum = typeNum;
		this.preNum = preNum;
		this.normalNum = normalNum;
		this.changeNum = changeNum;
		this.winNum = winNum;
		this.pushConNum = pushConNum;
		this.creDate = creDate;
		this.state = state;
		this.str1 = str1;
		this.str2 = str2;
	}

	// Property accessors

	public Integer getRsid() {
		return this.rsid;
	}

	public void setRsid(Integer rsid) {
		this.rsid = rsid;
	}

	public Integer getUser() {
		return this.user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public Integer getProNum() {
		return this.proNum;
	}

	public void setProNum(Integer proNum) {
		this.proNum = proNum;
	}

	public Integer getTaskNum() {
		return this.taskNum;
	}

	public void setTaskNum(Integer taskNum) {
		this.taskNum = taskNum;
	}

	public Integer getCusNum() {
		return this.cusNum;
	}

	public void setCusNum(Integer cusNum) {
		this.cusNum = cusNum;
	}

	public Integer getKeyNum() {
		return this.keyNum;
	}

	public void setKeyNum(Integer keyNum) {
		this.keyNum = keyNum;
	}

	public Integer getConNum() {
		return this.conNum;
	}

	public void setConNum(Integer conNum) {
		this.conNum = conNum;
	}

	public Integer getTypeNum() {
		return this.typeNum;
	}

	public void setTypeNum(Integer typeNum) {
		this.typeNum = typeNum;
	}

	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
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

	public Integer getPreNum() {
		return preNum;
	}

	public void setPreNum(Integer preNum) {
		this.preNum = preNum;
	}

	public Integer getNormalNum() {
		return normalNum;
	}

	public void setNormalNum(Integer normalNum) {
		this.normalNum = normalNum;
	}

	public Integer getChangeNum() {
		return changeNum;
	}

	public void setChangeNum(Integer changeNum) {
		this.changeNum = changeNum;
	}

	public Integer getWinNum() {
		return winNum;
	}

	public void setWinNum(Integer winNum) {
		this.winNum = winNum;
	}

	public Integer getPushConNum() {
		return pushConNum;
	}

	public void setPushConNum(Integer pushConNum) {
		this.pushConNum = pushConNum;
	}

	public String getCreDate() {
		return creDate;
	}

	public void setCreDate(String creDate) {
		this.creDate = creDate;
	}

}