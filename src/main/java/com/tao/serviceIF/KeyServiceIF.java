package com.tao.serviceIF;

import java.util.List;

import com.tao.entity.BidKeyword;
import com.tao.entity.BidTask;


public interface KeyServiceIF {
	/*
	 * hql专区
	 */
	// 保存对象
	void saveObject(Object object);
	// 更新对象
	void updateObject(Object object);
	//分页获取我的关键字列表
		List getPagedKey(BidKeyword key, int startNum, int pageSize);
		/**
		 * 获取关键字条数1.0
		 */
		Integer getKeyRow(BidKeyword key);
}
