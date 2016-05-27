package com.tao.service;

import java.util.List;

import com.tao.dao.Dao;
import com.tao.entity.BidCus;
import com.tao.entity.BidCusCon;
import com.tao.entity.BidCusKey;

public class CusService {
	Dao dao;

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
	/**
	 * 获取指定BidCusCon
	 * 
	 * @param cid, cusid
	 * @return
	 */
	public BidCusCon getCCById(String cusid, String cid, Class entity) {
		String sql = "select * from bid_cus_con where cusid =" + cusid + " and cid =" + cid;
		return (BidCusCon) dao.getObjectSql(sql, entity);

	}
	/**
	 * 获取指定Cus
	 * 
	 * @param gid
	 * @return
	 */
	public BidCus getCusByCkid(String ckid, Class entity) {
		String sql = "select * from bid_cus as c where c.cusid in (select ck.cusid from bid_cus_key as ck where ck.ckid=" + ckid + ")";
		return (BidCus) dao.getObjectSql(sql, entity);
	}
	
	/**
	 * 获取待筛选信息的关键字列表
	 * 
	 * @return
	 */
	public List getKindCuses(Class entity) {
		String sql = "select * from bid_cus as cus where cus.state=1 and cus.cusid in " +
				"(select distinct cusid from bid_cus_key as ck where ck.kid in " +
				"(select distinct con.key_word from bid_content as con where con.delFlag=1))";
		return dao.getObjListSql(sql, entity);
	}

	/**
	 * 获取指定客户
	 * 
	 * @param gid
	 * @return
	 */
	public BidCusKey getCusKeyById(String ckid) {
		String HQL = "from BidCusKey where ckid =" + ckid;
		return (BidCusKey) dao.getObjectHQL(HQL);

	}
	
	/**
	 * 获取指定CusKey列表
	 */
	public List getCusKeys(String cusid) {

		StringBuffer hql = new StringBuffer("from BidCusKey as ck where 1=1 ");
		// 模糊查询，分组名称
		if (cusid != null && !"".equals(cusid.trim())) {
			hql.append("and ck.bidCus.cusid = '" + cusid + "'");
		}

		return dao.getObjectsHQL(hql.toString());
	}

	/**
	 * 获取指定关键字列表
	 */
	public List getMyKeys(String cusid) {

		StringBuffer hql = new StringBuffer(
				"select ck.bidKeyword from BidCusKey as ck where 1=1 ");
		// 模糊查询，分组名称
		if (cusid != null && !"".equals(cusid.trim())) {
			hql.append("and ck.bidCus.cusid = '" + cusid + "'");
		}

		return dao.getObjectsHQL(hql.toString());
	}

	/**
	 * 获取指定客户
	 * 
	 * @param gid
	 * @return
	 */
	public BidCus getCusById(String cusid) {
		String HQL = "from BidCus where cusid =" + cusid;
		return (BidCus) dao.getObjectHQL(HQL);

	}

	/**
	 * 获取任务条数1.0
	 */
	public Integer getCusRow(BidCus cus) {

		// 取出查询参数
		String name = cus.getName();
		String company = cus.getCompany();
		String area = cus.getArea();

		StringBuffer hql = new StringBuffer("from BidCus where 1=1 ");

		// 模糊查询，任务名称
		if (name != null && !"".equals(name.trim())) {
			hql.append("and name like '%" + name + "%'");
		}
		// 模糊查询，公司名称
		if (name != null && !"".equals(company.trim())) {
			hql.append("and company like '%" + company + "%'");
		}
		// 模糊查询，所属区域
		if (area != null && !"".equals(area.trim())) {
			hql.append("and area like '%" + area + "%'");
		}

		return dao.getObjectsHQL(hql.toString()).size();
	}

	/**
	 * 分页获取客户信息1.0
	 */
	public List getPagedCus(BidCus cus, int startNum, int pageSize) {

		// 取出查询参数
		String name = cus.getName();
		String company = cus.getCompany();
		String area = cus.getArea();

		StringBuffer hql = new StringBuffer("from BidCus where 1=1 ");
		// 模糊查询，任务名称
		if (name != null && !"".equals(name.trim())) {
			hql.append("and name like '%" + name + "%'");
		}
		// 模糊查询，公司名称
		if (name != null && !"".equals(company.trim())) {
			hql.append("and company like '%" + company + "%'");
		}
		// 模糊查询，所属区域
		if (area != null && !"".equals(area.trim())) {
			hql.append("and area like '%" + area + "%'");
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
