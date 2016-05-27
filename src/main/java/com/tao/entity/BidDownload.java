package com.tao.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * BidDownload entity. @author MyEclipse Persistence Tools
 */

public class BidDownload implements java.io.Serializable {

	// Fields

	private Integer did;
	private BidTask bidTask;
	private String webname;
	private String charset;
	private String url;
	private String page;
	private Short state;
	private Short delFlag;
	private String str1;
	private String str2;
	private Set bidContents = new HashSet(0);

	// Constructors

	/** default constructor */
	public BidDownload() {
	}

	/** full constructor */
	public BidDownload(BidTask bidTask, String webname, String charset,
			String url, String page, Short state, Short delFlag, String str1,
			String str2, Set bidContents) {
		this.bidTask = bidTask;
		this.webname = webname;
		this.charset = charset;
		this.url = url;
		this.page = page;
		this.state = state;
		this.delFlag = delFlag;
		this.str1 = str1;
		this.str2 = str2;
		this.bidContents = bidContents;
	}

	// Property accessors

	public Integer getDid() {
		return this.did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public BidTask getBidTask() {
		return this.bidTask;
	}

	public void setBidTask(BidTask bidTask) {
		this.bidTask = bidTask;
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

	public String getPage() {
		return this.page;
	}

	public void setPage(String page) {
		this.page = page;
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

	public Set getBidContents() {
		return this.bidContents;
	}

	public void setBidContents(Set bidContents) {
		this.bidContents = bidContents;
	}

}