package com.tao.service;

import java.util.List;

import com.tao.dao.Dao;
import com.tao.entity.BidContent;
import com.tao.serviceIF.ConServiceIF;
import com.tao.util.MyString;

public class ConService implements ConServiceIF {
	Dao dao;

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	public List getMyCons(String cusid, Class entity) {
		String sql = "select * from bid_content as c where c.delFlag=1 and c.cid in (select cid " +
				"from bid_cus_con cs where cs.cusid = " + cusid + " and cs.delFlag = 1)";
		return dao.getObjListSql(sql, entity);
	}
	
/**
 * 
 * @param kid
 * @return
 */
	public List getConOfKey(String kid, Class entity) {
		String sql = "select * from bid_content where delFlag=1 and key_word = " + kid;
		return dao.getObjListSql(sql, entity);
	}
	
	/**
	 * 获取指定信息
	 * 
	 * @param gid
	 * @return
	 */
	public BidContent getConById(String cid) {
		String HQL = "from BidContent where cid =" + cid;
		return (BidContent) dao.getObjectHQL(HQL);

	}

	/**
	 * 获取content 1.0
	 */
	public Integer getConRow(BidContent con) {

		// 取出查询参数
		String webName = con.getStr2();
		String pid = con.getName();
		String kid = con.getKeyWord();
		String content = con.getContent();
		String title = con.getTitle();

		StringBuffer hql = new StringBuffer("from BidContent where 1=1 ");
		// 模糊查询，分组名称
		if (!MyString.isNullEmpty(webName)) {
			hql.append("and str2 like '%" + webName + "%'");
		}
		// 查询，pid
		if (!MyString.isNullEmpty(pid)) {
			hql.append("and str1 ='" + pid + "'");
		}
		// 查询，kid
		if (!MyString.isNullEmpty(kid)) {
			hql.append("and keyWord ='" + kid + "'");
		}
		// 模糊查询，分组名称
		if (!MyString.isNullEmpty(content)) {
			hql.append("and content like '%" + content + "%'");
		}
		// 模糊查询，分组名称
		if (!MyString.isNullEmpty(title)) {
			hql.append("and title like '%" + title + "%'");
		}

		return dao.getObjectsHQL(hql.toString()).size();
	}

	/**
	 * 分页获取content 1.0
	 */
	public List getPagedCon(BidContent con, int startNum, int pageSize) {

		// 取出查询参数
		String webName = con.getStr2();
		String pid = con.getName();
		String kid = con.getKeyWord();
		String content = con.getContent();
		String title = con.getTitle();

		StringBuffer hql = new StringBuffer("from BidContent where delFlag=1 ");
		// 模糊查询，分组名称
		if (!MyString.isNullEmpty(webName)) {
			hql.append("and str2 like '%" + webName + "%'");
		}
		// 查询，pid
		if (!MyString.isNullEmpty(pid)) {
			hql.append("and str1 ='" + pid + "'");
		}
		// 查询，kid
		if (!MyString.isNullEmpty(kid)) {
			hql.append("and keyWord ='" + kid + "'");
		}
		// 模糊查询，分组名称
		if (!MyString.isNullEmpty(content)) {
			hql.append("and content like '%" + content + "%'");
		}
		// 模糊查询，分组名称
		if (!MyString.isNullEmpty(title)) {
			hql.append("and title like '%" + title + "%'");
		}

		return dao.getWithPageHQL(startNum, pageSize, hql.toString());
	}

	/**
	 * 分页获取content 1.0
	 */
	public List getCons(BidContent con) {

		// 取出查询参数
		String title = con.getTitle();
		String name = con.getName();
		String pid = con.getStr1();
		String keyNum = con.getKeyWord();

		StringBuffer hql = new StringBuffer("from BidContent where delFlag=1 ");
		// 模糊查询，分组名称
		if (title != null && !"".equals(title.trim())) {
			hql.append("and title like '%" + title + "%'");
		}
		// 项目约束
		if (pid != null && !"".equals(pid.trim())) {
			hql.append("and str1 ='" + pid + "'");
		}
		// key约束
		if (keyNum != null && !"".equals(keyNum.trim())) {
			hql.append("and keyWord ='" + keyNum + "'");
		}
		return dao.getObjectsHQL(hql.toString());
	}

	/**
	 * 删除对象1.0
	 */
	public void delObject(Object object) {
		dao.delObjectHQL(object);
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
