package com.tao.action.spider;

import java.util.List;

import com.tao.entity.BidContent;
import com.tao.myWebmagic.processor.TestProcessor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;

public class TestSpider implements Task{

    protected Downloader downloader;
    protected Site site;
    protected String uuid;
	public TestSpider() {

    	downloader = new HttpClientDownloader();
	}
	protected PageProcessor pageProcessor;
    public List getUrls(Request request, TestProcessor testProcessor) {
        Page page = downloader.download(request, this);
        if (page == null) {
            System.out.println("为空");
        }
        return testProcessor.processList(page);
    }
    public BidContent getCon(Request request, TestProcessor testProcessor) {
        Page page = downloader.download(request, this);
        if (page == null) {
            System.out.println("为空");
        }
        return testProcessor.processDetail(page);
    }
	public PageProcessor getPageProcessor() {
		return pageProcessor;
	}
	public void setPageProcessor(PageProcessor pageProcessor) {
		this.pageProcessor = pageProcessor;
	}
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@Override
	public String getUUID() {

		return uuid;
	}
}
