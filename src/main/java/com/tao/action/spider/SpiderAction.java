package com.tao.action.spider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;

import com.tao.entity.BidContentFilter;
import com.tao.entity.BidUer;
import com.tao.entity.BidKeyword;
import com.tao.entity.BidProject;
import com.tao.entity.BidTask;
import com.tao.myWebmagic.pipeline.MysqlPipeline2;
import com.tao.myWebmagic.processor.BidPageProcessor;
import com.tao.myWebmagic.util.CharsetUtil;
import com.tao.myWebmagic.util.DateFormat;
import com.tao.myWebmagic.util.ObjSerialize;
import com.tao.service.GroupService;
import com.tao.service.KeyService;
import com.tao.service.ProService;
import com.tao.service.TaskService;
import com.tao.util.MyString;
import com.tao.util.Pager;
import com.tao.util.PagerHelper;

public class SpiderAction {

	// 采集日志
	private Logger logger = Logger.getLogger(getClass());
	TaskService taskService;
	KeyService keyService;
	ProService proService;
	GroupService groupService;
	MysqlPipeline2 pipeline;

	/**
	 * 执行项目采集
	 * 
	 * @return
	 */
	public String runProject() {
		List tasks, keys;
		// 获取项目id
		String pid = getParameter("pid");
		BidProject pro = proService.getProById(pid);
		tasks = taskService.getTaskByPid(pid, BidTask.class);
		// 获取项目所属关键字
		keys = keyService.getKeyByPid(pid, BidKeyword.class);
		return processTask(tasks, keys, pro);
	}

	/**
	 * 执行采集任务
	 * 
	 * @return
	 */
	public String processTask(List tasks, List keys, BidProject pro) {

		// step1 获取采集任务
		// List tasks = getTasks();
		//
		// //step2 获取关键字
		// List keys = getKeys();

		// step3

		//发布日期区间
		Date minDate = null, maxDate = null;
		String minDateStr = getParameter("minDate");
		String maxDateStr = getParameter("maxDate");
		//request 任务数
		String taskS = getParameter("taskNum");
		//request 重试次数
		String retryS = getParameter("retry");
		//request 超时设置
		String timeOutS = getParameter("timeOut");
		//request 间隔时间
		String sleepTimeS = getParameter("sleepTime");
		int retry, timeOut, sleepTime, taskNum;
		if(MyString.isNullEmpty(minDateStr)){
			minDate = DateFormat.getCurDate(-2);
		}
		else {
			minDate = DateFormat.getDateDay(minDateStr);
			
		}
		if(MyString.isNullEmpty(maxDateStr)){
			maxDate = DateFormat.getCurDate(1);
		}
		else {
			maxDate = DateFormat.getDateDay(maxDateStr);
		}
		if(MyString.isNullEmpty(retryS)){
			retry = 3;
		}
		else{
			retry = Integer.parseInt(retryS);
		}if(MyString.isNullEmpty(taskS)){
			taskNum = 2;
		}
		else{
			taskNum = Integer.parseInt(taskS);
		}
		if(MyString.isNullEmpty(timeOutS)){
			timeOut = 100000;
		}
		else{
			timeOut = Integer.parseInt(timeOutS);
		}
		if(MyString.isNullEmpty(sleepTimeS)){
			sleepTime = 1000;
		}
		else{
			sleepTime = Integer.parseInt(sleepTimeS);
		}
		
//		System.out.println("retry" + retry + "timeOut" + timeOut + "sleepTime" + sleepTime);
		BidPageProcessor bidPageProcessor = new BidPageProcessor();
		bidPageProcessor.setMinDate(minDate);
		bidPageProcessor.setMaxDate(maxDate);
		bidPageProcessor.buildPath(pro.getName());
		bidPageProcessor.init();
		Site site = Site.me().setRetryTimes(retry).setTimeOut(timeOut)
				.setSleepTime(sleepTime);

		// 遍历采集任务tasks
		for (Object object : tasks) {
			BidTask task = (BidTask) object;
			String charset, startUrl;

			charset = task.getCharset();
			startUrl = task.getStartUrl();

			// 遍历关键字keys
			for (Object obj : keys) {
				BidKeyword key = (BidKeyword) obj;
				String keyname = key.getName();
				System.out.println(keyname);
				// 关键字id
				String kid = "" + key.getKid();
				String keyCode = CharsetUtil.getUrlCode(keyname, charset);
				String url = startUrl.replace("$KEYWORD", keyCode);

				Request request = new Request(url);
				request.putExtra("kid", kid);
				site = site.addStartRequest(request);
			}

			// 域名
			// site.setDomain(domain);
			// 网站编码
			site.setCharset(charset);
			// 传入site;
			bidPageProcessor.setSite(site);
			// 传入要采集的任务;
			bidPageProcessor.setTask(task);
			Spider.create(bidPageProcessor).pipeline(pipeline)
					.thread(taskNum).run();

		}

		// 序列化urlMap,持久化urlMap
		try {
			ObjSerialize.serializeObj(bidPageProcessor.getUrlMap(),
					bidPageProcessor.getFilePath());
		} catch (FileNotFoundException e) {
			logger.error("文件不存在：" + e);
		} catch (IOException e) {
			logger.error("IO异常：" + e);
		}
		return "success";
	}

	// 获取指定关键字
	public List getKeys() {
		// 指定项目
		String pid = getParameter("pid");
		// String proId = proService.getStyleById(proId);
		// 指定任务
		String kids[] = ServletActionContext.getRequest().getParameterValues(
				"kid");
		List keyList = new ArrayList();
		return keyList;
	}

	// 获取指定采集任务
	public List getTasks() {
		// 指定项目
		String pid = getParameter("pid");
		// String proId = proService.getStyleById(proId);
		// 指定任务
		String tids[] = ServletActionContext.getRequest().getParameterValues(
				"tid");
		List taskList = new ArrayList();
		return taskList;
	}


	public String toProcessTask() {
		// 初始化task分组

		List proList = proService.getProList();
		setAttribute("proList", proList);
		// 初始化key分组

		List groupList = groupService.getGroupList();
		setAttribute("groupList", groupList);
		return "processTask";
	}

	public String toRunProject() {

		List proList = proService.getProList();
		setAttribute("proList", proList);
		return "runProject";
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

	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public MysqlPipeline2 getPipeline() {
		return pipeline;
	}

	public void setPipeline(MysqlPipeline2 pipeline) {
		this.pipeline = pipeline;
	}

	public KeyService getKeyService() {
		return keyService;
	}

	public void setKeyService(KeyService keyService) {
		this.keyService = keyService;
	}

}
