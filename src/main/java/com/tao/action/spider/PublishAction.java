package com.tao.action.spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.tao.entity.BidContent;
import com.tao.service.ConService;

public class PublishAction {

	private ConService conService;
	
	/**
	 * 发布信息
	 */
	public String publishInfo(){
		//pid 项目id
		String pid = "2";
		BidContent content = new BidContent();
		content.setStr1(pid);
		List cons = conService.getCons(content);
		for (Object object : cons) {
			BidContent con = (BidContent) object;
			postInfo(con);
		}
		return "success";
	}
	
	/**
	 * 信息入库
	 * @param con
	 * @return
	 */
	public void postInfo(BidContent con){
		String catid, title, content, fromurl, addtime, params;
		catid = con.getKeyWord();
		title = con.getTitle();
		content = con.getContent();

//		if("GBK".equals(charset)){
//			String now;
//			String down = page.getRawText();
//			try {
//				now = new String(down.getBytes("gbk"),"utf-8");
//				page.setRawText(now);
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}	
//		}
		title = title.replace("%", "%25");
		title = title.replace("&", "%26");
		title = title.replace("+", "%2B");
		content = content.replace("$space", "&nbsp;");
		content = content.replace("%", "%25");
		content = content.replace("&", "%26");
		content = content.replace("+", "%2B");
		fromurl = con.getSourceUrl();
		addtime = con.getPubDate();
		params = "moduleid=21&auth=123456&username=fantom&status=3&catid=" + catid + "&title=" + title + "&content=" + content + "&fromurl=" + fromurl;
//		String postUrl = "http://www.zhongan.com/api/spiderAction.php";
		String postUrl = "http://test.csein.cn/api/spiderAction.php";
		sendPost(postUrl, params);
	}
	
	
	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public void sendPost(String url, String param) {
		OutputStreamWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			// 发送请求参数
			out.write(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public ConService getConService() {
		return conService;
	}

	public void setConService(ConService conService) {
		this.conService = conService;
	}
}
