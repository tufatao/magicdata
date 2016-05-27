package com.tao.action.spider;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.tao.entity.BidGroup;
import com.tao.entity.BidKeyword;
import com.tao.entity.BidProject;
import com.tao.service.GroupService;
import com.tao.service.KeyService;
import com.tao.service.ProService;
import com.tao.util.MyString;
import com.tao.util.Pager;
import com.tao.util.PagerHelper;

public class KeyAction {

	KeyService keyService;
	GroupService groupService;
	ProService proService;
	
	/**
	 * key查询分页  组合条件层
	 */
	public String keyPage(){
		//获取前台参数
		String pageSize = getParameter("pageSize");
		String name = getParameter("name");
		//移除查询条件
		removeSessionAttribute("keyPage");
		//封装查询对象
		BidKeyword keyPage = new BidKeyword();
		if(null == pageSize){
			pageSize = "10";
		}
		keyPage.setStr1(pageSize);
		keyPage.setName(name);
		List keyList = getPagedKey(keyPage);
		setSessionAttribute("keyPage", keyPage);
		setAttribute("keyList", keyList);
		return "keyList";
	}
	
	
	/**
	 * task查询分页 跳转分页层
	 */
	public String keyPaging(){
		BidKeyword keyPage = (BidKeyword) getSessionAttribute("keyPage");
		setAttribute("keyList", getPagedKey(keyPage) );
		return "keyList";
	}
	

	/**
	 * key查询分页 查询数据层
	 */
	public List getPagedKey(BidKeyword key){
		int pageSize;
		pageSize = null == key.getStr1() ? 10 : Integer.parseInt(key.getStr1() );
		// 记录总条数
		int rowNum = keyService.getKeyRow(key);
		Pager pager = PagerHelper.getPager(ServletActionContext.getRequest(),
				rowNum, pageSize);
		setAttribute("currentPage", pager.getCurrentPage());
		setAttribute("totalPages", pager.getTotalPages());
		setAttribute("rowNum", rowNum);
		// 当前页列表
		List taskList = keyService.getPagedKey(key, pager
				.getStartRow(), pager.getPageSize() );
		
		return taskList;
	}
	
	/**
	 * 更新关键字
	 * @return
	 */
	public String updateKey(){
		//获取前台参数
		String kid = getParameter("kid");
		String brief = getParameter("brief");
		String gid = getParameter("gid");
		String num = getParameter("num");

		String pre[] = {"Any{", "All{", "AnyN{", "AllN{"};
		//筛选规则
		String anyR[] = getParameters("anyRegex");
		String allR[] = getParameters("allRegex");
		String anyNR[] = getParameters("anyNRegex");
		String allNR[] = getParameters("allNRegex");
		String anyRegex = "", allRegex = "", anyNRegex = "", allNRegex = ""; 
		if(!MyString.isNullEmpty(anyR[0])){
			anyRegex = MyString.array2String(pre[0], anyR);
			
		}
		if(!MyString.isNullEmpty(allR[0])){
			allRegex = MyString.array2String(pre[1], allR);
			
		}
		if(!MyString.isNullEmpty(anyNR[0])){
			anyNRegex = MyString.array2String(pre[2], anyNR);
			
		}
		if(!MyString.isNullEmpty(allNR[0])){
			allNRegex = MyString.array2String(pre[3], allNR);
			
		}
		String keyRegex = anyRegex + allRegex + anyNRegex + allNRegex;
		//包装对象
		BidKeyword key = keyService.getKeyById(kid);
		if(!MyString.isNullEmpty(brief)){
			key.setBrief(brief);
			
		}
		if(!MyString.isNullEmpty(num)){
			key.setStr1(num);
			
		}
		if(!MyString.isNullEmpty(keyRegex)){
			key.setKeyRegex( keyRegex);
			
		}
		//所属分组
		if(!MyString.isNullEmpty(gid)){
			BidGroup group = groupService.getGroupById(gid);
			key.setBidGroup(group);
			
		}
		//访问数据库
		keyService.updateObject(key);
		
		return "index";
	}
	
	/**
	 * 添加关键字
	 */
	public String addKey(){
		//获取前台参数
		String name = getParameter("name");
		String num = getParameter("num");
		String pid = getParameter("pid");
		String brief = getParameter("brief");
		String gid = getParameter("gid");

		String pre[] = {"Any{", "All{", "AnyN{", "AllN{"};
		//筛选规则
		String anyR[] = getParameters("anyRegex");
		String allR[] = getParameters("allRegex");
		String anyNR[] = getParameters("anyNRegex");
		String allNR[] = getParameters("allNRegex");
		String anyRegex = "", allRegex = "", anyNRegex = "", allNRegex = ""; 
		if(!MyString.isNullEmpty(anyR[0])){
			anyRegex = MyString.array2String(pre[0], anyR);
			
		}
		if(!MyString.isNullEmpty(allR[0])){
			allRegex = MyString.array2String(pre[1], allR);
			
		}
		if(!MyString.isNullEmpty(anyNR[0])){
			anyNRegex = MyString.array2String(pre[2], anyNR);
			
		}
		if(!MyString.isNullEmpty(allNR[0])){
			allNRegex = MyString.array2String(pre[3], allNR);
			
		}
		String keyRegex = anyRegex + allRegex + anyNRegex + allNRegex;
		//包装对象
		BidKeyword key = new BidKeyword();
		key.setName(name);
		key.setStr1(num);
		key.setBrief(brief);
		key.setKeyRegex( keyRegex);
		//所属分组
		BidGroup group = groupService.getGroupById(gid);
		key.setBidGroup(group);
		//所属项目
		BidProject pro = proService.getProById(pid);
		key.setBidProject(pro);
		//访问数据库
		keyService.saveObject(key);
		
		return "index";
	}
	
	/**
	 * 查看关键字详情
	 * @return
	 */
	public String toKeyDetail(){

		String kid = getParameter("kid");
		//关键字
		BidKeyword key = keyService.getKeyById(kid);
		setAttribute("key", key);
		//所属项目
		setAttribute("pro", key.getBidProject());
		return "keyDetail";
	}
	
	/**
	 * 跳转添加关键字界面
	 * @return
	 */
	public String toAddKey(){
		List groupList = groupService.getGroupList();
		setAttribute("groupList", groupList);
		String pid = getParameter("pid");
		//所属项目
		BidProject pro = proService.getProById(pid);
		setAttribute("pro", pro);
		return "addKey";
	}
	
	/**
	 * 跳转修改关键字界面
	 * @return
	 */
	public String toUpdateKey(){
		List groupList = groupService.getGroupList();
		setAttribute("groupList", groupList);
		
		String kid = getParameter("kid");
		//关键字
		BidKeyword key = keyService.getKeyById(kid);
		setAttribute("key", key);
		setAttribute("pro", key.getBidProject());
		return "updateKey";
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
	 * 获取前台参数值集合1.0
	 * 
	 * @return
	 */
	public String[] getParameters(String inputName) {
		String parameters[];
		parameters = ServletActionContext.getRequest().getParameterValues(inputName);
		return parameters;
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


	public ProService getProService() {
		return proService;
	}


	public void setProService(ProService proService) {
		this.proService = proService;
	}
}
