package com.tao.service;

import java.util.List;

import com.tao.dao.Dao;
import com.tao.entity.BidGroup;
import com.tao.entity.BidKeyword;
import com.tao.serviceIF.GroupServiceIF;

public class GroupService implements GroupServiceIF{
	Dao dao;

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	
	/**
	 * 分页获取分组1.0
	 */
	public List getGroups(BidGroup group) {
		
		//取出查询参数
		String name = group.getName();
		
		StringBuffer hql = new StringBuffer("from BidGroup where 1=1 ");
		//模糊查询，分组名称
		if(name != null && !"".equals(name.trim() ) ){
			hql.append("and name like '%" + name + "%'");
		}
		
		return dao.getObjectsHQL(hql.toString());
	}
	
	/**
	 * 获取指定分组
	 * @param gid
	 * @return
	 */
	public BidGroup getGroupById(String gid){
		String HQL = "from BidGroup where gid =" + gid;
		return (BidGroup) dao.getObjectHQL(HQL);
		
	}
	
	/**
	 * 获取关键字条数1.0
	 */
	public Integer getGroupRow(BidGroup group) {
		
		//取出查询参数
		String name = group.getName();
		
		StringBuffer hql = new StringBuffer("from BidGroup where 1=1 ");
		//模糊查询，分组名称
		if(name != null && !"".equals(name.trim() ) ){
			hql.append("and name like '%" + name + "%'");
		}
		
		return dao.getObjectsHQL(hql.toString() ).size();
	}
	
	
	//获取group列表
		public List getGroupList(){
			String HQL = "from BidGroup";
			List groupList  = dao.getObjectsHQL(HQL);
			return groupList;
		}
		
	
	/**
	 * 分页获取分组1.0
	 */
	public List getPagedGroup(BidGroup group, int startNum, int pageSize) {
		
		//取出查询参数
		String name = group.getName();
		
		StringBuffer hql = new StringBuffer("from BidGroup where 1=1 ");
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
