package com.tao.daoB;

import java.sql.Connection;
import java.sql.SQLException;

public class MysqlConn {
	/**
	 * @author Fantom
	 * 
	 */
	private  Connection conn = null;
	// 驱动程序名
	private  String driver = "com.mysql.jdbc.Driver";
	private  String url = "jdbc:mysql://127.0.0.1:3306/bids?userUnicode=true&amp;characterEncoding=utf-8";
	private  String userName = "root";
	private  String password = "chanyewang321";
	//获取MysqlConn
    public  Connection getMysqlConn(){
    	ConnAttrs connAttr = new ConnAttrs();
    	connAttr.setDriver(driver);
    	connAttr.setConnUrl(url);
    	connAttr.setUserName(userName);
    	connAttr.setPassword(password);
    	conn = ConnFactory.createConn(connAttr);
    	return conn;
    }
    
    public MysqlConn(String driver, String url, String userName, String password) {
		super();
		this.driver = driver;
		this.url = url;
		this.userName = userName;
		this.password = password;
	}

	public MysqlConn() {
		super();
	}
    public static MysqlConn me(){
    	return new MysqlConn();
    }

	//关闭连接
    public  void close(){
    	try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

}
