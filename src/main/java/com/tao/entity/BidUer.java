package com.tao.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * BidUer entity. @author MyEclipse Persistence Tools
 */

public class BidUer implements java.io.Serializable {

	// Fields

	private Integer uid;
	private BidArea bidArea;
	private String name;
	private String pw;
	private String company;
	private String brief;
	private String email;
	private String phone;
	private String area;
	private String cantact;
	private String regex;
	private Short state;
	private Short delFlag;
	private String str1;
	private String str2;
	private Set bidProjects = new HashSet(0);
	private Set bidCuses = new HashSet(0);

	// Constructors

	/** default constructor */
	public BidUer() {
	}

	/** full constructor */
	public BidUer(BidArea bidArea, String name, String pw, String company,
			String brief, String email, String phone, String area,
			String cantact, String regex, Short state, Short delFlag,
			String str1, String str2, Set bidProjects, Set bidCuses) {
		this.bidArea = bidArea;
		this.name = name;
		this.pw = pw;
		this.company = company;
		this.brief = brief;
		this.email = email;
		this.phone = phone;
		this.area = area;
		this.cantact = cantact;
		this.regex = regex;
		this.state = state;
		this.delFlag = delFlag;
		this.str1 = str1;
		this.str2 = str2;
		this.bidProjects = bidProjects;
		this.bidCuses = bidCuses;
	}

	// Property accessors

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public BidArea getBidArea() {
		return this.bidArea;
	}

	public void setBidArea(BidArea bidArea) {
		this.bidArea = bidArea;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return this.pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getBrief() {
		return this.brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCantact() {
		return this.cantact;
	}

	public void setCantact(String cantact) {
		this.cantact = cantact;
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

	public Set getBidProjects() {
		return this.bidProjects;
	}

	public void setBidProjects(Set bidProjects) {
		this.bidProjects = bidProjects;
	}

	public Set getBidCuses() {
		return this.bidCuses;
	}

	public void setBidCuses(Set bidCuses) {
		this.bidCuses = bidCuses;
	}

}