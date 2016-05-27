package com.tao.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * BidProject entity. @author MyEclipse Persistence Tools
 */

public class BidProject implements java.io.Serializable {

	// Fields

	private Integer pid;
	private BidUer bidUer;
	private String name;
	private String brief;
	private Short state;
	private Short delFlag;
	private String str1;
	private String str2;
	private Set bidCuses = new HashSet(0);
	private Set bidPublishs = new HashSet(0);
	private Set bidKeywords = new HashSet(0);
	private Set bidTasks = new HashSet(0);

	// Constructors

	/** default constructor */
	public BidProject() {
	}

	/** full constructor */
	public BidProject(BidUer bidUer, String name, String brief, Short state,
			Short delFlag, String str1, String str2, Set bidCuses,
			Set bidPublishs, Set bidKeywords, Set bidTasks) {
		this.bidUer = bidUer;
		this.name = name;
		this.brief = brief;
		this.state = state;
		this.delFlag = delFlag;
		this.str1 = str1;
		this.str2 = str2;
		this.bidCuses = bidCuses;
		this.bidPublishs = bidPublishs;
		this.bidKeywords = bidKeywords;
		this.bidTasks = bidTasks;
	}

	// Property accessors

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public BidUer getBidUer() {
		return this.bidUer;
	}

	public void setBidUer(BidUer bidUer) {
		this.bidUer = bidUer;
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

	public Set getBidCuses() {
		return this.bidCuses;
	}

	public void setBidCuses(Set bidCuses) {
		this.bidCuses = bidCuses;
	}

	public Set getBidPublishs() {
		return this.bidPublishs;
	}

	public void setBidPublishs(Set bidPublishs) {
		this.bidPublishs = bidPublishs;
	}

	public Set getBidKeywords() {
		return this.bidKeywords;
	}

	public void setBidKeywords(Set bidKeywords) {
		this.bidKeywords = bidKeywords;
	}

	public Set getBidTasks() {
		return this.bidTasks;
	}

	public void setBidTasks(Set bidTasks) {
		this.bidTasks = bidTasks;
	}

}