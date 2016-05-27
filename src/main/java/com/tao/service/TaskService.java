package com.tao.service;

import java.util.List;

import com.tao.dao.Dao;
import com.tao.entity.BidContentFilter;
import com.tao.entity.BidTask;
import com.tao.serviceIF.TaskServiceIF;

public class TaskService implements TaskServiceIF{
	Dao dao;

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	/**
	 * 获取指定任务task
	 * @param tid
	 * @return
	 */
	public List getTaskByPid(String pid, Class entity){
		String sql = "select * from bid_task where state=1 and pid =" + pid;
		return dao.getObjListSql(sql, entity);
		
	}
	
	/**
	 * 获取指定任务task
	 * @param tid
	 * @return
	 */
	public BidTask getTaskById(String tid){
		String HQL = "from BidTask where tid =" + tid;
		return (BidTask) dao.getObjectHQL(HQL);
		
	}
	
	/**
	 * 获取指定任务task的name
	 * @param tid
	 * @return
	 */
	public String getNameById(String tid){
		String HQL = "select name from BidTask where tid =" + tid;
		return (String) dao.getObjectHQL(HQL);
		
	}
	/**
	 * 获取指定filter
	 * @param tid
	 * @return
	 */
	public BidContentFilter getFilterById(String fid){
		String HQL = "from BidContentFilter where fid =" + fid;
		return (BidContentFilter) dao.getObjectHQL(HQL);
		
	}
	
	/**
	 * 分页获取任务1.0
	 */
	public List getPagedTask(BidTask task, int startNum, int pageSize) {
		
		//取出查询参数
		String name = task.getName();
		String webname = task.getWebname();
		String charset = task.getCharset();
		
		StringBuffer hql = new StringBuffer("from BidTask where 1=1 ");
		//模糊查询，任务名称
		if(name != null && !"".equals(name.trim() ) ){
			hql.append("and name like '%" + name + "%'");
		}
		//模糊查询，网站名称
		if(webname != null && !"".equals(webname.trim() ) ){
					hql.append("and webname like '%" + webname + "%'");
				}
		//编码方式
		if(charset != null && !"".equals(charset.trim() ) ){
			hql.append("and charset = '" + charset + "'");
		}
		
		return dao.getWithPageHQL(startNum, pageSize, hql.toString());
	}
	
	/**
	 * 获取任务条数1.0
	 */
	public Integer getTaskRow(BidTask task) {
		
		//取出查询参数
		String name = task.getName();
		String webname = task.getWebname();
		String charset = task.getCharset();
		
		StringBuffer hql = new StringBuffer("from BidTask where 1=1 ");
		//模糊查询，任务名称
		if(name != null && !"".equals(name.trim() ) ){
			hql.append("and name like '%" + name + "%'");
		}
		//模糊查询，网站名称
		if(webname != null && !"".equals(webname.trim() ) ){
					hql.append("and webname like '%" + webname + "%'");
				}
		//编码方式
		if(charset != null && !"".equals(charset.trim() ) ){
			hql.append("and charset = '" + charset + "'");
		}
		
		return dao.getObjectsHQL(hql.toString() ).size();
	}
	
	/**
	 * 保存对象1.0
	 */
	public void saveObject(Object object) {
		dao.saveObjectHQL(object);
	}

	/**
	 * 更新对象
	 * @param object
	 */
	public void updateObject(Object object) {
		dao.updateObjectHQL(object);
		
	}

}
