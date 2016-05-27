package com.tao.entity;

/**
 * BidContentFilter entity. @author MyEclipse Persistence Tools
 */

public class BidContentFilter implements java.io.Serializable {

	// Fields

	private Integer cfid;
	private BidTask bidTask;
	private String name;
	private String titleRegex;
	private String contentRegex;
	private String publishdateRegex;
	private String startDateRegex;
	private String endDateRegex;
	private String requireUnitRegex;
	private String offerUnitRegex;
	private String agentRegex;
	private String dealMoneyRegex;
	private String areaRegex;
	private String bidTypeRegex;
	private String adRegex;
	private String keyword;
	private Short state;
	private Short delFlag;
	private String str1;
	private String str2;

	// Constructors

	/** default constructor */
	public BidContentFilter() {
	}

	/** full constructor */
	public BidContentFilter(BidTask bidTask, String name, String titleRegex,
			String contentRegex, String publishdateRegex,
			String startDateRegex, String endDateRegex,
			String requireUnitRegex, String offerUnitRegex, String agentRegex,
			String dealMoneyRegex, String areaRegex, String bidTypeRegex,
			String adRegex, String keyword, Short state, Short delFlag,
			String str1, String str2) {
		this.bidTask = bidTask;
		this.name = name;
		this.titleRegex = titleRegex;
		this.contentRegex = contentRegex;
		this.publishdateRegex = publishdateRegex;
		this.startDateRegex = startDateRegex;
		this.endDateRegex = endDateRegex;
		this.requireUnitRegex = requireUnitRegex;
		this.offerUnitRegex = offerUnitRegex;
		this.agentRegex = agentRegex;
		this.dealMoneyRegex = dealMoneyRegex;
		this.areaRegex = areaRegex;
		this.bidTypeRegex = bidTypeRegex;
		this.adRegex = adRegex;
		this.keyword = keyword;
		this.state = state;
		this.delFlag = delFlag;
		this.str1 = str1;
		this.str2 = str2;
	}

	// Property accessors

	public Integer getCfid() {
		return this.cfid;
	}

	public void setCfid(Integer cfid) {
		this.cfid = cfid;
	}

	public BidTask getBidTask() {
		return this.bidTask;
	}

	public void setBidTask(BidTask bidTask) {
		this.bidTask = bidTask;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitleRegex() {
		return this.titleRegex;
	}

	public void setTitleRegex(String titleRegex) {
		this.titleRegex = titleRegex;
	}

	public String getContentRegex() {
		return this.contentRegex;
	}

	public void setContentRegex(String contentRegex) {
		this.contentRegex = contentRegex;
	}

	public String getPublishdateRegex() {
		return this.publishdateRegex;
	}

	public void setPublishdateRegex(String publishdateRegex) {
		this.publishdateRegex = publishdateRegex;
	}

	public String getStartDateRegex() {
		return this.startDateRegex;
	}

	public void setStartDateRegex(String startDateRegex) {
		this.startDateRegex = startDateRegex;
	}

	public String getEndDateRegex() {
		return this.endDateRegex;
	}

	public void setEndDateRegex(String endDateRegex) {
		this.endDateRegex = endDateRegex;
	}

	public String getRequireUnitRegex() {
		return this.requireUnitRegex;
	}

	public void setRequireUnitRegex(String requireUnitRegex) {
		this.requireUnitRegex = requireUnitRegex;
	}

	public String getOfferUnitRegex() {
		return this.offerUnitRegex;
	}

	public void setOfferUnitRegex(String offerUnitRegex) {
		this.offerUnitRegex = offerUnitRegex;
	}

	public String getAgentRegex() {
		return this.agentRegex;
	}

	public void setAgentRegex(String agentRegex) {
		this.agentRegex = agentRegex;
	}

	public String getDealMoneyRegex() {
		return this.dealMoneyRegex;
	}

	public void setDealMoneyRegex(String dealMoneyRegex) {
		this.dealMoneyRegex = dealMoneyRegex;
	}

	public String getAreaRegex() {
		return this.areaRegex;
	}

	public void setAreaRegex(String areaRegex) {
		this.areaRegex = areaRegex;
	}

	public String getBidTypeRegex() {
		return this.bidTypeRegex;
	}

	public void setBidTypeRegex(String bidTypeRegex) {
		this.bidTypeRegex = bidTypeRegex;
	}

	public String getAdRegex() {
		return this.adRegex;
	}

	public void setAdRegex(String adRegex) {
		this.adRegex = adRegex;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
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