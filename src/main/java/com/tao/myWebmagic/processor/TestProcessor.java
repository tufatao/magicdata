package com.tao.myWebmagic.processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tao.entity.BidContent;
import com.tao.entity.BidContentFilter;
import com.tao.entity.BidTask;
import com.tao.myWebmagic.util.DateFormat;
import com.tao.myWebmagic.util.ObjSerialize;
import com.tao.myWebmagic.util.RuleSelect;
import com.tao.util.ForMd5;
import com.tao.util.MyString;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 招投标采集任务类
 * 
 * @author Fantom
 * 
 */
public class TestProcessor implements PageProcessor {

	private Logger logger = Logger.getLogger(getClass());
	String dateRegex = "$R20\\d{2}[-年][0-1]?\\d[-月][0-3]?\\d";

	// private String filePath = "E:\\urlMap\\urlMaptest.txt";
	Site site;

	private BidTask task;

	// 参考时间
	private Date tempDate = DateFormat.getCurDate(-2);
	// 当前时间
	private Date collectDate = DateFormat.getCurDate(0);

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

	/**
	 * 列表页
	 * 
	 * @param page
	 */
	public List processList(Page page) {
		logger.info("列表页分析: " + page.getUrl());
		String urlRegion, urlRegex;
		urlRegion = task.getUrlRegionRegex();

		urlRegex = task.getUrlRegex();

		Html html = page.getHtml();
		// css 选择器筛选url区域
		// 列表页
		List links = RuleSelect.processRule(html, urlRegion).links()
				.regex(urlRegex.substring(2)).all();
		return links;
		// content是用别的爬虫工具抽取到的正文
		// .$("div.title") //css 选择，Java里虽然很少有$符号出现，不过貌似$作为方法名是合法的
		// .xpath("//@href") //提取链接
		// .regex(".*blog.*") //正则匹配过滤
		// .all(); //转换为string列表

	}

	/**
	 * 详情页
	 * 
	 * @param page
	 */
	public BidContent processDetail(Page page) {

		String url = page.getRequest().getUrl();
		logger.info("详情页分析: " + url);
		// 警告提示
		StringBuffer sb = new StringBuffer("警告：");
		// 内容提取规则
		BidContentFilter filter = null;
		// 跳过标志;
		boolean skipFlag = false;
		// 要提取的字段
		String title, content, area, comeFrom, dateTo, str1, pubDate;
		{
			/**
			 * 初始化数据
			 */

			Object[] filters = task.getBidContentFilters().toArray();
			if (filters.length > 0) {
				filter = (BidContentFilter) filters[0];
			} else {
				sb.append("\n" + task.getName() + "-----未设置Filter");
			}

			title = "未采集到数据..";
			content = "未采集到数据..";
			area = "未采集到数据..";
			comeFrom = "未采集到数据..";
			dateTo = "未采集到数据..";
			str1 = "未采集到数据..";
			pubDate = "未采集到数据..";

		}

		if (null != filter) {

			String pubDateRegionRegex, conRegex, titleRegex;
			//
			pubDateRegionRegex = filter.getPublishdateRegex();
			conRegex = filter.getContentRegex();
			titleRegex = filter.getTitleRegex();

			Html html = page.getHtml();
			// 提取标题
			if (!MyString.isNullEmpty(titleRegex)) {
				String titleS = RuleSelect.processRule(html, titleRegex)
						.toString();
				if (null != titleS) {
					title = titleS.trim();
				} else {
					logger.info("标题为空，跳过此页面 ：" + url);
					sb.append("\n" + filter.getName() + "-未获取到-" + url
							+ "-的Title");
					skipFlag = true;
				}
			} else {
				sb.append("\n" + filter.getName() + "-----未设置titleRegex");
			}
			// 获取页面
			if (!MyString.isNullEmpty(conRegex)) {
				String contentS = RuleSelect.processRule(html, conRegex)
						.toString();
				if (null != contentS) {
					content = contentS.trim();
				} else {
					logger.info("内容为空，跳过此页面 ：" + url);
					sb.append("\n" + filter.getName() + "-未获取到-" + url
							+ "-的文章内容");
					skipFlag = true;
				}

			} else {
				sb.append("\n" + filter.getName() + "-----未设置conRegex");
			}
			// 获取发布时间
			if (!MyString.isNullEmpty(pubDateRegionRegex)) {
				// 发布时间 $R20\d{2}-[0-1]?\d-[0-3]?\d\s*?[0-2]?\d:[0-5]?\d
				Selectable pubT = RuleSelect.processRule(html,
						pubDateRegionRegex);
				if (null != pubT) {
					String pubTi = RuleSelect.processRule(pubT, dateRegex)
							.toString();
					if (null != pubTi) {
						String pubTime = pubTi.trim();
						pubDate = DateFormat.regexDate(pubTime);
						Date pubnum = DateFormat.getDateDay(pubDate);
						if (pubnum.compareTo(tempDate) < 0) {

							sb.append("\n" + filter.getName() + "-此页面-" + url
									+ "-的" + pubDate + "-不满足发布时间约束");

							skipFlag = true;
						}

						page.putField("pubDate", pubDate);
					} else {
						sb.append("\n" + filter.getName() + "-未获取到-" + url
								+ "-的发布时间");
					}
				}
			} else {
				sb.append("\n" + filter.getName()
						+ "-----未设置pubDateRegionRegex");
			}

			// 要排除的内容 脚本

			// 要排除的内容 广告
			String adRegex = filter.getStr1();
			if (!MyString.isNullEmpty(adRegex)) {
				String ad = RuleSelect.processRule(html, pubDateRegionRegex)
						.toString();
				if (null != ad && null != content) {
					content = content.replace(ad, "");
				} else {
					sb.append("\n" + filter.getName() + "-未获取到-" + url
							+ "-的广告内容");
				}

			} else {
				sb.append("\n" + filter.getName() + "-----未设置adRegex");
			}
		}

		//警示提醒
		if(sb.length() > 5){
			System.out.println(sb.toString());
		}
		// 关键字keyword
		String keynum = (String) page.getRequest().getExtra("keynum");
		if (skipFlag) {
			// skip this page
			page.setSkip(true);
		}

		// 信息封装
		BidContent con = new BidContent();
		con.setTitle(title);
		con.setContent(content);
		con.setKeyWord(keynum);
		con.setPubDate(pubDate);
		con.setStartDate(DateFormat.getFormatDate(collectDate));
		con.setStr2(task.getWebname());
		con.setSourceUrl(url);
		con.setStr1("" + task.getBidProject().getPid());
		return con;
		// page.putField("tags",html.xpath("//div[@class='BlogTags']/a/text()").all());
	}

	/**
	 * 起始页
	 */
	@Override
	public void process(Page page) {
		/*
		 * 列表页listRegex , 文章页urlRegex ;
		 */

		logger.info("起始页分析: " + page.getUrl());
		String listRegex, urlRegex;
		listRegex = task.getListRegex();
		urlRegex = task.getUrlRegex();
		// 列表页
		if (page.getUrl().regex(listRegex.substring(2)).match()) {
			processList(page);
		}
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public BidTask getTask() {
		return task;
	}

	public void setTask(BidTask task) {
		this.task = task;
	}
}
