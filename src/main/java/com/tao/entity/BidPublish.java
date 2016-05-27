package com.tao.entity;

/**
 * BidPublish entity. @author MyEclipse Persistence Tools
 */

public class BidPublish implements java.io.Serializable {

	// Fields

	private Integer pubid;
	private BidProject bidProject;
	private String webname;
	private String charset;
	private String url;
	private Short state;
	private Short delFlag;
	private String str1;
	private String str2;

	// Constructors

	/** default constructor */
	public BidPublish() {
	}

	/** full constructor */
	public BidPublish(BidProject bidProject, String webname, String charset,
			String url, Short state, Short delFlag, String str1, String str2) {
		this.bidProject = bidProject;
		this.webname = webname;
		this.charset = charset;
		this.url = url;
		this.state = state;
		this.delFlag = delFlag;
		this.str1 = str1;
		this.str2 = str2;
	}

	// Property accessors

	public Integer getPubid() {
		return this.pubid;
	}

	public void setPubid(Integer pubid) {
		this.pubid = pubid;
	}

	public BidProject getBidProject() {
		return this.bidProject;
	}

	public void setBidProject(BidProject bidProject) {
		this.bidProject = bidProject;
	}

	public String getWebname() {
		return this.webname;
	}

	public void setWebname(String webname) {
		this.webname = webname;
	}

	public String getCharset() {
		return this.charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
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