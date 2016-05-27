package com.tao.action.spider;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.tao.entity.BidContent;
import com.tao.entity.BidContentFilter;
import com.tao.entity.BidProject;
import com.tao.entity.BidTask;
import com.tao.myWebmagic.util.RuleSelect;
import com.tao.service.ConService;
import com.tao.service.ProService;
import com.tao.service.TaskService;
import com.tao.util.Pager;
import com.tao.util.PagerHelper;

public class ConAction {

	TaskService taskService;
	ProService proService;
	ConService conService;
	
	/**
	 * content查询分页  组合条件层
	 */
	public String conPage(){
		//获取前台参数
		String pageSize = getParameter("pageSize");
		String title = getParameter("title");
		String content = getParameter("content");
		String pid = getParameter("pid");
		String kid = getParameter("kid");
		String webname = getParameter("webname");
		//移除查询条件
		removeSessionAttribute("conPage");
		//封装查询对象
		BidContent conPage = new BidContent();
		if(null == pageSize){
			pageSize = "10";
		}
		conPage.setStr1(pageSize);
		conPage.setKeyWord(kid);
		conPage.setContent(content);
		conPage.setTitle(title);
		//项目id
		conPage.setName(pid);
		conPage.setStr2(webname);
		List conList = getPagedCon(conPage);
		setSessionAttribute("conPage", conPage);
		setAttribute("conList", conList);

		List proList = proService.getProList();
		setAttribute("proList", proList);
		return "conList";
	}
	
	/**
	 * task查询分页 跳转分页层
	 */
	public String conPaging(){
		BidContent conPage = (BidContent) getSessionAttribute("conPage");
		setAttribute("conList", getPagedCon(conPage) );
		return "conList";
	}
	
	/**
	 * task查询分页 查询数据层
	 */
	public List getPagedCon(BidContent con){
		int pageSize;
		pageSize = null == con.getStr1()? 10 : Integer.parseInt(con.getStr1() );
		// 记录总条数
		int rowNum = conService.getConRow(con);
		Pager pager = PagerHelper.getPager(ServletActionContext.getRequest(),
				rowNum, pageSize);
		setAttribute("currentPage", pager.getCurrentPage());
		setAttribute("totalPages", pager.getTotalPages());
		setAttribute("rowNum", rowNum);
		// 当前页列表
		List conList = conService.getPagedCon(con, pager
				.getStartRow(), pager.getPageSize());
		
		return conList;
	}

	/**
	 * 删除信息
	 * @return
	 */
	public String delCon(){
		String cid = getParameter("cid");
		//任务
		BidContent con = conService.getConById(cid);
		conService.delObject(con);
		//所属项目
//		setAttribute("pro", con.getBidProject());
		return "success";
	}
	
	/**
	 * 信息详情
	 * @return
	 */
	public String toConDetail(){
		String cid = getParameter("cid");
		//任务
		BidContent con = conService.getConById(cid);
		setAttribute("con", con);
		//所属项目
//		setAttribute("pro", con.getBidProject());
		return "conDetail";
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

	public ConService getConService() {
		return conService;
	}

	public void setConService(ConService conService) {
		this.conService = conService;
	}

}
