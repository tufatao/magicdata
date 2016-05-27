package com.tao.action.bid;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import ognl.SetPropertyAccessor;

import org.apache.struts2.ServletActionContext;

import com.tao.entity.BidContent;
import com.tao.entity.BidCus;
import com.tao.entity.BidCusCon;
import com.tao.entity.BidCusKey;
import com.tao.entity.BidKeyword;
import com.tao.entity.BidMailRecord;
import com.tao.entity.BidPlaRecord;
import com.tao.entity.BidRecordShow;
import com.tao.mail.MailFactory;
import com.tao.mail.MailSenderInfo;
import com.tao.mail.SimpleMailSender;
import com.tao.myWebmagic.util.DateFormat;
import com.tao.myWebmagic.util.RuleSelect;
import com.tao.service.BidService;
import com.tao.service.ConService;
import com.tao.service.CusService;
import com.tao.service.KeyService;
import com.tao.util.FileRead;
import com.tao.util.MyRegex;
import com.tao.util.MyString;
import com.tao.util.Pager;
import com.tao.util.PagerHelper;

public class BidAction {

	BidService bidService;
	ConService conService;
	KeyService keyService;
	CusService cusService;
	CountAction countAction;
	
	public String toCountShow(){
		//5日数据
		setAttribute("dailyShow", bidService.getRecord(5, 1, BidRecordShow.class));
		//周报
		setAttribute("weeklyShow", bidService.getRecord(2, 2, BidRecordShow.class));
		
		//月报
		setAttribute("monthShow", bidService.getRecord(2, 3, BidRecordShow.class));
		
		//季度
		setAttribute("seasonShow", bidService.getRecord(2, 4, BidRecordShow.class));
		
		//年报
		setAttribute("yearShow", bidService.getRecord(1, 5, BidRecordShow.class));
		
		return "countShow";
	}
	
