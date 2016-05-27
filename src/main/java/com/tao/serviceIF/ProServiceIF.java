package com.tao.serviceIF;

import java.util.List;

import com.tao.entity.BidGroup;
import com.tao.entity.BidKeyword;
import com.tao.entity.BidProject;


public interface ProServiceIF {
	/*
	 * hql专区
	 */
	// 保存对象
	void saveObject(Object object);
	// 更新对象
	void updateObject(Object object);
	//获取对象
	BidProject getProById(String sid);
	//获取group列表
	List getProList();
	//分页获取我的关键字列表
	List getPagedPro(BidProject pro, int startNum, int pageSize);
	/**
	 * 获取关键字条数1.0
	 */
	Integer getProRow(BidProject pro);

}
