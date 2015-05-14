package com.hrbsys.fingerprint.dao;

import com.hrbsys.base.dao.impl.BaseDaoImpl;
import com.hrbsys.bean.UPDATEVERSION;
import com.hrbsys.bean.Xsxx;
import com.hrbsys.bean.ZWLRLB;
import com.hrbsys.tools.UUIDTools;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Li
 */
public class FingerprintDao extends BaseDaoImpl
{
    public List query(String hql, Object[] parameters, int page, int rows)
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
            query.setFirstResult((page - 1) * rows);
            query.setMaxResults(rows);
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

    public int total(String hql, Object[] parameters)
    {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        int total = 0;
        try
        {
            for (int i = 0; i < parameters.length; i++)
            {
                query.setParameter(i, parameters[i]);
            }
            total = Integer.parseInt(query.uniqueResult().toString());
        } catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        } finally
        {
            session.close();
        }
        return total;
    }

    public void saveFingerprintList(String sql, ZWLRLB fingerprintList, String[] studentNumbers)
    {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try
        {
            session.save(fingerprintList);
            Query query = session.createSQLQuery(sql);
            for (String studentNumber : studentNumbers)
            {
                query.setString(0, UUIDTools.getUUID());
                query.setString(1, fingerprintList.getZWLR_ID());
                query.setString(2, studentNumber);
                query.executeUpdate();
            }
            tx.commit();
        } catch (Exception e)
        {
            e.printStackTrace();
            tx.rollback();
            throw e;
        } finally
        {
            session.close();
        }
    }

    public void updateFingerprintList(String sql1, String sql2, String sql3, ZWLRLB fingerprintList, String[] studentNumbers)
    {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try
        {
            Query query = session.createSQLQuery(sql1);
            query.setString(0, fingerprintList.getLBMC());
            query.setString(1, fingerprintList.getZWLR_ID());
            query.executeUpdate();
            query = session.createSQLQuery(sql2);
            query.setString(0, fingerprintList.getZWLR_ID());
            query.executeUpdate();
            query = session.createSQLQuery(sql3);
            for (String studentNumber : studentNumbers)
            {
                query.setString(0, UUIDTools.getUUID());
                query.setString(1, fingerprintList.getZWLR_ID());
                query.setString(2, studentNumber);
                query.executeUpdate();
            }
            tx.commit();
        } catch (Exception e)
        {
            e.printStackTrace();
            tx.rollback();
            throw e;
        } finally
        {
            session.close();
        }
    }

    public void saveFingerprintRegister(List<UPDATEVERSION> updateversionList, List<Xsxx> studentInfoList)
    {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try
        {
            for (UPDATEVERSION updateversion : updateversionList)
            {
                session.saveOrUpdate(updateversion);
            }
            for (Xsxx studentInfo : studentInfoList)
            {
                session.saveOrUpdate(studentInfo);
            }
            tx.commit();
        } catch (Exception e)
        {
            e.printStackTrace();
            tx.rollback();
            throw e;
        } finally
        {
            session.close();
        }
    }

    public void deleteFingerprintRegister(String sql, String parameter1, String parameter2, String[] parameters, List<UPDATEVERSION> list)
    {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try
        {
            Query query = session.createSQLQuery(sql);
            query.setString(0, parameter1);
            query.setString(1, parameter2);
            for (int i = 0; i < parameters.length; i++)
            {
                query.setString(i + 2, parameters[i]);
            }
            query.executeUpdate();
            for(UPDATEVERSION updateversion:list)
            {
                session.saveOrUpdate(updateversion);
            }
            tx.commit();
        } catch (Exception e)
        {
            e.printStackTrace();
            tx.rollback();
            throw e;
        } finally
        {
            session.close();
        }
    }

    public void deleteFingerprintList(String sql1,String sql2,String[] parameters)
    {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try
        {
            Query query = session.createSQLQuery(sql1);
            for (int i = 0; i < parameters.length; i++)
            {
                query.setString(i, parameters[i]);
            }
            query.executeUpdate();
            query = session.createSQLQuery(sql2);
            for (int i = 0; i < parameters.length; i++)
            {
                query.setString(i, parameters[i]);
            }
            query.executeUpdate();
            tx.commit();
        } catch (Exception e)
        {
            e.printStackTrace();
            tx.rollback();
            throw e;
        } finally
        {
            session.close();
        }
    }
}