package com.denuncia.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

public interface GenericDAO {

	@Autowired
	public abstract void setSessionFactory(SessionFactory sessionFactory);

	/**
	 * Carrega um registro do banco de dados
	 * @param id chave da tabela
	 * @return registro do banco de dados
	 */
	public abstract <T> T load(Class<T> type, Serializable id);
	
	public abstract <T> T findUnique(DetachedCriteria query);
	
	public abstract <T> T findUnique(String query, String[] params, Object[] values);

	public abstract <T> List<T> findByExample(T t);

	public abstract <T> List<T> findByExample(Class<T> type, T entity,
			Order... orders);

	public abstract <T> T findUniqueByExample(T t);

	public abstract <T> List<T> findAll(Class<T> type);
	
	public abstract <T> List<T> findAllDistinct(Class<T> type);

	public abstract <T> List<T> findAll(Class<T> type, final int first,
			final int max);

	public abstract <T> List<T> findAllOrderBy(Class<T> type, Order... orders);

	public abstract <T> void update(T t);

	public abstract <T> void save(T t);

	public abstract <T> void saveAll(T... entities);

	public abstract <T> void saveOrUpdate(final T entity);

	public abstract <T> void saveOrUpdateAll(Collection<T> entities);

	public abstract <T> void delete(T entity);

	public abstract <T> void delete(T... entities);

	public abstract <T> void deleteById(Class<T> type, Serializable id);

	public abstract <T> void deleteById(Class<T> type, Serializable... ids);

	public abstract <T> void deleteAll(final Class<T> type);

	public abstract <T> int count(final Class<T> type);

	public abstract void flush();

	// SQL Querys
	public abstract <T> List<T> findSqlQuery(final String sqlQuery);

	public abstract void executeDatabaseCommands(final String command);
	
	public abstract <T> void evict(T entity);

}