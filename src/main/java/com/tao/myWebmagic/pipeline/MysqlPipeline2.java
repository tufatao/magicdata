package com.tao.myWebmagic.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import org.apache.log4j.Logger;
import com.tao.dao.Dao;
import com.tao.entity.BidTotal;
import com.tao.util.CharFiltForMysql;

/**
 * Write results in console.<br>
 * Usually used in test.
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
public class MysqlPipeline2 implements Pipeline {

	Dao dao;
    private Logger logger = Logger.getLogger(getClass());
    @Override
    public void process(ResultItems resultItems, Task task) {
    	logger.info("入库分析: " + resultItems.getRequest().getUrl());
        Object con_obj;
        String kid = "", title = "", content = null, area = "", collectTime = ""
        		, pubDate = "", dateTo = "", pageUrl = "", str1 = "";
        //获取MysqlConn
//		title_obj = resultItems.getAll().get("title");
        con_obj = resultItems.getAll().get("content");
		if(null == con_obj) {
			System.out.println((String) con_obj);
			logger.info("content为空，不符合入库要求");
			return;
		}else{
			content = "" + con_obj;
		}
		title = "" + resultItems.getAll().get("title");
		pubDate = "" + resultItems.getAll().get("pubDate");
		collectTime = "" + resultItems.getAll().get("collectTime");
		String pid = "" + resultItems.getAll().get("pid");
		
		title = CharFiltForMysql.filtRiskChar(title).trim();
		content = CharFiltForMysql.filtRiskChar(content).trim();
//		str1 = (String) resultItems.getAll().get("str1");
		
		kid = (String) resultItems.getAll().get("kid");
    	pageUrl = resultItems.getRequest().getUrl();
////    	webName_obj = resultItems.getAll().get("webName");
//
//        	String title = title_obj.toString();
//        	content = CharFiltForMysql.filtRiskChar(content);
    	BidTotal con = new BidTotal();
    	con.setTitle(title);
    	con.setContent(content);
    	con.setKeyWord(kid);
    	con.setPubDate(pubDate);
    	con.setStartDate(collectTime);
    	con.setSourceUrl(pageUrl);
    	con.setStr1(pid);
    	//0 表示初始状态
    	con.setState((short) 0);
    	//1 表示未删除; 0 表示已删
    	con.setDelFlag((short) 1);
    	dao.saveObjectHQL(con);    	
    }
	public Dao getDao() {
		return dao;
	}
	public void setDao(Dao dao) {
		this.dao = dao;
	}

    
}
