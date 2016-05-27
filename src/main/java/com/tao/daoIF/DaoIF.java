package com.tao.daoIF;

import java.util.List;

public interface DaoIF {

	/**
	 * HQL区域 Start
	 */

	// 保存对象
	void saveObjectHQL(Object object);

	// 更新对象
	void updateObjectHQL(Object object);

	// 删除单个对象
	void delObjectHQL(Object object);

	// 获取单个对象
	Object getObjectHQL(String HQL);

	// 获取对象集
	List<?> getObjectsHQL(String HQL);

	// 获取对象分页列表
	List<?> getWithPageHQL(int startNum, int maxPageSize, String HQL);

	/**
	 * HQL区域 End
	 */

	/*
	 * SQL区域 Start
	 */

	/*
	 * SQL区域 End
	 */
}