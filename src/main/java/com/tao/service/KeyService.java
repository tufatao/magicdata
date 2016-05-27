package com.tao.service;

import java.util.List;

import com.tao.dao.Dao;
import com.tao.entity.BidKeyword;
import com.tao.serviceIF.KeyServiceIF;

public class KeyService implements KeyServiceIF {
	Dao dao;

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	
	/**
	 * 获取指定关键字
	 * 
	 * @param gid
	 * @return
	 */
	public List getKeyByPid(String pid, Class entity) {
		String sql = "select * from bid_keyword where state=1 and pid =" + pid;
		return dao.getObjListSql(sql, entity);

	}
	
	/**
	 * 获取待筛选信息的关键字列表
	 * 
	 * @return
	 */
	public List getKindKeys(Class entity) {
		String sql = "select * from bid_keyword as k where k.kid in (select distinct con.key_word from bid_content as con where con.delFlag=1)";
		return dao.getObjListSql(sql, entity);
	}

	/**
	 * 获取待筛选信息的关键字列表
	 * 
	 * @return
	 */
	public List getKindKeys(String cusid, Class entity) {
		String sql = "select * from bid_keyword as k where k.kid in (select ck.kid from bid_cus_key as ck where cusid = '" + cusid +"' and ck.kid in (select distinct con.key_word from bid_content as con where con.delFlag=1))";
		return dao.getObjListSql(sql, entity);
	}
	
	/**
	 * 获取指定关键字
	 * 
	 * @param gid
	 * @return
	 */
	public BidKeyword getKeyById(String kid) {
		String HQL = "from BidKeyword where kid =" + kid;
		return (BidKeyword) dao.getObjectHQL(HQL);

	}

	/**
	 * 获取关键字条数1.0
	 */
	public Integer getKeyRow(BidKeyword key) {

		// 取出查询参数
		String name = key.getName();

		StringBuffer hql = new StringBuffer("from BidKeyword where 1=1 ");
		// 模糊查询，分组名称
		if (name != null && !"".equals(name.trim())) {
			hql.append("and name like '%" + name + "%'");
		}

		return dao.getObjectsHQL(hql.toString()).size();
	}

	/**
	 * 分页获取关键字1.0
	 */
	public List getPagedKey(BidKeyword key, int startNum, int pageSize) {

		// 取出查询参数
		String name = key.getName();

		StringBuffer hql = new StringBuffer("from BidKeyword where 1=1 ");
		// 模糊查询，分组名称
		if (name != null && !"".equals(name.trim())) {
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
	 * 
	 * @param object
	 */
	public void updateObject(Object object) {
		dao.updateObjectHQL(object);

	}

}
