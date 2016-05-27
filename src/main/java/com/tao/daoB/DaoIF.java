package com.tao.daoB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public interface DaoIF {
	/**
	 * @author Fantom
	 * 
	 */
	//增、删、改
	void excute(Connection conn,String sql);
	/**
	 * 查
	 */
	//单个对象
	ResultSet queryObject(Connection conn,String sql);
	//多个对象
	ResultSet queryList(Connection conn,String sql);
	//
}
