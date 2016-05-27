package com.tao.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * BidKeyword entity. @author MyEclipse Persistence Tools
 */

public class BidKeyword implements java.io.Serializable {

	// Fields

	private Integer kid;
	private BidProject bidProject;
	private BidGroup bidGroup;
	private String name;
	private String brief;
	private String keyRegex;
	private Short state;
	private Short delFlag;
	private String str1;
	private String str2;
	private Set bidCusKeies = new HashSet(0);

	// Constructors

	/** default constructor */
	public BidKeyword() {
	}

	/** full constructor */
	public BidKeyword(BidProject bidProject, BidGroup bidGroup, String name,
			String brief, String keyRegex, Short state, Short delFlag,
			String str1, String str2, Set bidCusKeies) {
		this.bidProject = bidProject;
		this.bidGroup = bidGroup;
		this.name = name;
		this.brief = brief;
		this.keyRegex = keyRegex;
		this.state = state;
		this.delFlag = delFlag;
		this.str1 = str1;
		this.str2 = str2;
		this.bidCusKeies = bidCusKeies;
	}

	// Property accessors

	public Integer getKid() {
		return this.kid;
	}

	public void setKid(Integer kid) {
		this.kid = kid;
	}

	public BidProject getBidProject() {
		return this.bidProject;
	}

	public void setBidProject(BidProject bidProject) {
		this.bidProject = bidProject;
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

	public String getKeyRegex() {
		return this.keyRegex;
	}

	public void setKeyRegex(String keyRegex) {
		this.keyRegex = keyRegex;
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

	public Set getBidCusKeies() {
		return this.bidCusKeies;
	}

	public void setBidCusKeies(Set bidCusKeies) {
		this.bidCusKeies = bidCusKeies;
	}

}