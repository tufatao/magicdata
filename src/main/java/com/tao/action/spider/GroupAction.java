package com.tao.action.spider;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.tao.entity.BidGroup;
import com.tao.entity.BidKeyword;
import com.tao.service.GroupService;
import com.tao.util.MyString;
import com.tao.util.Pager;
import com.tao.util.PagerHelper;

public class GroupAction {

	GroupService groupService;

	/**
	 * key查询分页  组合条件层
	 */
	public String groupPage(){
		//获取前台参数
		String pageSize = getParameter("pageSize");
		String name = getParameter("name");
		//移除查询条件
		removeSessionAttribute("groupPage");
		//封装查询对象
		BidGroup groupPage = new BidGroup();
		if(null == pageSize){
			pageSize = "10";
		}
		groupPage.setStr1(pageSize);
		groupPage.setName(name);
		List groupList = getPagedGroup(groupPage);
		setSessionAttribute("groupPage", groupPage);
		setAttribute("groupList", groupList);
		return "groupList";
	}
	
	
	/**
	 * task查询分页 跳转分页层
	 */
	public String groupPaging(){
		BidGroup groupPage = (BidGroup) getSessionAttribute("groupPage");
		setAttribute("groupList", getPagedGroup(groupPage) );
		return "groupList";
	}
	

	/**
	 * key查询分页 查询数据层
	 */
	public List getPagedGroup(BidGroup group){
		int pageSize;
		pageSize = null == group.getStr1() ? 10 : Integer.parseInt(group.getStr1() );
		// 记录总条数
		int rowNum = groupService.getGroupRow(group);
		Pager pager = PagerHelper.getPager(ServletActionContext.getRequest(),
				rowNum, pageSize);
		setAttribute("currentPage", pager.getCurrentPage());
		setAttribute("totalPages", pager.getTotalPages());
		setAttribute("rowNum", rowNum);
		// 当前页列表
		List groupList = groupService.getPagedGroup(group, pager
				.getStartRow(), pager.getPageSize() );
		
		return groupList;
	}
	
	
	/**
	 * 添加分组
	 */
	public String addGroup(){
		//获取前台参数
		String name = getParameter("name");
		//编号
		String gid = getParameter("gid");
		//简介
		String brief = getParameter("brief");
		
		//包装对象
		BidGroup group = new BidGroup();
		group.setName(name);
		group.setBrief(brief);
		if(!MyString.isNullEmpty(gid)){
			BidGroup gp = groupService.getGroupById(gid);
			group.setBidGroup(gp);
		}
		//访问数据库
		groupService.saveObject(group);
		
		return "index";
	}
	
	//
	public String toAddGroup(){

		List groupList = groupService.getGroupList();
		setAttribute("groupList", groupList);
		return "addGroup";
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
		object = ServletActionContext.getRequest().getSession().getAttribute(
				inputName);
		return object;
	}

	/**
	 * 去掉Session参数值1.0
	 * 
	 * @return
	 */
	public void removeSessionAttribute(String inputName) {
		ServletActionContext.getRequest().getSession().removeAttribute(
				inputName);
	}

	/**
	 * 设置Session参数1.0 attFront 前台参数名 dataBack 后台传入值
	 * 
	 * @return
	 */
	public void setSessionAttribute(String attFront, Object dataBack) {
		ServletActionContext.getRequest().getSession().setAttribute(attFront,
				dataBack);
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

}
