package com.tao.service;

import java.util.List;

import com.tao.dao.Dao;
import com.tao.entity.BidCus;
import com.tao.entity.BidCusKey;
import com.tao.entity.BidUer;

public class UserService {
	Dao dao;

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	/**
	 * 获取密码
	 * @param name
	 * @return
	 */
	public Object getPwByName(String name){
		//String pw;
		String HQL = "select u.pw from BidUer as u where u.name= '" + name + "'";
		return dao.getObjectHQL(HQL);
	}

	/**
	 * 获取指定用户
	 * 
	 * @param uid
	 * @return
	 */
	public BidUer getUserById(String uid) {
		String HQL = "from BidUer where uid =" + uid;
		return (BidUer) dao.getObjectHQL(HQL);

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

		StringBuffer hql = new StringBuffer("from BidCustomer where 1=1 ");
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
