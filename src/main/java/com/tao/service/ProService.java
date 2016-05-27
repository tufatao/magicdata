package com.tao.service;

import java.util.List;

import com.tao.dao.Dao;
import com.tao.entity.BidProject;
import com.tao.serviceIF.ProServiceIF;

public class ProService implements ProServiceIF{
	Dao dao;

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	/**
	 * 获取指定分组
	 * @param gid
	 * @return
	 */
	public BidProject getProById(String pid){
		String HQL = "from BidProject where pid =" + pid;
		return (BidProject) dao.getObjectHQL(HQL);
		
	}
	
	/**
	 * 获取关键字条数1.0
	 */
	public Integer getProRow(BidProject pro) {
		
		//取出查询参数
		String name = pro.getName();
		
		StringBuffer hql = new StringBuffer("from BidProject where 1=1 ");
		//模糊查询，分组名称
		if(name != null && !"".equals(name.trim() ) ){
			hql.append("and name like '%" + name + "%'");
		}
		
		return dao.getObjectsHQL(hql.toString() ).size();
	}
	
	
	//获取group列表
		public List getProList(){
			String HQL = "from BidProject";
			List proList  = dao.getObjectsHQL(HQL);
			return proList;
		}
		
	
	/**
	 * 分页获取分组1.0
	 */
	public List getPagedPro(BidProject pro, int startNum, int pageSize) {
		
		//取出查询参数
		String name = pro.getName();
		
		StringBuffer hql = new StringBuffer("from BidProject where 1=1 ");
		//模糊查询，分组名称
		if(name != null && !"".equals(name.trim() ) ){
			hql.append("and name like '%" + name + "%'");
		}
		
		return dao.getWithPageHQL(startNum, pageSize, hql.toString());
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
