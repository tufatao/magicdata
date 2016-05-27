package com.tao.mail;

import java.io.UnsupportedEncodingException;

public class MailFactory {
	public static MailSenderInfo getEmail() {
	  // 设置邮件服务器信息
	  MailSenderInfo mailInfo = new MailSenderInfo();
	  mailInfo.setMailServerHost("smtp.qiye.163.com");
	  mailInfo.setMailServerPort("25");
	  mailInfo.setValidate(true);
	  // 邮箱用户名  nick+" <"+from+">"
	  mailInfo.setUserName("service@csein.cn");
	  // 邮箱密码
	  mailInfo.setPassword("jw2014688");
	  // 发件人邮箱
	//设置自定义发件人昵称  
      String nick="";  
      try {  
          nick=javax.mail.internet.MimeUtility.encodeText("中安安全（应急）产业网");  
      } catch (UnsupportedEncodingException e) {  
          e.printStackTrace();  
      }     
      mailInfo.setFromAddress(nick + "<service@csein.cn>");
	  // 收件人邮箱
	  return mailInfo;
	 }
}
