package com.tao.action.spider;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.tao.entity.BidCus;
import com.tao.entity.BidKeyword;
import com.tao.entity.BidProject;
import com.tao.service.CusService;
import com.tao.service.ProService;
import com.tao.util.Pager;
import com.tao.util.PagerHelper;

public class CheckAction {

	CusService cusService;
	ProService proService;

	/**
	 * 查看客户详情
	 * @return
	 */
	public String toCusDetail(){

		//客户
		String cusid = getParameter("cusid");
		BidCus cus = cusService.getCusById(cusid);
		setAttribute("cus", cus);
		//所属项目
		setAttribute("pro", cus.getBidProject());
		return "cusDetail";
	}
	/**
	 * customer查询分页 组合条件层
	 */
	public String cusPage() {
		// 获取前台参数
		String pageSize = getParameter("pageSize");
		String name = getParameter("name");
		String company = getParameter("company");
		String area = getParameter("area");
		// 移除查询条件
		removeSessionAttribute("cusPage");
		// 封装查询对象
		BidCus cusPage = new BidCus();
		cusPage.setStr1(pageSize);
		cusPage.setName(name);
		cusPage.setCompany(company);
		cusPage.setArea(area);
		List cusList = getPagedCus(cusPage);
		setSessionAttribute("cusPage", cusPage);
		setAttribute("cusList", cusList);
		return "cusList";
	}

	/**
	 * customer查询分页 跳转分页层
	 */
	public String cusPaging() {
		BidCus cusPage = (BidCus) getSessionAttribute("cusPage");
		setAttribute("cusList", getPagedCus(cusPage));
		return "cusList";
	}

	/**
	 * customer查询分页 查询数据层
	 */
	public List getPagedCus(BidCus cus) {
		int pageSize;
		pageSize = null == cus.getStr1() ? 10 : Integer.parseInt(cus.getStr1());
		// 记录总条数
		int rowNum = cusService.getCusRow(cus);
		Pager pager = PagerHelper.getPager(ServletActionContext.getRequest(),
				rowNum, pageSize);
		setAttribute("currentPage", pager.getCurrentPage());
		setAttribute("totalPages", pager.getTotalPages());
		setAttribute("rowNum", rowNum);
		// 当前页列表
		List cusList = cusService.getPagedCus(cus, pager.getStartRow(),
				pager.getPageSize());

		return cusList;
	}

	/**
	 * 添加客户
	 */
	public String addCus() {
		// 获取前台参数
		String name = getParameter("name");
		String company = getParameter("company");
		String brief = getParameter("brief");
		String pid = getParameter("pid");
		String email = getParameter("email");
		String phone = getParameter("phone");
		String contact = getParameter("contact");
		String area = getParameter("area");
		String cusRegex = getParameter("cusRegex");
		// 包装对象
		BidCus cus = new BidCus();
		cus.setName(name);
		cus.setBrief(brief);
		cus.setCompany(company);
		cus.setEmail(email);
		cus.setPhone(phone);
		//所属项目
		BidProject pro = proService.getProById(pid);
		cus.setBidProject(pro);
		cus.setCantact(contact);
		cus.setArea(area);
		cus.setRegex(cusRegex);
		// 访问数据库
		cusService.saveObject(cus);

		return "index";
	}

	/*
	 * 跳转添加客户页面
	 */
	public String toAddCus() {
		String pid = getParameter("pid");
		//所属项目
		BidProject pro = proService.getProById(pid);
		setAttribute("pro", pro);
		return "addCus";
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

	public CusService getCusService() {
		return cusService;
	}

	public void setCusService(CusService cusService) {
		this.cusService = cusService;
	}

	public ProService getProService() {
		return proService;
	}

	public void setProService(ProService proService) {
		this.proService = proService;
	}

}
