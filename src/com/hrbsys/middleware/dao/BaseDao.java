package com.hrbsys.middleware.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Li
 */
public class BaseDao
{
    private SessionFactory sessionFactory;

    public List query(String hql, Object[] parameters)
    {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        List list=new ArrayList();
        try
        {
            for (int i = 0; i < parameters.length; i++)
            {
                query.setParameter(i, parameters[i]);
            }
            list = query.list();
        } catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        } finally
        {
            session.close();
        }
        return list;
    }

    public List query(String hql)
    {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        List list=new ArrayList();
        try
        {
            list = query.list();
        } catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        } finally
        {
            session.close();
        }
        return list;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}