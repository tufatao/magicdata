package com.tao.service;

import java.util.List;

import com.tao.dao.Dao;
import com.tao.entity.BidContentFilter;
import com.tao.entity.BidRecordShow;
import com.tao.entity.BidTask;
import com.tao.serviceIF.TaskServiceIF;

public class BidService implements TaskServiceIF {
	Dao dao;

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public void emptyTable(String table){
		String sql = "truncate table " + table;
		dao.execuSql(sql);
	}
	/**
	 * 
	 * @param minDate
	 * @param maxDate
	 */
	public void move2Content(String minDate, String maxDate) {
		dao.execuSql("insert into bid_content(name,title,content,key_word,pub_date,start_date,end_date,source_url,type,state,delFlag,str1,str2) select name,title,content,key_word,pub_date,start_date,end_date,source_url,type,state,delFlag,str1,str2 from bid_total t where t.start_date >= '"
				+ minDate + "' and t.start_date <='" + maxDate + "'");

	}
	
	/**
	 * 通过ckid获取指定Cus
	 * 
	 * @param tid
	 * @return
	 */
	public List getCusByCkid(String ckid) {
		String HQL = "from BidCusKey where ckid =" + ckid;
		return dao.getObjectsHQL(HQL);

	}
	
	/**
	 * 通过kid获取指定BidCusKey集合
	 * 
	 * @param tid
	 * @return
	 */
	public List getCKsById(String kid) {
		String HQL = "from BidCusKey where kid =" + kid;
		return dao.getObjectsHQL(HQL);

	}

	/**
	 * 获取指定任务task
	 * 
	 * @param tid
	 * @return
	 */
	public BidTask getTaskById(String tid) {
		String HQL = "from BidTask where tid =" + tid;
		return (BidTask) dao.getObjectHQL(HQL);

	}

	
	//统计报表;周报;月报;季度;年报
	public List getRecord(int num, int state, Class entity){
		String sql = "select * from bid_record_show where 1=1 and state = " + state + 
		" order by cre_date desc limit 0," + num;
		return dao.getObjListSql(sql, entity);
	}
			
	
	/**
	 * 获取指定任务task的name
	 * 
	 * @param tid
	 * @return
	 */
	public String getNameById(String tid) {
		String HQL = "select name from BidTask where tid =" + tid;
		return (String) dao.getObjectHQL(HQL);

	}

	/**
	 * 获取指定filter
	 * 
	 * @param tid
	 * @return
	 */
	public BidContentFilter getFilterById(String fid) {
		String HQL = "from BidContentFilter where fid =" + fid;
		return (BidContentFilter) dao.getObjectHQL(HQL);

	}

	/**
	 * 分页获取任务1.0
	 */
	public List getPagedTask(BidTask task, int startNum, int pageSize) {

		// 取出查询参数
		String name = task.getName();
		String webname = task.getWebname();
		String charset = task.getCharset();

		StringBuffer hql = new StringBuffer("from BidTask where 1=1 ");
		// 模糊查询，任务名称
		if (name != null && !"".equals(name.trim())) {
			hql.append("and name like '%" + name + "%'");
		}
		// 模糊查询，网站名称
		if (webname != null && !"".equals(webname.trim())) {
			hql.append("and webname like '%" + webname + "%'");
		}
		// 编码方式
		if (charset != null && !"".equals(charset.trim())) {
			hql.append("and charset = '" + charset + "'");
		}

		return dao.getWithPageHQL(startNum, pageSize, hql.toString());
	}

	/**
	 * 获取任务条数1.0
	 */
	public Integer getTaskRow(BidTask task) {

		// 取出查询参数
		String name = task.getName();
		String webname = task.getWebname();
		String charset = task.getCharset();

		StringBuffer hql = new StringBuffer("from BidTask where 1=1 ");
		// 模糊查询，任务名称
		if (name != null && !"".equals(name.trim())) {
			hql.append("and name like '%" + name + "%'");
		}
		// 模糊查询，网站名称
		if (webname != null && !"".equals(webname.trim())) {
			hql.append("and webname like '%" + webname + "%'");
		}
		// 编码方式
		if (charset != null && !"".equals(charset.trim())) {
			hql.append("and charset = '" + charset + "'");
		}

		return dao.getObjectsHQL(hql.toString()).size();
	}

	/**
	 * 保存对象1.0
	 */
	public void saveObject(Object object) {
		dao.saveObjectHQL(object);
	}

	/**
	 * 更新对象
	 * 
	 * @param object
	 */
	public void updateObject(Object object) {
		dao.updateObjectHQL(object);

	}

}
