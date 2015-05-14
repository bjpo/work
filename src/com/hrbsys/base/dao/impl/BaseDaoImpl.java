package com.hrbsys.base.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.MESSAGE;
import com.hrbsys.kechengb.service.impl.KECHENGBServiceImpl;
import com.hrbsys.tools.TeamPrint;

public class BaseDaoImpl implements BaseDao {

	protected SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public <T> boolean save(T t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(t);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (e != null) {
				tx.rollback();
			}
		}
		return false;
	}

	@Override
	public <T> boolean delete(T t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(t);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (e != null) {
				tx.rollback();
			}
		}
		return false;
	}

	public boolean deleteList(List t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			for (int i = 0; i < t.size(); i++) {
				session.delete(t.get(i));
				session.flush();
				session.clear();
			}
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (e != null) {
				tx.rollback();
			}
		}
		return false;
	}

	@Override
	public <T> boolean delete(Class<T> entityClass, Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(get(entityClass, id));
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (e != null) {
				tx.rollback();
			}
		}
		return false;
	}

	@Override
	public <T> boolean update(T t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(t);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (e != null) {
				tx.rollback();
			}
		}
		return false;
	}

	@Override
	public <T> T get(Class<T> entityClass, Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		T t = (T) session.get(entityClass, id);
		tx.commit();
		return t;
	}

	@Override
	public <T> List<T> findAll(String hql, Class<T> entityClass) {
		return findAll(hql, entityClass, new Object[]{});
	}

	@Override
	public <T> List<T> findAll(String hql, Class<T> entityClass, Object param) {
		return findAll(hql, entityClass, new Object[]{param});
	}

	@Override
	public <T> List<T> findAll(String hql, Class<T> entityClass, Object[] params) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<T> all = new ArrayList<T>();
		try {
			Query query = session.createQuery(hql);
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
			all = (List<T>) query.list();
			tx.commit();

		} catch (Exception e) {
			System.out.println("报错时baseDao中打印的Sql是：："+hql);
			e.printStackTrace();
			tx.commit();
		}

		return all;
	}

	@Override
	public <T> List<T> findByPage(final String hql, Class<T> entityClass,
			final int firstResult, final int maxResult) {
		return findByPage(hql, entityClass, new Object[]{}, firstResult,
				maxResult);
	}

	@Override
	public <T> List<T> findByPage(final String hql, Class<T> entityClass,
			final Object param, final int firstResult, final int maxResult) {
		return findByPage(hql, entityClass, new Object[]{param}, firstResult,
				maxResult);
	}

	@Override
	public <T> List<T> findByPage(final String hql, Class<T> entityClass,
			final Object[] params, final int firstResult, final int maxResult) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		List<T> all = (List<T>) query.list();
		tx.commit();
		return all;
	}

	// 分页加排序查找：自主封装
	public <T> List<T> findByPage(String hql, Class<T> entityClass,
			int firstResult, int maxResult, ArrayList<String> params,
			String order, String sort) {
		for (String param : params) {
			if ((null != param) && (!"".equals(param))) {
				hql += param;
			}
		}
		if (null != sort && !"".equals(sort)) {
			hql += " order by " + sort + " " + order;
		}
		System.out.println("baseDao中的hql：：：：："+hql);
		return findByPage(hql, entityClass, new Object[]{}, firstResult,
				maxResult);
	}

	// 分页加排序查找：自主封装
	public <T> List<T> findByLike(String hql, Class<T> entityClass,
			ArrayList<String> params, String order, String sort) {
		for (String param : params) {
			if ((null != param) && (!"".equals(param))) {
				hql += param;
			}
		}
		if (null != sort && !"".equals(sort)) {
			hql += " order by " + sort + " " + order;
		}
		TeamPrint.println(BaseDaoImpl.class, "findByLike", "SQL语句", hql
				+ params);
		return findAll(hql, entityClass);
	}

	// 执行SQL语句
	public List executeSQL(String sql) {
		List all = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		all = session.createSQLQuery(sql).list();
		tx.commit();
		return all;
	}

	// 执行SQL插入语句
	public void executeInsert_SQL(String sql) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.createSQLQuery(sql).executeUpdate();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
	}

	// 执行SQL语句带回滚
	public boolean executeDoSQL(String sql) {
		boolean flag = false;
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.createSQLQuery(sql).executeUpdate();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
		return flag;
	}
}
