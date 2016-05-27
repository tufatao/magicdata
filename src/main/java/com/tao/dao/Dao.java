package com.tao.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tao.daoIF.DaoIF;

public class Dao extends HibernateDaoSupport implements DaoIF {
	
	/*
	 * sql专区
	 */
	// 更新操作（增、删、改）
	public void execuSql(final String HQL) {
		super.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createSQLQuery(HQL).executeUpdate();
			}
		});
	}

	// 获取单个对象
	public Object getObjectSql(final String SQL) {
		return super.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.createSQLQuery(SQL).uniqueResult();
					}
				});
	}
	
	// 获取单个对象
	public Object getObjectSql(final String SQL, final Class entity) {
		return super.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.createSQLQuery(SQL).addEntity(entity).uniqueResult();
					}
				});
	}

	// 获取多个对象
	public List getObjListSql(final String SQL, final Class entity) {
		return (List) super.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.createSQLQuery(SQL).addEntity(entity).list();
					}
				});
	}

	/*
	 * hql专区
	 */
	/**
	 * 获取对象集
	 */
	public List<?> getObjectsHQL(final String HQL) {
		return super.getHibernateTemplate().executeFind(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.createQuery(HQL).list();
					}
				});
	}

	/**
	 * 获取分页对象
	 */
	public List<?> getWithPageHQL(final int startNum, final int maxPageSize,
			final String HQL) {
		return super.getHibernateTemplate().executeFind(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.createQuery(HQL)
								.setFirstResult(startNum).setMaxResults(
										maxPageSize).list();
					}
				});
	}

	/**
	 * 获取单个对象
	 */
	public Object getObjectHQL(final String HQL) {
		return super.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.createQuery(HQL).uniqueResult();
					}
				});
	}

	/**
	 * 保存单个对象
	 */
	public void saveObjectHQL(Object object) {
		super.getHibernateTemplate().save(object);
		super.getHibernateTemplate().clear();
		super.getHibernateTemplate().flush();
	}

	/**
	 * 更新单个对象
	 */
	public void updateObjectHQL(Object object) {
		super.getHibernateTemplate().update(object);
		super.getHibernateTemplate().clear();
		super.getHibernateTemplate().flush();
	}

	/**
	 * 删除单个对象
	 */
	public void delObjectHQL(Object object) {
		super.getHibernateTemplate().delete(object);
		super.getHibernateTemplate().clear();
		super.getHibernateTemplate().flush();
	}
}
