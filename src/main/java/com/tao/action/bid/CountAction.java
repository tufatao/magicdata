package com.tao.action.bid;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.struts2.ServletActionContext;

import com.tao.entity.BidRecordShow;
import com.tao.myWebmagic.util.DateFormat;
import com.tao.service.CountService;
import com.tao.util.MyString;

public class CountAction {
	CountService countService;
	String curDate;
	
	// 1.日统计;2周统计;3月统计;4季度统计;5年统计;
	// 统计计算
	//自定义统计, 待优化...
	public String myCount(){

		String startDate = getParameter("startDate");
		String endDate = getParameter("endDate");
		BidRecordShow rs = count(startDate, endDate);
		//state为2表示周统计
		rs.setState((short) 0);
		countService.saveObject(rs);
		
		return "";
	}
	
	//统计
	public void count(){
		curDate = DateFormat.getString(new SimpleDateFormat("yyyy-MM-dd"), DateFormat.getCurDate(0));
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		if(1 == day){
			//月末统计
			monthCount();
			//季度统计
			if(1 == cal.get(Calendar.MONTH) % 3){
				seasonCount();
			}
			//年末统计
			if(1 == cal.get(Calendar.MONTH)){
				yearCount();
		}
		}
		//周末统计
		if(5 == cal.get(Calendar.DAY_OF_WEEK)){
			weekCount();
		}
		//每日统计
		dailyCount();
		
	}

	//年统计, 待优化...
	public void yearCount(){
		String startDate = DateFormat.getString(new SimpleDateFormat("yyyy-MM-dd"), DateFormat.getCurYear(-1));

		BidRecordShow rs = count(startDate, curDate);
		//state为5表示年统计
		rs.setState((short) 5);
		rs.setStr1("年统计");
		countService.saveObject(rs);
	}

	//季度统计, 待优化...
	public void seasonCount(){
		String startDate = DateFormat.getString(new SimpleDateFormat("yyyy-MM-dd"), DateFormat.getCurMonth(-3));

		BidRecordShow rs = count(startDate, curDate);
		//state为4表示季度统计
		rs.setState((short) 4);
		rs.setStr1("季度统计");
		countService.saveObject(rs);
	}
	
	//月统计, 待优化...
	public void monthCount(){
		String startDate = DateFormat.getString(new SimpleDateFormat("yyyy-MM-dd"), DateFormat.getCurMonth(-1));

		BidRecordShow rs = count(startDate, curDate);
		//state为3表示月统计
		rs.setState((short) 3);
		rs.setStr1("月统计");
		countService.saveObject(rs);
	}
	
	//周统计, 待优化...
	public void weekCount(){
		String startDate = DateFormat.getString(new SimpleDateFormat("yyyy-MM-dd"), DateFormat.getCurDate(-7));

		BidRecordShow rs = count(startDate, curDate);
		//state为2表示周统计
		rs.setState((short) 2);
		rs.setStr1("周统计");
		countService.saveObject(rs);
	}
	
	//日常统计
	public void dailyCount(){
		BidRecordShow rs = count(curDate, curDate);
		//state为1表示日统计
		rs.setState((short) 1);
		rs.setStr1("日统计");
		countService.saveObject(rs);
		
	}
	
	//统计
	public BidRecordShow count(String startDate, String endDate){
		int keyNum, cusNum, pushConNum, conNum, preNum, changeNum, winNum, normalNum;
		keyNum = countKey(startDate, endDate);
		cusNum = countCus(startDate, endDate);
		pushConNum = countPushInfo(startDate, endDate);
		conNum = countInfo(startDate, endDate);
		preNum = countPre(startDate, endDate);
		normalNum = countNormal(startDate, endDate);
		changeNum = countChange(startDate, endDate);
		winNum = countWin(startDate, endDate);
		
		BidRecordShow rs = new BidRecordShow();
		rs.setKeyNum(keyNum);
		rs.setCusNum(cusNum);
		rs.setConNum(conNum);
		rs.setPreNum(preNum);
		rs.setNormalNum(normalNum);
		rs.setChangeNum(changeNum);
		rs.setWinNum(winNum);
		rs.setPushConNum(pushConNum);
		rs.setCreDate(endDate);
		return rs;
		
	}
	
	//Key合计
	public int countKey(String startDate, String endDate) {
		if(MyString.isNullEmpty(startDate) || MyString.isNullEmpty(endDate)){
			startDate = endDate = curDate;
		}
		String condition = " and cre_date >='" + startDate + "' and cre_date <='" + endDate + "'";
		String sql = "select count(distinct keyword) from bid_mail_record where 1=1 " + condition;
		return countService.getData(sql);
	}

