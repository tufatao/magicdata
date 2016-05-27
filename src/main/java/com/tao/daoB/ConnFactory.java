package com.tao.daoB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnFactory{
	/**
	 * @author Fantom
	 * @param connAttrs
	 * @return
	 */
	public static Connection createConn(ConnAttrs connAttrs) {
		Connection conn = null;
		// 驱动程序名
		String driver = connAttrs.getDriver();
		// URL指向要访问的数据库名scutcs
		String urlTo = connAttrs.getConnUrl();

		// MySQL配置时的用户名
		String user = connAttrs.getUserName();

		// MySQL配置时的密码
		String password = connAttrs.getPassword();
		Properties prop = new Properties();
		// prop2.put("charSet", "gbk"); // 这里是解决中文乱码
		prop.put("user", user);
		prop.put("password", password);
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(urlTo, prop);
		} catch (Exception e) {
			System.out.println("获取连接失败");
			e.printStackTrace();
		}
		return conn;
	}

}