	/*
	 * 区分招标信息类型
	 */
		public int getStyle(String title) {
			String changeinfo[] = { "变更", "更正", "失败", "澄清", "补充", "取消", "延期", "流标",
					"废标" };
			String preinfo[] = { "预告", "预公告", "预公示" };
			String getinfo[] = { "结果", "成交", "中选", "终止", "中标" };
			int styles[] = {1, 2, 3, 4};
			int style;
			//预告
			if(MyString.regexAny(title, preinfo)){
				style = styles[0];
				
			}
			else if(MyString.regexAny(title, changeinfo)){
				//变更
				style = styles[2];
				
			}
			else if(MyString.regexAny(title, getinfo)){
				//中标
				style = styles[3];
				
			}
			else {
				//公告
				style = styles[1];
				
			}
			return style;
		}

		
		/*
		 * 邮件发送方案二 通过正则获取码源中数据,并向对应客户发邮件
		 */
		public String getMailParams(){
			String filePath = getRealPath() + File.separator + "someFiles" + File.separator + "mailTemp.html";
			String mailContent = FileRead.readFileByLines(filePath);
			//获取待推送信息的客户
			List cusList = cusService.getKindCuses(BidCus.class);
			// 邮件内容 pre为邮件固定前缀，tail为邮件固定后缀。
			String subject = "交易信息 ";
			int mailCount = 1;
			//遍历cusList 
			for (Object obj : cusList) {
				StringBuffer sb = new StringBuffer();
				int count = 1;
				BidCus cus = (BidCus) obj;
				String mailStr = cus.getEmail();
				List myCons = conService.getMyCons("" + cus.getCusid(), BidContent.class);
				for (Object object : myCons) {
					BidContent c = (BidContent) object;
					String str = getMailTemp(c, count);

					{//添加推送记录
					BidMailRecord mRec = new BidMailRecord();
					//获取记录元素
					int cusid = cus.getCusid();
					int cid = c.getCid();
					//项目id
					int pid = Integer.parseInt(c.getStr1());
					int kid = Integer.parseInt(c.getKeyWord());
					int conType = c.getType();
					
					//int tid = c.get();任务id
					//平台链接
//					String toUrl = ;
					//生成日期
					String creDate = DateFormat.getString(new SimpleDateFormat("yyyy-MM-dd")
					, DateFormat.getCurDate(0));
					mRec.setCon(cid);
					mRec.setCus(cusid);
					mRec.setPro(pid);
					mRec.setKeyword(kid);
					mRec.setConType(conType);
					mRec.setCreDate(creDate);
					
					bidService.saveObject(mRec);
					}
					sb.append(str);
					count++;
				}

				String mailCon = mailContent.replace("#content#", sb);
				// 发送邮件
				String []mails = mailStr.split(";");
				for (String mail : mails) {
					this.sendMail(subject, mail, mailCon.toString());
					
					mailCount++;
					if (mailCount % 150 == 0) {
						try {
							Thread.sleep(15 * 60 * 1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			//日常统计
			
			countAction.count();
			return toCountShow();
		}
		
		/*
		 * 获取邮件模版
		 */
			public String getMailTemp(BidContent con, int count) {
				String title, content, pubDate, endDate, area, subCon, key, type, url, host, service, tel, myMail;
				String endDateHtml = "", areaHtml = "";
				title = con.getTitle();
				pubDate = con.getPubDate();
				endDate = con.getEndDate() == null ? "见详情" : con.getEndDate();
				url = con.getSourceUrl();
				key = keyService.getKeyById(con.getKeyWord()).getName();
				area = con.getArea() == null ? "见详情" : con.getArea();
				int typeNum = con.getState();
				switch(typeNum){
				case 1:type = "招标预告";break;
				case 2:type = "招标公告";break;
				case 3:type = "招标变更";break;
				case 4:type = "中标公告";break;
				default :type = "招标公告";break;
				
				}

				if (endDate != null || !"".equals(endDate)) {
					endDateHtml = "<td>截止时间</td><td>" + endDate + "</td>";
				}
				if (area != null || !"".equals(area)) {
					areaHtml = "<td>项目地区</td><td>" + area + "</td>";
				}
				myMail = "<!-- Table markup--><div class='con_div'><div class='con_head'><a href='"
						+ url
						+ "'><b>"
						+ count
						+ ". 【"
						+ type
						+ "】 "
						+ title
						+ "</b></a></div>"
						// + "<div class='con_main'>&nbsp;&nbsp;&nbsp;&nbsp;" + subCon +
						// "...</div>"
						+ "<div class='con_bottom'>"
						+ "<table class='con_tab'><tr>"
						+ "<td>招标产品</td><td>"
						+ key
						+ "</td>"
						+ areaHtml
						+ "<td>发布时间</td><td>"
						+ pubDate
						+ "</td>"
						+ endDateHtml
						+ "</tr>"
						// + "<tr><td>招标机构</td><td>"
						// + host
						// + "</td><td>招标类型</td><td>" + type + "</td><td>联系人</td>"
						// + "<td>" + service + "</td><td> 联系电话</td><td>" + tel
						// +"</td></tr>"
						+ "</table></div></div><!-- Table markup-->";
				return myMail;
			}

			/**
			 * 获取项目路径
			 * pass
			 * @return
			 */
			public String getRealPath(){
				return ServletActionContext.getServletContext().getRealPath("/");
			}
	
	/**
	 * 将筛选后的信息推送给对应 客户 （批量发送邮件）
	 * 
	 * @return
	 */
	public void sendMail(String subject, String mail, String mailDetail) {
		MailSenderInfo mailInfo = MailFactory.getEmail();// 邮件发送对象
		// 邮件标题
		mailInfo.setSubject(subject);
		mailInfo.setContent(mailDetail);
		mailInfo.setToAddress(mail);
		// 发送Text格式邮件
		// SimpleMailSender.sendTextMail(mailInfo);
		// 发送html格式
		SimpleMailSender.sendHtmlMail(mailInfo);
	}
	
	/**
	 * 获取网页源代码 return 网页源代码String
	 */
	public String getWebData(String url) {
		StringBuffer sb = new StringBuffer();
		try {
			// 创建一个url对象来指向 该网站链接 括号里()装载的是该网站链接的路径
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) (u.openConnection()); //
			conn.setDoOutput(false);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("GET");// GET
			conn.connect();

			int code = conn.getResponseCode();
			if (code != HttpURLConnection.HTTP_OK) {
				try {
					throw new Exception("远程没有返回正确结果，返回【" + code + "】。");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 直接把结果打印出来
			InputStream in = conn.getInputStream();
			// 统一使用utf-8 编码模式
			BufferedReader br = new BufferedReader(new InputStreamReader(in,
					"utf-8"));
			String t = null;
			while ((t = br.readLine()) != null) {
				sb.append(t + "\r\n");
			}
			br.close(); // 读取完成后关闭读取器
		} catch (IOException e) {
			// 如果出错 抛出异常
			e.printStackTrace();
		}
		return sb.toString();
	}

	
	public String catchInfo(){
		/**
		 * 抓取当天新增招投标信息,存入数据库
		 */
			int currentPage;
			String strUrl, webData, infoDate, catchFrom, catchTo, parentRegax, title, childUrl, childData;
			boolean flag;
			Matcher m;
			// 获取当前时间
			catchFrom = getParameter("catchFrom");
			catchTo = getParameter("catchTo");
			for (int i = 1; i < 5; i++) {// 招标类别（预告、公告、变更、中标）循环
				flag = true;
				currentPage = 1;
				while (flag) {// 翻页循环
					strUrl = "http://jyxx.csein.cn/Bid/List.shtml?page="
							+ currentPage + "&id=" + i;
					webData = getWebData(strUrl);
					parentRegax = "lass=\"txtL\"><a href=\"(.*?)\" target=\"_blank\" class=\"blFrc\">(.*?)</a></td>[\\s\\S]*?<td>(.*?)</td>";
					m = MyRegex.getMat(webData, parentRegax);
					while (m.find()) {// 页面内列表循环
						infoDate = m.group(3);
						//换格式"yyyy-MM-dd"
						infoDate = DateFormat.regexDate(infoDate);
						if ((infoDate.compareTo(catchFrom) >= 0 && infoDate
										.compareTo(catchTo) <= 0)) {

							// 通过调用group()方法里的索引将url,标题和关键字全部给打印出来
							childUrl = "http://jyxx.csein.cn/Bid/" + m.group(1);
							childUrl = childUrl.replace("0.shtml", "UFKLDSF.shtml");
							childData = getWebData(childUrl);

							title = m.group(2);
							int endOfConId = childData.indexOf("conId-->");
							int startOfConId = childData.indexOf("<!--conId");
							if (startOfConId < 0) {
								continue;
							}
							String conId = childData.substring(startOfConId + 9,
									endOfConId);
							BidContent con = conService.getConById(conId);
							// con.setDateTo(this.setStyle(title));
							con.setSourceUrl(childUrl);
							con.setPubDate(infoDate);
							conService.updateObject(con);
						} else {
							flag = false;
							break;
						}
					}
					if (flag) {
						currentPage++;
					}
				}
			}
		return "success";
	}
	
	
	/**
	 * task查询分页 跳转分页层
	 */
	public String conPaging(){
		BidContent conPage = (BidContent) getSessionAttribute("conPage");
		setAttribute("conList", getPagedCon(conPage) );
		return "pushInfo";
	}
	
	/**
	 * task查询分页 查询数据层
	 */
	public List getPagedCon(BidContent con){
		int pageSize;
		pageSize = null == con.getStr1()? 10 : Integer.parseInt(con.getStr1() );
		// 记录总条数
		int rowNum = conService.getConRow(con);
		Pager pager = PagerHelper.getPager(ServletActionContext.getRequest(),
				rowNum, pageSize);
		setAttribute("currentPage", pager.getCurrentPage());
		setAttribute("totalPages", pager.getTotalPages());
		setAttribute("rowNum", rowNum);
		// 当前页列表
		List conList = conService.getPagedCon(con, pager
				.getStartRow(), pager.getPageSize());
		
		return conList;
	}

	/**
	 * content查询分页  组合条件层
	 */
	public String toPushInfo(){
		//移除查询条件
				removeSessionAttribute("conPage");
				//封装查询对象
				BidContent conPage = new BidContent();
				conPage.setStr1("15");
				List conList = getPagedCon(conPage);
				setSessionAttribute("conPage", conPage);
				setAttribute("conList", conList);
		return "pushInfo";
	}
	
	/*
	 * 组装要post的信息
	 * style,类型（1，预告；2，公告,;4，中标公告）;typeId，关键字Id;number,招标编号;userName,招标业主
	 * ;company,招标公司; contact ----联系人 tel -------联系电话 address ----联系地址 endTime
	 * ----截止日期 areaID -----所属地区(自定义) tradeID ----所属行业(自定义) comeFrom----来源
	 */
	public String pushInfo(){
		BidContent con = new BidContent();
		con.setName("true");
		List conList = conService.getCons(con);
		String url = "http://jyxxmanage.csein.cn/ArticleCollect/CollectAdd.aspx";
		String title = "", comeFrom = "", content = "", keyName = "", keyId = ""
				, userName = "", company = "", contact = "", tel = "", endTime = ""
				, styleNum = "",  proId = "", groupId = "", param = "";
		for (Object obj : conList) {
			BidContent c = (BidContent) obj;
			title = c.getTitle();
			content = "<!--conId" + c.getCid() + "conId-->" + c.getContent();
			BidKeyword key = keyService.getKeyById(c.getKeyWord());
//			comeFrom = c.getBidTask().getWebname();
			keyName = key.getName();
			keyId = key.getStr1();
			styleNum = c.getType() + "";
			groupId = "" + key.getBidGroup().getGid();

			param = "Title=" + title + "&Content=" + content + "&Source="
					+ comeFrom + "&Keywords=" + keyName + "&UserKeyID="
					+ keyId + "&Type=" + styleNum + "&Number=" + ""
					+ "&UserName=" + userName + "&Company=" + company
					+ "&Contact=" + contact + "&Tel=" + tel + "&Address=" + ""
					+ "&EndTime=" + endTime + "&AreaID=" + proId + "&TradeID="
					+ groupId;
			try {
				sendPost(url, param);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return "error";
			}
			
			{//添加推送记录
			BidPlaRecord plaRec = new BidPlaRecord();
			//获取记录元素
			int cid = c.getCid();
			//项目id
			int pid = Integer.parseInt(c.getStr1());
			int kid = Integer.parseInt(c.getKeyWord());
			int conType = c.getType();
			
			//int tid = c.get();任务id
			//平台链接
//			String toUrl = ;
			//生成日期
			String creDate = DateFormat.getString(new SimpleDateFormat("yyyy-MM-dd")
			, DateFormat.getCurDate(0));
			plaRec.setCon(cid);
			plaRec.setPro(pid);
			plaRec.setKeyword(kid);
			plaRec.setConType(conType);
			plaRec.setCreDate(creDate);
			
			bidService.saveObject(plaRec);
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		return "success";
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
	public void sendPost(String url, String param) throws IOException  {
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
	
	/**
	 * 获取信息池中有信息的客户
	 * @return
	 */
	public List getCurCuses() {
		// 获取信息池中有信息的客户
		List curCusList = cusService.getKindCuses(BidCus.class);
		return curCusList;
	}
	
	/**
	 * 审核信息
	 * 
	 * @return
	 */
	public String checkInfo() {
		// 获取信息池中有信息的客户
//		List<BidCustomer> cuses = getCurCuses();

		BidContent con = null;
		//更换 cus
		String cusid = getParameter("cusid");
		if (!MyString.isNullEmpty(cusid)) {
			// 获取我关联的关键字
			List<BidKeyword> myKeys = cusService.getMyKeys(cusid);
			setSessionAttribute("myKeys", myKeys);
			setAttribute("con", myKeys.get(0));
		
		}
		//更换 key
		String kid = getParameter("kid");
		if (!MyString.isNullEmpty(kid)) {
			// 获取关键字下的信息
			List<BidContent> cons = conService.getConOfKey(kid, BidContent.class);
			setSessionAttribute("cons", cons);
			
		}
		
		//更换 con
		String cid = getParameter("cid");
		if (!MyString.isNullEmpty(cid)) {
			con = conService.getConById(cid);
		}
		
		setAttribute("con", con);
		return "";
	}
	
	/**
	 * 刷新检查: true 刷新; false 不刷新
	 * @param kid
	 * @return
	 */
	public boolean needRefresh(String kid) {
		boolean flag = false;
		int count = conService.getConOfKey(kid, BidContent.class).size();
		if (count < 1) {
			flag = true;
		}
		return flag;
	}

	public String cusExpress(){

		//获取curCus
		List<BidCus> cuses = (List) getSessionAttribute("cuses");
		String cusid = getParameter("cusid");
		int index;
		for (BidCus curCus : cuses) {
			String id = curCus.getCusid() + "";
			if(id.equals(cusid)){
				index = cuses.indexOf(curCus);
				setCusInfo(index, curCus);
				break;
			}
		}
		return "cusCheck";
	}

	public String keyExpress(){
		List<BidKeyword> myKeys = (List) getSessionAttribute("myKeys");
		String kid = getParameter("kid");
		int index;
		for (BidKeyword key : myKeys) {
			String id = key.getKid() + "";
			if(id.equals(kid)){
				index = myKeys.indexOf(key);
				SetKeyInfo(index, key);
				break;
			}
		}
		return "cusCheck";
	}
	
	public String keyExpressB(){
		List<BidKeyword> myKeys = (List) getSessionAttribute("myKeys");
		String kid = getParameter("kid");
		int index;
		for (BidKeyword key : myKeys) {
			String id = key.getKid() + "";
			if(id.equals(kid)){
				index = myKeys.indexOf(key);
				SetKeyInfo(index, key);
				break;
			}
		}
		
		return "keyCheck";
	}
	
	/**
	 * Key信息传递
	 * @param index
	 * @param curKey
	 */
	public void SetKeyInfo(int index, BidKeyword curKey){
			
			setSessionAttribute("indexKey", index);
		setSessionAttribute("curKey", curKey);
		// 获取关键字下的信息
		List<BidContent> cons = conService.getConOfKey(curKey.getKid()+"", BidContent.class);

		setSessionAttribute("cons", cons);
		setSessionAttribute("curCon", cons.get(0));
		
	}
	
	/**
	 * 待
	 */
	public String nextKey(){
		List myKeys = (List) getSessionAttribute("myKeys");
		Object obj = getSessionAttribute("curKey");
		int index = myKeys.indexOf(obj) + 1;
		BidKeyword curKey;
		if(index >= 0 && index < myKeys.size()){
			curKey = (BidKeyword) myKeys.get(index);
		SetKeyInfo(index, curKey);
		}
		return "cusCheck";
	}
	
	/**
	 * 待
	 */
	public String preKey(){
		List myKeys = (List) getSessionAttribute("myKeys");
		Object obj = getSessionAttribute("curKey");
		int index = myKeys.indexOf(obj) - 1;
		BidKeyword curKey;
		if(index >= 0 && index < myKeys.size()){
			curKey = (BidKeyword) myKeys.get(index);
			SetKeyInfo(index, curKey);
		}
		return "cusCheck";
	}

	/**
	 * 待
	 */
	public String nextKeyB(){
		List myKeys = (List) getSessionAttribute("myKeys");
		Object obj = getSessionAttribute("curKey");
		int index = myKeys.indexOf(obj) + 1;
		BidKeyword curKey;
		if(index >= 0 && index < myKeys.size()){
			curKey = (BidKeyword) myKeys.get(index);
		SetKeyInfo(index, curKey);
		}
		return "keyCheck";
	}
	
	/**
	 * 待
	 */
	public String preKeyB(){
		List myKeys = (List) getSessionAttribute("myKeys");
		Object obj = getSessionAttribute("curKey");
		int index = myKeys.indexOf(obj) - 1;
		BidKeyword curKey;
		if(index >= 0 && index < myKeys.size()){
			curKey = (BidKeyword) myKeys.get(index);
			SetKeyInfo(index, curKey);
		}
		return "keyCheck";
	}
	
	/**
	 * 上一篇文章
	 * @return
	 */
	public void preArt(){
		List cons = (List) getSessionAttribute("cons");
		Object obj = getSessionAttribute("curCon");
		BidContent curCon;
		int index = cons.indexOf(obj) - 1;
		if(index >= 0 && index < cons.size()){
			curCon = (BidContent) cons.get(index);
			
		}
		else {
			curCon = (BidContent) cons.get(0);
		}
		setSessionAttribute("curCon", curCon);
		//封装Json
		JSONObject json = new JSONObject();  
		json.put("title", curCon.getTitle());  
		json.put("content", curCon.getContent());
		json.put("flag", 1 == curCon.getDelFlag());
		writeJson(json);
	}
	
	/**
	 * 
	 * @param json
	 */
	public void writeJson(JSONObject json){
		PrintWriter pw = null;
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=utf-8");

		try {
			pw = response.getWriter();
			pw.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (pw != null) {
				pw.flush();
				pw.close();
			}
		}
	}
	
	/**
	 * 解除信息与客户关联，
	 */
	public void removeRelate(){
		String cusid = ((BidCus) getSessionAttribute("curCus")).getCusid() + "";
		String cid = getParameter("cid");
		if(MyString.isNullEmpty(cid)){
			//通过cid
			cid = ((BidContent) getSessionAttribute("curCon")).getCid() + "";	
		}
		//通过cid 和 cusid 获取 bidCuscon
		BidCusCon cc = cusService.getCCById(cusid, cid, BidCusCon.class);
		short delFlag = cc.getDelFlag();
		if(delFlag % 2 == 0){//设为有效
			delFlag++;
		}
		else {//设为无效
			delFlag--;
			
		}
		cc.setDelFlag((short) delFlag);
		cusService.updateObject(cc);
	}
	
	/**
	 * 修改信息delFlag
	 */
	public void validInfo(){

		List cons = (List) getSessionAttribute("cons");
		String cid = getParameter("cid");
		BidContent con;
		if(!MyString.isNullEmpty(cid)){
			//通过cid
			con = conService.getConById(cid);
			
		}
		else{
			//通过index
			Object obj = getSessionAttribute("curCon");
			con = (BidContent) obj;
			
		}
		short delFlag = con.getDelFlag();
		if(delFlag % 2 == 0){//设为有效
			delFlag++;
		}
		else {//设为无效
			delFlag--;
			cons.remove(con);
			setSessionAttribute("cons", cons);
			
		}
		con.setDelFlag(delFlag);
		conService.updateObject(con);
		
	}
	
	/**
	 * 下一篇文章
	 * @return
	 */
	public void nextArt(){
		List cons = (List) getSessionAttribute("cons");
		Object obj = getSessionAttribute("curCon");
		BidContent curCon;
		int index = cons.indexOf(obj) + 1;
		if(index >= 0 && index < cons.size()){
			curCon = (BidContent) cons.get(index);
			
		}
		else {
			curCon = (BidContent) cons.get(cons.size() - 1);
		}
		setSessionAttribute("curCon", curCon);
		
		//封装Json
		JSONObject json = new JSONObject();
		json.put("flag", 1 == curCon.getDelFlag());  
		json.put("title", curCon.getTitle());  
		json.put("content", curCon.getContent());
		writeJson(json);
	}
	
	public String preCus(){
		//获取curCus
		List cuses = (List) getSessionAttribute("cuses");
		Object obj = getSessionAttribute("curCus");
		int index = cuses.indexOf(obj) - 1;
		if(index >= 0 && index < cuses.size()){
			BidCus curCus = (BidCus) cuses.get(index);
			setCusInfo(index, curCus);

		}
		return "cusCheck";
	}

	//指定客户，信息审核  ...待
	public String pointCus(){
		String cusid = getParameter("cusid");

		if(MyString.isNullEmpty(cusid)){
			
		}
		return "checkInfo";
	}
	
	public void setCusInfo(int index, BidCus curCus){

		setSessionAttribute("indexCus", index);
	String cusid = curCus.getCusid() + "";
	// 获取我名下,有信息的关键字
	List<BidKeyword> myKeys = keyService.getKindKeys(cusid, BidKeyword.class);
	BidKeyword curKey = myKeys.get(0);
	//
	String kid = myKeys.get(0).getKid() + "";
	// 获取关键字下的信息
	List<BidContent> cons = conService.getConOfKey(kid, BidContent.class);

	BidContent curCon = cons.get(0);
	setSessionAttribute("curKey", curKey);
	setSessionAttribute("curCus", curCus);
	setSessionAttribute("myKeys", myKeys);
	setSessionAttribute("indexKey", 0);
	setSessionAttribute("cons", cons);
	setSessionAttribute("curCon", curCon);
	}
	
	public String nextCus(){
		//获取curCus
		List cuses = (List) getSessionAttribute("cuses");
		Object obj = getSessionAttribute("curCus");
		int index = cuses.indexOf(obj) + 1;
		if(index >= 0 && index < cuses.size()){
			BidCus curCus = (BidCus) cuses.get(index);
			setCusInfo(index, curCus);

		}
		return "cusCheck";
	}

	/**
	 * 审核页面初始化数据
	 * 
	 * @return
	 */
	public String toKeyCheck() {
		// 获取信息池中有信息的关键字
		List<BidKeyword> myKeys = keyService.getKindKeys(BidKeyword.class);
		BidKeyword curKey = myKeys.get(0);
		//
		String kid = myKeys.get(0).getKid() + "";
		// 获取关键字下的信息
		List<BidContent> cons = conService.getConOfKey(kid, BidContent.class);

		BidContent curCon = cons.get(0);
		setSessionAttribute("indexKey", 0);
		setSessionAttribute("curKey", curKey);
		setSessionAttribute("myKeys", myKeys);
		setSessionAttribute("cons", cons);
		setSessionAttribute("curCon", curCon);
		
		return "keyCheck";
	}
	
	/**
	 * 审核页面初始化数据
	 * 
	 * @return
	 */
	public String toCusCheck() {
		// 获取信息池中有信息的客户
		List<BidCus> cuses = getCurCuses();
		BidCus curCus = cuses.get(0);
		//
		String cusid = cuses.get(0).getCusid() + "";
		// 获取我名下,有信息的关键字
		List<BidKeyword> myKeys = keyService.getKindKeys(cusid, BidKeyword.class);
//		List<BidKeyword> myKeys = keyService.getKindKeys(BidKeyword.class);
		BidKeyword curKey = myKeys.get(0);
		//
		String kid = myKeys.get(0).getKid() + "";
		// 获取关键字下的信息
		List<BidContent> cons = conService.getConOfKey(kid, BidContent.class);

		BidContent curCon = cons.get(0);
		setSessionAttribute("indexCus", 0);
		setSessionAttribute("indexKey", 0);
		setSessionAttribute("cuses", cuses);
		setSessionAttribute("curKey", curKey);
		setSessionAttribute("curCus", curCus);
		setSessionAttribute("myKeys", myKeys);
		setSessionAttribute("cons", cons);
		setSessionAttribute("curCon", curCon);
		
		return "cusCheck";
	}

	/**
	 * 跳转邮件推送界面
	 * @return
	 */
	public String toMailInfo(){
		
		return "mailInfo";
	}
	

	/**
	 * 跳转抓取界面
	 * @return
	 */
	public String toCatchInfo(){
		
		return "catchInfo";
	}
	
	// 筛选引擎
	public void autoFilter() {
		// 关键字集合(排重后的)keyList
		List keyList = keyService.getKindKeys(BidKeyword.class);
		// 遍历待筛选信息的关键字
		for (Object object : keyList) {
			BidKeyword key = (BidKeyword) object;
			String keyRegex = key.getKeyRegex();
			String kid = key.getKid() + "";
			List cks = bidService.getCKsById(kid);
			BidContent con = new BidContent();

			// 设置key约束
			con.setKeyWord(kid);
			List infos = conService.getCons(con);
			// 遍历待筛选信息
			for (Object o : infos) {
				// 筛选信息,信息是否有效 true/false,先假设有效(false)
				boolean flag = false;
				BidContent c = (BidContent) o;
				String content = c.getContent();
				// 内容是否符合要求, 仅关键字规则筛选
				flag = RuleSelect.veriRule(content, keyRegex);
				if (!flag) {
					//设置招标类型
					short style = (short) getStyle(c.getTitle());
					c.setType(style);
					// 通过关键字规则的,匹配客户规则

					for (Object obj : cks) {

						BidCusKey ck = (BidCusKey) obj;
						String cusKeyRegex = ck.getRegex();
						BidCus cus = cusService.getCusByCkid(ck.getCkid() + "", BidCus.class);
						String cusRegex = cus.getRegex();
						// 内容是否符合要求, 仅用户规则筛选
						boolean f = RuleSelect.veriRule(content, cusRegex)
								|| RuleSelect.veriRule(content, cusKeyRegex);
						if (!f) {
							
							// 为客户生成信息列表
							BidCusCon cusCon = new BidCusCon();
					    	//0 表示初始状态
							cusCon.setState((short) 0);
					    	//1 表示未删除; 0 表示已删
							cusCon.setDelFlag((short) 1);
							cusCon.setBidCus(cus);
							cusCon.setBidContent(c);
							// 保存cusCon
							cusService.saveObject(cusCon);

						}
					}

				}
				//若信息无效，flag 为 true
				if (flag) {
					c.setDelFlag((short) 0);
				}
				// 更新c
				conService.updateObject(c);
			}
		}

	}

	/**
	 * 导入信息
	 * 
	 * @return
	 */
	public String importInfo() {

		// 获取起始日期
		String minDate = getParameter("minDate");
		// 获取结束日期
		String maxDate = getParameter("maxDate");
		// 清空bid_cus_con
		bidService.emptyTable("bid_cus_con");
		// 清空bid_content
		bidService.emptyTable("bid_content");
		bidService.move2Content(minDate, maxDate);
		
		//自动审核
		autoFilter();
		return toKeyCheck();
	}

	public String startTask() {

		return "importInfo";
	}

	/**
	 * task查询分页 组合条件层
	 */

	/**
	 * task查询分页 跳转分页层
	 */

	/**
	 * task查询分页 查询数据层
	 */

	/**
	 * 添加任务
	 */

	// 添加提取规则

	/**
	 * Filter详情
	 * 
	 * @return
	 */

	/**
	 * 任务详情
	 * 
	 * @return
	 */

	/**
	 * 添加采集任务
	 * 
	 * @return
	 */

	/**
	 * 获取前台参数值1.0
	 * 
	 * @return
	 */
	public String getParameter(String inputName) {
		String parameter;
		parameter = ServletActionContext.getRequest().getParameter(inputName);
		return parameter;
	}

	/**
	 * 设置前台参数1.0 attFront 前台参数名 dataBack 后台传入值
	 * 
	 * @return
	 */
	public void setAttribute(String attFront, Object dataBack) {
		ServletActionContext.getRequest().setAttribute(attFront, dataBack);
	}

	/**
	 * 获取Session参数值1.0
	 * 
	 * @return
	 */
	public Object getSessionAttribute(String inputName) {
		Object object;
		object = ServletActionContext.getRequest().getSession()
				.getAttribute(inputName);
		return object;
	}

	/**
	 * 去掉Session参数值1.0
	 * 
	 * @return
	 */
	public void removeSessionAttribute(String inputName) {
		ServletActionContext.getRequest().getSession()
				.removeAttribute(inputName);
	}

	/**
	 * 设置Session参数1.0 attFront 前台参数名 dataBack 后台传入值
	 * 
	 * @return
	 */
	public void setSessionAttribute(String attFront, Object dataBack) {
		ServletActionContext.getRequest().getSession()
				.setAttribute(attFront, dataBack);
	}

	public BidService getBidService() {
		return bidService;
	}

	public void setBidService(BidService bidService) {
		this.bidService = bidService;
	}

	public ConService getConService() {
		return conService;
	}

	public void setConService(ConService conService) {
		this.conService = conService;
	}

	public KeyService getKeyService() {
		return keyService;
	}

	public void setKeyService(KeyService keyService) {
		this.keyService = keyService;
	}

	public CusService getCusService() {
		return cusService;
	}

	public void setCusService(CusService cusService) {
		this.cusService = cusService;
	}

	public CountAction getCountAction() {
		return countAction;
	}

	public void setCountAction(CountAction countAction) {
		this.countAction = countAction;
	}

}
