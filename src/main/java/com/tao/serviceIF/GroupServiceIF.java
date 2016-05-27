package com.tao.serviceIF;

import java.util.List;

import com.tao.entity.BidGroup;
import com.tao.entity.BidKeyword;


public interface GroupServiceIF {
	/*
	 * hql专区
	 */
	// 保存对象
	void saveObject(Object object);
	// 更新对象
	void updateObject(Object object);
	//获取对象
	BidGroup getGroupById(String gid);
	//获取group列表
	List getGroupList();
	//分页获取我的关键字列表
	List getPagedGroup(BidGroup group, int startNum, int pageSize);
	/**
	 * 获取关键字条数1.0
	 */
	Integer getGroupRow(BidGroup group);

}
