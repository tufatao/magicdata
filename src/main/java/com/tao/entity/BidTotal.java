package com.tao.entity;

/**
 * BidTotal entity. @author MyEclipse Persistence Tools
 */

public class BidTotal implements java.io.Serializable {

	// Fields

	private Integer totid;
	private Integer did;
	private Integer aid;
	private Integer tid;
	private String name;
	private String title;
	private String content;
	private String pubDate;
	private String startDate;
	private String endDate;
	private String requireUnit;
	private String offerUnit;
	private String agent;
	private String dealMoney;
	private String area;
	private String sourceUrl;
	private String newUrl;
	private String keyWord;
	private Short type;
	private Short state;
	private Short delFlag;
	private String str1;
	private String str2;

	// Constructors

	/** default constructor */
	public BidTotal() {
	}

	/** full constructor */
	public BidTotal(Integer did, Integer aid, Integer tid, String name,
			String title, String content, String pubDate, String startDate,
			String endDate, String requireUnit, String offerUnit, String agent,
			String dealMoney, String area, String sourceUrl, String newUrl,
			String keyWord, Short type, Short state, Short delFlag,
			String str1, String str2) {
		this.did = did;
		this.aid = aid;
		this.tid = tid;
		this.name = name;
		this.title = title;
		this.content = content;
		this.pubDate = pubDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.requireUnit = requireUnit;
		this.offerUnit = offerUnit;
		this.agent = agent;
		this.dealMoney = dealMoney;
		this.area = area;
		this.sourceUrl = sourceUrl;
		this.newUrl = newUrl;
		this.keyWord = keyWord;
		this.type = type;
		this.state = state;
		this.delFlag = delFlag;
		this.str1 = str1;
		this.str2 = str2;
	}

	// Property accessors

	public Integer getTotid() {
		return this.totid;
	}

	public void setTotid(Integer totid) {
		this.totid = totid;
	}

	public Integer getDid() {
		return this.did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public Integer getAid() {
		return this.aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Integer getTid() {
		return this.tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPubDate() {
		return this.pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRequireUnit() {
		return this.requireUnit;
	}

	public void setRequireUnit(String requireUnit) {
		this.requireUnit = requireUnit;
	}

	public String getOfferUnit() {
		return this.offerUnit;
	}

	public void setOfferUnit(String offerUnit) {
		this.offerUnit = offerUnit;
	}

	public String getAgent() {
		return this.agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getDealMoney() {
		return this.dealMoney;
	}

	public void setDealMoney(String dealMoney) {
		this.dealMoney = dealMoney;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSourceUrl() {
		return this.sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getNewUrl() {
		return this.newUrl;
	}

	public void setNewUrl(String newUrl) {
		this.newUrl = newUrl;
	}

	public String getKeyWord() {
		return this.keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
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