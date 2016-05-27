package com.tao.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * BidGroup entity. @author MyEclipse Persistence Tools
 */

public class BidGroup implements java.io.Serializable {

	// Fields

	private Integer gid;
	private BidGroup bidGroup;
	private String name;
	private String brief;
	private Short state;
	private Short delFlag;
	private String str1;
	private String str2;
	private Set bidGroups = new HashSet(0);
	private Set bidKeywords = new HashSet(0);

	// Constructors

	/** default constructor */
	public BidGroup() {
	}

	/** full constructor */
	public BidGroup(BidGroup bidGroup, String name, String brief, Short state,
			Short delFlag, String str1, String str2, Set bidGroups,
			Set bidKeywords) {
		this.bidGroup = bidGroup;
		this.name = name;
		this.brief = brief;
		this.state = state;
		this.delFlag = delFlag;
		this.str1 = str1;
		this.str2 = str2;
		this.bidGroups = bidGroups;
		this.bidKeywords = bidKeywords;
	}

	// Property accessors

	public Integer getGid() {
		return this.gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public BidGroup getBidGroup() {
		return this.bidGroup;
	}

	public void setBidGroup(BidGroup bidGroup) {
		this.bidGroup = bidGroup;
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

	public Set getBidGroups() {
		return this.bidGroups;
	}

	public void setBidGroups(Set bidGroups) {
		this.bidGroups = bidGroups;
	}

	public Set getBidKeywords() {
		return this.bidKeywords;
	}

	public void setBidKeywords(Set bidKeywords) {
		this.bidKeywords = bidKeywords;
	}

}