	//客户合计
	public int countCus(String startDate, String endDate) {
		if(MyString.isNullEmpty(startDate) || MyString.isNullEmpty(endDate)){
			startDate = endDate = curDate;
		}
		String condition = " and cre_date >='" + startDate + "' and cre_date <='" + endDate + "'";
		String sql = "select count(distinct cus) from bid_mail_record where 1=1 " + condition;
		return countService.getData(sql);
	}

	//推送信息合计
	public int countPushInfo(String startDate, String endDate) {

		if(MyString.isNullEmpty(startDate) || MyString.isNullEmpty(endDate)){
			startDate = endDate = curDate;
		}
		String condition = " and cre_date >='" + startDate + "' and cre_date <='" + endDate + "'";
		String sql = "select count(distinct con) from bid_mail_record where 1=1 " + condition;
		return countService.getData(sql);
	}

	//信息合计
	public int countInfo(String startDate, String endDate) {

		if(MyString.isNullEmpty(startDate) || MyString.isNullEmpty(endDate)){
			startDate = endDate = curDate;
		}
		String condition = " and pub_date >='" + startDate + "' and pub_date <='" + endDate + "'";
		String sql = "select count(totid) from bid_total where 1=1 " + condition;
		return countService.getData(sql);
	}

	//招标预告合计
	public int countPre(String startDate, String endDate) {

		if(MyString.isNullEmpty(startDate) || MyString.isNullEmpty(endDate)){
			startDate = endDate = curDate;
		}
		String condition1 = " and con_type =" + 1;
		String condition = " and cre_date >='" + startDate + "' and cre_date <='" + endDate + "'";
		String sql = "select count(distinct con) from bid_mail_record where 1=1 " + condition + condition1;
		return countService.getData(sql);
	}

	//招标公告合计
	public int countNormal(String startDate, String endDate) {

		if(MyString.isNullEmpty(startDate) || MyString.isNullEmpty(endDate)){
			startDate = endDate = curDate;
		}
		String condition1 = " and con_type = " + 2;
		String condition = " and cre_date >='" + startDate + "' and cre_date <='" + endDate + "'";
		String sql = "select count(distinct con) from bid_mail_record where 1=1 " + condition + condition1;
		return countService.getData(sql);
	}
	
	//招标变更合计
	public int countChange(String startDate, String endDate) {

		if(MyString.isNullEmpty(startDate) || MyString.isNullEmpty(endDate)){
			startDate = endDate = curDate;
		}
		String condition1 = "and con_type = " + 3;
		String condition = " and cre_date >='" + startDate + "' and cre_date <='" + endDate + "'";
		String sql = "select count(distinct con) from bid_mail_record where 1=1 " + condition + condition1;
		return countService.getData(sql);
	}

	//中标公告合计
	public int countWin(String startDate, String endDate) {

		if(MyString.isNullEmpty(startDate) || MyString.isNullEmpty(endDate)){
			startDate = endDate = curDate;
		}
		String condition1 = "and con_type = " + 4;
		String condition = " and cre_date >='" + startDate + "' and cre_date <='" + endDate + "'";
		String sql = "select count(distinct con) from bid_mail_record where 1=1 " + condition + condition1;
		return countService.getData(sql);
	}

	/**
	 * 获取前台参数值1.0
	 * 
	 * @return
	 */
	public String getParameter(String inputName) {
		String parameter;
		parameter = ServletActionContext.getRequest().getParameter(inputName);
		return parameter;
	}

	/**
	 * 设置前台参数1.0 attFront 前台参数名 dataBack 后台传入值
	 * 
	 * @return
	 */
	public void setAttribute(String attFront, Object dataBack) {
		ServletActionContext.getRequest().setAttribute(attFront, dataBack);
	}

	/**
	 * 获取Session参数值1.0
	 * 
	 * @return
	 */
	public Object getSessionAttribute(String inputName) {
		Object object;
		object = ServletActionContext.getRequest().getSession()
				.getAttribute(inputName);
		return object;
	}

	/**
	 * 去掉Session参数值1.0
	 * 
	 * @return
	 */
	public void removeSessionAttribute(String inputName) {
		ServletActionContext.getRequest().getSession()
				.removeAttribute(inputName);
	}

	/**
	 * 设置Session参数1.0 attFront 前台参数名 dataBack 后台传入值
	 * 
	 * @return
	 */
	public void setSessionAttribute(String attFront, Object dataBack) {
		ServletActionContext.getRequest().getSession()
				.setAttribute(attFront, dataBack);
	}


	public CountService getCountService() {
		return countService;
	}

	public void setCountService(CountService countService) {
		this.countService = countService;
	}

	public String getCurDate() {
		return curDate;
	}

	public void setCurDate(String curDate) {
		this.curDate = curDate;
	}

}
