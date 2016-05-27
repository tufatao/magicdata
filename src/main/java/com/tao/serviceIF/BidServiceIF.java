package com.tao.serviceIF;

import java.util.List;

import com.tao.entity.BidContentFilter;
import com.tao.entity.BidTask;


public interface BidServiceIF {
	/*
	 * hql专区
	 */
	//获取指定任务task
	BidTask getTaskById(String tid);
	// 保存对象
	void saveObject(Object object);
	// 更新对象
	void updateObject(Object object);
	//获取指定任务filter
	BidContentFilter getFilterById(String fid);
	//分页获取我的项目列表
	List getPagedTask(BidTask task, int startNum, int pageSize);
	/**
	 * 获取任务条数1.0
	 */
	Integer getTaskRow(BidTask task);	
}
