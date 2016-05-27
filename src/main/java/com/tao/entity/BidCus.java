package com.tao.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * BidCus entity. @author MyEclipse Persistence Tools
 */

public class BidCus implements java.io.Serializable {

	// Fields

	private Integer cusid;
	private BidProject bidProject;
	private BidArea bidArea;
	private BidUer bidUer;
	private String name;
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
	private Set bidCusCons = new HashSet(0);
	private Set bidCusKeies = new HashSet(0);

	// Constructors

	/** default constructor */
	public BidCus() {
	}

	/** full constructor */
	public BidCus(BidProject bidProject, BidArea bidArea, BidUer bidUer,
			String name, String company, String brief, String email,
			String phone, String area, String cantact, String regex,
			Short state, Short delFlag, String str1, String str2,
			Set bidCusCons, Set bidCusKeies) {
		this.bidProject = bidProject;
		this.bidArea = bidArea;
		this.bidUer = bidUer;
		this.name = name;
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
		this.bidCusCons = bidCusCons;
		this.bidCusKeies = bidCusKeies;
	}

	// Property accessors

	public Integer getCusid() {
		return this.cusid;
	}

	public void setCusid(Integer cusid) {
		this.cusid = cusid;
	}

	public BidProject getBidProject() {
		return this.bidProject;
	}

	public void setBidProject(BidProject bidProject) {
		this.bidProject = bidProject;
	}

	public BidArea getBidArea() {
		return this.bidArea;
	}

	public void setBidArea(BidArea bidArea) {
		this.bidArea = bidArea;
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

	public Set getBidCusCons() {
		return this.bidCusCons;
	}

	public void setBidCusCons(Set bidCusCons) {
		this.bidCusCons = bidCusCons;
	}

	public Set getBidCusKeies() {
		return this.bidCusKeies;
	}

	public void setBidCusKeies(Set bidCusKeies) {
		this.bidCusKeies = bidCusKeies;
	}

}