package com.tao.action.spider;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.tao.entity.BidContentFilter;
import com.tao.entity.BidProject;
import com.tao.entity.BidTask;
import com.tao.myWebmagic.util.RuleSelect;
import com.tao.service.ProService;
import com.tao.service.TaskService;
import com.tao.util.Pager;
import com.tao.util.PagerHelper;

public class TaskAction {

	TaskService taskService;
	ProService proService;
	
	/**
	 * task查询分页  组合条件层
	 */
	public String taskPage(){
		//获取前台参数
		String pageSize = getParameter("pageSize");
		String name = getParameter("name");
		String webname = getParameter("webname");
		String charset = getParameter("charset");
		//移除查询条件
		removeSessionAttribute("taskPage");
		//封装查询对象
		BidTask taskPage = new BidTask();
		taskPage.setStr1(pageSize);
		taskPage.setName(name);
		taskPage.setWebname(webname);
		taskPage.setCharset(charset);
		List taskList = getPagedTask(taskPage);
		setSessionAttribute("taskPage", taskPage);
		setAttribute("taskList", taskList);
		return "taskList";
	}
	
	/**
	 * task查询分页 跳转分页层
	 */
	public String taskPaging(){
		BidTask taskPage = (BidTask) getSessionAttribute("taskPage");
		setAttribute("taskList", getPagedTask(taskPage) );
		return "taskList";
	}
	
	/**
	 * task查询分页 查询数据层
	 */
	public List getPagedTask(BidTask task){
		int pageSize;
		pageSize = null == task.getStr1()? 10 : Integer.parseInt(task.getStr1() );
		// 记录总条数
		int rowNum = taskService.getTaskRow(task);
		Pager pager = PagerHelper.getPager(ServletActionContext.getRequest(),
				rowNum, pageSize);
		setAttribute("currentPage", pager.getCurrentPage());
		setAttribute("totalPages", pager.getTotalPages());
		setAttribute("rowNum", rowNum);
		setAttribute("pageSize", pageSize);
		// 当前页列表
		List taskList = taskService.getPagedTask(task, pager
				.getStartRow(), pager.getPageSize());
		
		return taskList;
	}

	/**
	 * 添加任务
	 */
	public String addTask(){
		//获取前台参数
		String name = getParameter("name");
		String pid = getParameter("pid");
		String webname = getParameter("webname");
		String charset = getParameter("charset");
		String startUrl = getParameter("startUrl");
		String cookie = getParameter("cookie");
		String userAgent = getParameter("userAgent");
		
		String urlType = getParameter("urlType");
		String urlRegionType = getParameter("urlRegionType");
		String listType = getParameter("listType");
		String urlRegex = getParameter("urlRegex");
		String urlRegionRegex = getParameter("urlRegionRegex");
		String listRegex = getParameter("listRegex");
		//包装对象
		BidTask task = new BidTask();
		task.setName(name);
		task.setWebname(webname);
		task.setCharset(charset);
		task.setStartUrl(startUrl);
		task.setCookie(cookie);
		task.setUserAgent(userAgent);
		task.setUrlRegex(RuleSelect.generateRule(urlType, urlRegex));
		task.setUrlRegionRegex(RuleSelect.generateRule(urlRegionType, urlRegionRegex));
		task.setListRegex(RuleSelect.generateRule(listType, listRegex));
		//所属项目
		BidProject pro = proService.getProById(pid);
		task.setBidProject(pro);
		//可采集的
		task.setStr1("1");
		//访问数据库
		taskService.saveObject(task);
		
		return "index";
	}
	
	//添加提取规则
	public String addFilter(){
		String tid = getParameter("tid");
		String name = getParameter("name");
		String titleType = getParameter("titleType");
		String conType = getParameter("conType");
		String pubDateType = getParameter("pubDateType");
		String endDateType = getParameter("endDateType");
		String unitType = getParameter("unitType");
		
		String titleRegex = getParameter("titleRegex");
		String conRegex = getParameter("conRegex");
		String pubDateRegex = getParameter("pubDateRegex");
		String endDateRegex = getParameter("endDateRegex");
		String unitRegex = getParameter("unitRegex");
		
		BidContentFilter filter = new BidContentFilter();
		filter.setName(name);
		filter.setTitleRegex(RuleSelect.generateRule(titleType, titleRegex));
		filter.setContentRegex(RuleSelect.generateRule(conType, conRegex));
		filter.setPublishdateRegex(RuleSelect.generateRule(pubDateType, pubDateRegex));
		filter.setEndDateRegex(RuleSelect.generateRule(endDateType, endDateRegex));
		filter.setRequireUnitRegex(RuleSelect.generateRule(unitType, unitRegex));

		BidTask task = taskService.getTaskById(tid);
		filter.setBidTask(task);
		taskService.saveObject(task);
		return taskPage();
	}
	
	/**
	 * Filter详情
	 * @return
	 */
	public String toFilterDetail(){
		String tid = getParameter("tid");
		//任务
		BidTask task = taskService.getTaskById(tid);
		BidContentFilter filter = (BidContentFilter) task.getBidContentFilters().toArray()[0];
		setAttribute("filter", filter);
		//所属任务
		setAttribute("taskName", task.getName());
		return "filterDetail";
	}
	
	/**
	 * 任务详情
	 * @return
	 */
	public String toTaskDetail(){
		String tid = getParameter("tid");
		//任务
		BidTask task = taskService.getTaskById(tid);
		setAttribute("task", task);
		//所属项目
		setAttribute("pro", task.getBidProject());
		return "taskDetail";
	}
	/**
	 * 添加采集任务
	 * @return
	 */
	public String toAddTask(){
		String pid = getParameter("pid");
		//所属项目
		BidProject pro = proService.getProById(pid);
		setAttribute("pro", pro);
		return "addTask";
	}

	/**
	 * 添加提取规则
	 * 
	 * @return
	 */
	public String toAddFilter(){
		String tid = getParameter("tid");
		BidTask task = taskService.getTaskById(tid);
		setAttribute("task", task);
		return "addFilter";
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

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public ProService getProService() {
		return proService;
	}

	public void setProService(ProService proService) {
		this.proService = proService;
	}

}
