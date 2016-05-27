package com.tao.daoB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao implements DaoIF{

	/**
	 * @author Fantom
	 * 
	 */
	PreparedStatement pre = null;
	ResultSet result = null;
	//关闭资源
	public void closeSource(){
		if(result != null){
			try {
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pre != null){
			try {
				pre.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//增、删、改
	public void excute(Connection conn, String sql) {
		try {
			pre = conn.prepareStatement(sql);
			pre.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeSource();
			}
	}
	//获取实例
	public static Dao me(){
		return new Dao();
	}
	/**
	 * 查
	 */
	//单个对象
	public ResultSet queryList(Connection conn, String sql) {
		try {
			pre = conn.prepareStatement(sql);
			result = pre.executeQuery();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ResultSet queryObject(Connection conn, String sql) {
		return null;
	}

}
