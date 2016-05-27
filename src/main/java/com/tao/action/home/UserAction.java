package com.tao.action.home;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.tao.entity.BidCus;
import com.tao.entity.BidCusKey;
import com.tao.entity.BidGroup;
import com.tao.entity.BidKeyword;
import com.tao.entity.BidProject;
import com.tao.entity.BidUer;
import com.tao.service.ConService;
import com.tao.service.CusService;
import com.tao.service.GroupService;
import com.tao.service.KeyService;
import com.tao.service.ProService;
import com.tao.service.UserService;
import com.tao.util.Pager;
import com.tao.util.PagerHelper;

public class UserAction {
	KeyService keyService;
	CusService cusService;
	ProService proService;
	GroupService groupService;
	UserService userService;
	ConService conService;
	
	/**
	 * 跳转登录页面
	 * @return
	 */
	public String toLogin() {

		// 用户id
		return "login";
	}
	
	/**
	 * 登录
	 * @return
	 */
	public String login(){
		//凭证
		String name = getParameter("name");
		String pw = getParameter("password");
		String pw2 = (String) userService.getPwByName(name);
		//验证
		boolean flag = pw.equals(pw2);
		if(flag){
			//验证成功
			return "index";
		}
		else {
			//验证失败
			return "regist";
		}
	}
	
	/**
	 * 跳转注册页面
	 * @return
	 */
	public String toRegist() {

		// 用户id
		return "regist";
	}
	
	/**
	 * 用户注册
	 * @return
	 */
	public String regist(){
		// 获取前台参数
				String name = getParameter("name");
				String company = getParameter("company");
				String brief = getParameter("brief");
				String email = getParameter("email");
				String phone = getParameter("phone");
				String contact = getParameter("contact");
				String area = getParameter("area");
				// 包装对象
				BidUer user = new BidUer();
				user.setName(name);
				user.setBrief(brief);
				user.setCompany(company);
				user.setEmail(email);
				user.setPhone(phone);
				user.setCantact(contact);
				user.setArea(area);
				// 访问数据库
				cusService.saveObject(user);

				return "index";
	}
	
	public String toEdit(){
		// 用户id
		String uid = getParameter("uid");
		// 包装对象
		BidUer user = userService.getUserById(uid);
		
		setAttribute("curUser", user);
		return "userEdit";
	}
	
	/**
	 * 完善信息
	 * @return
	 */
	public String edit(){
		// 用户id
		String uid = getParameter("uid");
		// 包装对象
		BidUer user = userService.getUserById(uid);
		
		String email = getParameter("email");
		String phone = getParameter("phone");
		String contact = getParameter("contact");
		String area = getParameter("area");
		user.setEmail(email);
		user.setPhone(phone);
		user.setCantact(contact);
		user.setArea(area);
		
		userService.updateObject(user);
		return detail(uid);
	}
	
	/**
	 * 跳转详情页面
	 * @return
	 */
	public String toDetail() {

		// 用户id
		String uid = getParameter("uid");
		return detail(uid);
	}
	
	/**
	 * 查看user详情
	 * @return
	 */
	public String detail(String uid){
		BidUer user = userService.getUserById(uid);
		
		setAttribute("curUser", user);
		return "userDetail";
	}

	/**
	 * 我的项目
	 * @return
	 */
	public String toMyPro(){
		// 用户id
		String uid = getParameter("uid");
		// 包装对象
		BidUer user = userService.getUserById(uid);
		
		setAttribute("myPro", user.getBidProjects());
		
		return "myPro";
	}
	
	
	
	
	/**
	 * 
	 * @return
	 */
	public String cusKeys() {

		// 客户
		String cusid = getParameter("cusid");
		BidCus cus = cusService.getCusById(cusid);
		// 所属客户
		setAttribute("cus", cus);
		List cusKeys = cusService.getCusKeys(cusid);
		setAttribute("cusKeys", cusKeys);
		return "myKeys";
	}

	/**
	 * 为客户指定关键字
	 * 
	 * @return
	 */
	public String relateKey() {

		// 客户
		String cusid = getParameter("cusid");
		BidCus cus = cusService.getCusById(cusid);
		String kids[] = getParameters("kid");

		for (String kid : kids) {
			BidKeyword key = keyService.getKeyById(kid);
			BidCusKey ck = new BidCusKey();
			ck.setBidCus(cus);
			ck.setBidKeyword(key);
			cusService.saveObject(ck);
		}
		// 所属客户
		// setAttribute("cus", cus);
		return cusKeys();
	}

	/**
	 * 去关联关键字
	 * 
	 * @return
	 */
	public String toRelateKey() {

		// 客户
		String cusid = getParameter("cusid");
		BidCus cus = cusService.getCusById(cusid);
		List groups = groupService.getGroups(new BidGroup());
		setAttribute("groups", groups);
		// 所属客户
		setAttribute("cus", cus);

		return "relateKey";
	}

	/**
	 * 查看我关注的关键字
	 * 
	 * @return
	 */
	public String myKeys() {

		// 客户
		String cusid = getParameter("cusid");
		BidCus cus = cusService.getCusById(cusid);
		List myKeys = cusService.getMyKeys(cusid);
		setAttribute("myKeys", myKeys);
		// 所属客户
		setAttribute("cus", cus);
		return "myKeys";
	}

	/**
	 * 查看客户详情
	 * 
	 * @return
	 */
	public String toCusDetail() {

		// 客户
		String cusid = getParameter("cusid");
		BidCus cus = cusService.getCusById(cusid);
		setAttribute("cus", cus);
		// 所属项目
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

	/*
	 * 跳转添加客户页面
	 */
	public String toAddCus() {
		String pid = getParameter("pid");
		// 所属项目
		BidProject pro = proService.getProById(pid);
		setAttribute("pro", pro);
		return "addCus";
	}

	/**
	 * 获取前台参数值1.0
	 * 
	 * @return
	 */
	public String[] getParameters(String inputName) {
		String parameters[];
		parameters = ServletActionContext.getRequest().getParameterValues(
				inputName);
		return parameters;
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

	public KeyService getKeyService() {
		return keyService;
	}

	public void setKeyService(KeyService keyService) {
		this.keyService = keyService;
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ConService getConService() {
		return conService;
	}

	public void setConService(ConService conService) {
		this.conService = conService;
	}

}
