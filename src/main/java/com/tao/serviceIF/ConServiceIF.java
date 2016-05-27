package com.tao.serviceIF;

import java.util.List;

import com.tao.entity.BidContent;


public interface ConServiceIF {
	/*
	 * hql专区
	 */
	//获取指定任务content
	BidContent getConById(String cid);
	// 保存对象
	void saveObject(Object object);
	// 更新对象
	void updateObject(Object object);
	List getCons(BidContent con);
	//分页获取我的关键字列表
		List getPagedCon(BidContent con, int startNum, int pageSize);
		/**
		 * 获取关键字条数1.0
		 */
		Integer getConRow(BidContent con);
}
