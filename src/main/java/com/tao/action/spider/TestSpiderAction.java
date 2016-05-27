package com.tao.action.spider;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;

import com.tao.entity.BidContent;
import com.tao.entity.BidProject;
import com.tao.entity.BidTask;
import com.tao.myWebmagic.pipeline.MysqlPipeline2;
import com.tao.myWebmagic.processor.TestProcessor;
import com.tao.myWebmagic.util.CharsetUtil;
import com.tao.service.KeyService;
import com.tao.service.ProService;
import com.tao.service.TaskService;
import com.tao.util.Pager;
import com.tao.util.PagerHelper;

public class TestSpiderAction {

	// 采集日志
	private Logger logger = Logger.getLogger(getClass());
	TaskService taskService;
	ProService proService;
	KeyService keyService;
	MysqlPipeline2 pipeline;

	/**
	 * 任务采集测试入口
	 * 
	 * @return
	 */
	public String taskTest() {
		// 获取任务id
		String tid = getParameter("tid");
		// 封装任务
		BidTask task = taskService.getTaskById(tid);
		setAttribute("task", task);
		return "taskTest";
	}
	
	public String putJson(JSONObject jo){

		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter pw = null;

		try {
			pw = response.getWriter();
			pw.write(jo.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (pw != null) {
				pw.flush();
				pw.close();
			}
		}
		return null;
	}
	
	/**
	 * Step1:指定关键字
	 * @return
	 */
	public String pointKey() {
		// 获取任务id
		String tid = getParameter("tid");
		String taskName = taskService.getNameById(tid);
		
		JSONObject jo1 = new JSONObject();
		jo1.put("taskName", taskName);
		jo1.put("tid", tid);
		return putJson(jo1);
	}
	
	/**
	 * Step2:抓取url列表
	 * @return
	 */
	public String testUrls() {

		// step1 获取采集任务
		// List tasks = getTasks();
		// 获取任务id
		String tid = getParameter("tid");
		// 封装任务
		BidTask task = taskService.getTaskById(tid);
		TestProcessor testProcessor = new TestProcessor();
		Site site = Site.me().setRetryTimes(3).setTimeOut(100000)
				.setSleepTime(1000);

		String charset, startUrl;
		charset = task.getCharset();
		startUrl = task.getStartUrl();

		// 关键字id
		String keyName = getParameter("name");
		System.out.println(keyName);
		String keyCode = CharsetUtil.getUrlCode(keyName, charset);
		String url = startUrl.replace("$KEYWORD", keyCode);

		Request request = new Request(url);
		site = site.addStartRequest(request);

		// 域名
		// site.setDomain(domain);
		// 网站编码
		site.setCharset(charset);
		// 传入site;
		testProcessor.setSite(site);
		// 传入要采集的任务;
		testProcessor.setTask(task);
		TestSpider spider = new TestSpider();
		spider.setSite(site);
		List urls = spider.getUrls(request, testProcessor);
		
		setAttribute("urls", urls);
		setAttribute("task", task);
		return "urlList";
	}
	
	/**
	 * Step3:测试结果展示
	 * @return
	 */
	public String testDetail() {

		// 获取任务id
		String tid = getParameter("tid");
		String url = getParameter("url");
		// 封装任务
		BidTask task = taskService.getTaskById(tid);
		TestProcessor testProcessor = new TestProcessor();
		Site site = Site.me().setRetryTimes(3).setTimeOut(100000)
				.setSleepTime(1000);

		String charset;

		charset = task.getCharset();

		Request request = new Request(url);
		site = site.addStartRequest(request);

		// 域名
		// site.setDomain(domain);
		// 网站编码
		site.setCharset(charset);
		// 传入site;
		testProcessor.setSite(site);
		// 传入要采集的任务;
		testProcessor.setTask(task);
		TestSpider spider = new TestSpider();
		spider.setSite(site);
		BidContent con = spider.getCon(request, testProcessor);
		setAttribute("con", con);
		return "conDetail";
	}
	/**
	 * 执行采集任务
	 * 
	 * @return
	 */
	public String processTask() {

		// step1 获取采集任务
		// List tasks = getTasks();
		//
		// //step2 获取关键字
		// List keys = getKeys();
		// 获取任务id
		String tid = getParameter("tid");
		// 封装任务
		BidTask task = taskService.getTaskById(tid);
		TestProcessor testProcessor = new TestProcessor();
		Site site = Site.me().setRetryTimes(3).setTimeOut(100000)
				.setSleepTime(1000);

		String charset, startUrl;
		charset = task.getCharset();
		startUrl = task.getStartUrl();

		// 关键字id
		String keyName = getParameter("name");
		System.out.println(keyName);
		String keyCode = CharsetUtil.getUrlCode(keyName, charset);
		String url = startUrl.replace("$KEYWORD", keyCode);

		Request request = new Request(url);
		site = site.addStartRequest(request);

		// 域名
		// site.setDomain(domain);
		// 网站编码
		site.setCharset(charset);
		// 传入site;
		testProcessor.setSite(site);
		// 传入要采集的任务;
		testProcessor.setTask(task);
		TestSpider spider = new TestSpider();
		spider.setSite(site);
		List urls = spider.getUrls(request, testProcessor);
		setAttribute("urls", urls);
		setAttribute("task", task);
		return "urlList";
	}

	/**
	 * 测试结果展示
	 * 
	 * @return
	 */
	public String toTestDetail() {

		// 获取任务id
		String tid = getParameter("tid");
		String url = getParameter("url");
		// 封装任务
		BidTask task = taskService.getTaskById(tid);
		TestProcessor testProcessor = new TestProcessor();
		Site site = Site.me().setRetryTimes(3).setTimeOut(100000)
				.setSleepTime(1000);

		String charset, startUrl;

		charset = task.getCharset();

		Request request = new Request(url);
		site = site.addStartRequest(request);

		// 域名
		// site.setDomain(domain);
		// 网站编码
		site.setCharset(charset);
		// 传入site;
		testProcessor.setSite(site);
		// 传入要采集的任务;
		testProcessor.setTask(task);
		TestSpider spider = new TestSpider();
		spider.setSite(site);
		BidContent con = spider.getCon(request, testProcessor);
		setAttribute("con", con);
		return "conDetail";
	}

	// 获取指定关键字
	public List getKeys() {
		// 指定项目
		String kid = getParameter("kid");
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

	/**
	 * task查询分页 组合条件层
	 */
	public String taskPage() {
		// 获取前台参数
		String pageSize = getParameter("pageSize");
		String name = getParameter("name");
		String webname = getParameter("webname");
		String charset = getParameter("charset");
		// 移除查询条件
		removeSessionAttribute("taskPage");
		// 封装查询对象
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
	public String taskPaging() {
		BidTask taskPage = (BidTask) getSessionAttribute("taskPage");
		setAttribute("taskList", getPagedTask(taskPage));
		return "taskList";
	}

	/**
	 * task查询分页 查询数据层
	 */
	public List getPagedTask(BidTask task) {
		int pageSize;
		pageSize = null == task.getStr1() ? 10 : Integer.parseInt(task
				.getStr1());
		// 记录总条数
		int rowNum = taskService.getTaskRow(task);
		Pager pager = PagerHelper.getPager(ServletActionContext.getRequest(),
				rowNum, pageSize);
		setAttribute("currentPage", pager.getCurrentPage());
		setAttribute("totalPages", pager.getTotalPages());
		setAttribute("rowNum", rowNum);
		// 当前页列表
		List taskList = taskService.getPagedTask(task, pager.getStartRow(),
				pager.getPageSize());

		return taskList;
	}

	/**
	 * 添加任务
	 */
	public String addTask() {
		// 获取前台参数
		String name = getParameter("name");
		String pid = getParameter("sid");
		String webname = getParameter("webname");
		String charset = getParameter("charset");
		String startUrl = getParameter("startUrl");
		String cookie = getParameter("cookie");
		String userAgent = getParameter("userAgent");
		String urlRegex = getParameter("urlRegex");
		String urlRegionRegex = getParameter("urlRegionRegex");
		String listRegex = getParameter("listRegex");
		// 包装对象
		BidTask task = new BidTask();
		task.setName(name);
		task.setWebname(webname);
		task.setCharset(charset);
		task.setStartUrl(startUrl);
		task.setCookie(cookie);
		task.setUserAgent(userAgent);
		task.setUrlRegex(urlRegex);
		task.setUrlRegionRegex(urlRegionRegex);
		task.setListRegex(listRegex);
		// 所属项目
		BidProject pro = proService.getProById(pid);
		task.setBidProject(pro);
		// 可采集的
		task.setStr1("1");
		// 访问数据库
		taskService.saveObject(task);

		return "index";
	}

	public String toProcessTask() {
		// 初始化task分组

		List proList = proService.getProList();
		setAttribute("proList", proList);
		return "processTask";
	}

	public String toRunProject() {

		List proList = proService.getProList();
		setAttribute("proList", proList);
		return "runProject";
	}

	/**
	 * 添加提取规则
	 * 
	 * @return
	 */
	public String toAddFilter() {
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
