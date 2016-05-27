package com.tao.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * BidTask entity. @author MyEclipse Persistence Tools
 */

public class BidTask implements java.io.Serializable {

	// Fields

	private Integer tid;
	private BidProject bidProject;
	private String name;
	private String webname;
	private String charset;
	private String startUrl;
	private String cookie;
	private String userAgent;
	private String urlRegex;
	private String urlRegionRegex;
	private String listRegex;
	private Short state;
	private Short delFlag;
	private String str1;
	private String str2;
	private Set bidDownloads = new HashSet(0);
	private Set bidContents = new HashSet(0);
	private Set bidContentFilters = new HashSet(0);

	// Constructors

	/** default constructor */
	public BidTask() {
	}

	/** full constructor */
	public BidTask(BidProject bidProject, String name, String webname,
			String charset, String startUrl, String cookie, String userAgent,
			String urlRegex, String urlRegionRegex, String listRegex,
			Short state, Short delFlag, String str1, String str2,
			Set bidDownloads, Set bidContents, Set bidContentFilters) {
		this.bidProject = bidProject;
		this.name = name;
		this.webname = webname;
		this.charset = charset;
		this.startUrl = startUrl;
		this.cookie = cookie;
		this.userAgent = userAgent;
		this.urlRegex = urlRegex;
		this.urlRegionRegex = urlRegionRegex;
		this.listRegex = listRegex;
		this.state = state;
		this.delFlag = delFlag;
		this.str1 = str1;
		this.str2 = str2;
		this.bidDownloads = bidDownloads;
		this.bidContents = bidContents;
		this.bidContentFilters = bidContentFilters;
	}

	// Property accessors

	public Integer getTid() {
		return this.tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public BidProject getBidProject() {
		return this.bidProject;
	}

	public void setBidProject(BidProject bidProject) {
		this.bidProject = bidProject;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getStartUrl() {
		return this.startUrl;
	}

	public void setStartUrl(String startUrl) {
		this.startUrl = startUrl;
	}

	public String getCookie() {
		return this.cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getUserAgent() {
		return this.userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getUrlRegex() {
		return this.urlRegex;
	}

	public void setUrlRegex(String urlRegex) {
		this.urlRegex = urlRegex;
	}

	public String getUrlRegionRegex() {
		return this.urlRegionRegex;
	}

	public void setUrlRegionRegex(String urlRegionRegex) {
		this.urlRegionRegex = urlRegionRegex;
	}

	public String getListRegex() {
		return this.listRegex;
	}

	public void setListRegex(String listRegex) {
		this.listRegex = listRegex;
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

	public Set getBidDownloads() {
		return this.bidDownloads;
	}

	public void setBidDownloads(Set bidDownloads) {
		this.bidDownloads = bidDownloads;
	}

	public Set getBidContents() {
		return this.bidContents;
	}

	public void setBidContents(Set bidContents) {
		this.bidContents = bidContents;
	}

	public Set getBidContentFilters() {
		return this.bidContentFilters;
	}

	public void setBidContentFilters(Set bidContentFilters) {
		this.bidContentFilters = bidContentFilters;
	}

}