package com.tao.serviceIF;

import java.util.List;

import com.tao.entity.BidCus;


public interface CusServiceIF {
	/*
	 * hql专区
	 */
	// 保存对象
	void saveObject(Object object);
	// 更新对象
	void updateObject(Object object);
	//分页获取我的项目列表
		List getPagedCus(BidCus cus, int startNum, int pageSize);
		/**
		 * 获取客户条数1.0
		 */
		Integer getCusRow(BidCus cus);
		/**
		 * 获取指定客户
		 */
		BidCus getCusById(String cusid);
}
