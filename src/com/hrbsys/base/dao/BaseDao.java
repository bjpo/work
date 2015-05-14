package com.hrbsys.base.dao;

import java.util.ArrayList;
import java.util.List;

public interface BaseDao {
	public <T> boolean save(T t);

	public <T> boolean delete(T t);
	
	public boolean deleteList(List t);
	
	public <T> boolean delete(Class<T> entityClass, Integer id);

	public <T> boolean update(T t);

	public <T> T get(Class<T> entityClass, Integer id);

	public <T> List<T> findAll(String hql, Class<T> entityClass);

	public <T> List<T> findAll(String hql, Class<T> entityClass, Object param);

	public <T> List<T> findAll(String hql, Class<T> entityClass, Object[] params);

	public <T> List<T> findByPage(final String hql, final Class<T> entityClass,
			final int firstResult, final int maxResult);

	public <T> List<T> findByPage(final String hql, final Class<T> entityClass,
			final Object param, final int firstResult, final int maxResult);

	public <T> List<T> findByPage(final String hql, final Class<T> entityClass,
			final Object[] params, final int firstResult, final int maxResult);
	public <T> List<T> findByPage(String hql, Class<T> entityClass,int firstResult,int maxResult,ArrayList<String> params,String order,String sort);
	public List executeSQL(String sql);
	public void executeInsert_SQL(String sql);
	public boolean executeDoSQL(String sql);
	public <T> List<T> findByLike(String hql, Class<T> entityClass,ArrayList<String> params,String order,String sort);

}
