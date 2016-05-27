package com.tao.service;

import java.math.BigInteger;
import java.util.List;

import com.tao.dao.Dao;
import com.tao.entity.BidCus;
import com.tao.entity.BidCusCon;

public class CountService {
	Dao dao;

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
	

	//统计
	public int getData(String sql){
		BigInteger bi = (BigInteger) dao.getObjectSql(sql);
		return bi.intValue();
	}
	
	/**
	 * 获取指定BidCusCon
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
