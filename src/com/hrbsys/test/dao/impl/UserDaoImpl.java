package com.hrbsys.test.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hrbsys.test.bean.FKUser;
import com.hrbsys.test.dao.UserDao;

public class UserDaoImpl implements UserDao {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<FKUser> queryAllUsers() {
		return null;
	}

	public boolean saveUser(FKUser user) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(user);
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
}