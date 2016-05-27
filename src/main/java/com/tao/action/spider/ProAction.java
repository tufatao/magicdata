package com.tao.action.spider;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.tao.entity.BidProject;
import com.tao.service.ProService;
import com.tao.service.TaskService;

public class ProAction {

	TaskService taskService;
	ProService proService;
	
	/**
	 * 添加分组
	 */
	public String addGroup(){
		//获取前台参数
		String name = getParameter("name");
		String brief = getParameter("brief");
		//包装对象
		BidProject pro = new BidProject();
		pro.setName(name);
		pro.setBrief(brief);
		//访问数据库
		proService.saveObject(pro);
		
		return "success";
	}
	
	/**
	 * 查看项目详情
	 * @return
	 */
	public String toProDetail(){

		String pid = getParameter("pid");
		//所属项目
		BidProject pro = proService.getProById(pid);
		setAttribute("pro", pro);
		return "proDetail";
	}
	
	/**
	 * 跳转添加项目界面
	 * @return
	 */
	public String toAddPro(){

		return "addPro";
	}
	
	/**
	 * 跳转项目列表页面
	 * @return
	 */
	public String toProList() {

		List proList = proService.getProList();
		setAttribute("proList", proList);
		return "proList";
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

	public ProService getProService() {
		return proService;
	}

	public void setProService(ProService proService) {
		this.proService = proService;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	
}
