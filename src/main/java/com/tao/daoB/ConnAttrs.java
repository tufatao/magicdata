package com.tao.daoB;

public class ConnAttrs {
	/**
	 * @author Fantom
	 * userName 用户名, password 密码, connUrl 连接地址
	 */
	private String userName,password,connUrl,driver;

	public ConnAttrs(String userName, String password, String connUrl,
			String driver) {
		super();
		this.userName = userName;
		this.password = password;
		this.connUrl = connUrl;
		this.driver = driver;
	}

	public ConnAttrs() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConnUrl() {
		return connUrl;
	}

	public void setConnUrl(String connUrl) {
		this.connUrl = connUrl;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

}
