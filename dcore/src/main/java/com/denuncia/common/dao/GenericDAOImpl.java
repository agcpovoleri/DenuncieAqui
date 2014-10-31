package com.denuncia.common.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.denuncia.common.dao.hibernate.InsertParameter;
import com.denuncia.common.dao.hibernate.MyInsertWork;
import com.denuncia.common.dao.hibernate.MyJdbcTemplate;

@Repository
public class GenericDAOImpl implements GenericDAO  {

	private static Logger LOGGER = Logger.getLogger(GenericDAOImpl.class);

	HibernateTemplate hibernateTemplate;

	@Autowired
	public final void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public final HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}


	public <T> T load(Class<T> type, Serializable id) {
		return (T) getHibernateTemplate().get(type, id);
	}

	public <T> T findUnique(DetachedCriteria query) {
		List<T> registros = getHibernateTemplate().findByCriteria(query);

		if(registros!=null && registros.size()>0){
			return registros.get(0);
		}else{
			return null;
		}
	}

	public <T> T findUnique(String query, String[] params, Object[] values) {
		List<T> registros = getHibernateTemplate().findByNamedParam(query,params,values);

		if(registros!=null && registros.size()>0){
			return registros.get(0);
		}else{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findByExample(T t) {
		return getHibernateTemplate().findByExample(t);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findByExample(Class<T> type, T entity, Order... orders) {
		DetachedCriteria criteria = DetachedCriteria.forClass(type);
		criteria.add(Example.create(entity).enableLike(MatchMode.ANYWHERE).ignoreCase());
		for(Order order : orders) {
			criteria.addOrder(order);
		}
		return getHibernateTemplate().findByCriteria(criteria);

	}

	@SuppressWarnings("unchecked")
	public <T> T findUniqueByExample(T t) {
		List<T> queryResult = getHibernateTemplate().findByExample(t);

		if (queryResult !=null && queryResult.size() > 0) {
			return queryResult.get(0);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findAll(Class<T> type) {
		DetachedCriteria criteria = DetachedCriteria.forClass(type);
		return getHibernateTemplate().findByCriteria(criteria);
	}


	@SuppressWarnings("unchecked")
	public <T> List<T> findAllDistinct(Class<T> type) {
		DetachedCriteria criteria = DetachedCriteria.forClass(type);
		criteria.setProjection(Projections.distinct( Projections.projectionList().add(Projections.id()) ) );
		return getHibernateTemplate().findByCriteria(criteria);
	}	

	@SuppressWarnings("unchecked")
	public <T> List<T> findAll(Class<T> type, final int first,final int max) {
		DetachedCriteria criteria = DetachedCriteria.forClass(type);
		return getHibernateTemplate().findByCriteria(criteria, first,max );
	}


	@SuppressWarnings("unchecked")
	public <T> List<T> findAllOrderBy(Class<T> type, Order... orders) {
		DetachedCriteria criteria = DetachedCriteria.forClass(type);

		for(Order order : orders) {
			criteria.addOrder(order);
		}
		return getHibernateTemplate().findByCriteria(criteria);
	}


	public <T> void update(T t) {
		getHibernateTemplate().update(t);
	}

	public <T> void save(T t) {
		getHibernateTemplate().save(t);
	}

	public <T> void saveAll(T... entities) {
		for(T entity : entities) {
			getHibernateTemplate().save(entity);
		}
	}

	public <T> void saveOrUpdate(final T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	public <T> void saveOrUpdateAll(Collection<T> entities) {
		getHibernateTemplate().saveOrUpdate(entities);
	}

	public <T> void delete(T entity) {
		// remove registro
		getHibernateTemplate().delete(entity);
	}

	public <T> void delete(T... entities) {
		for(T entity : entities) {
			// remove registro
			getHibernateTemplate().delete(entity);
		}
	}

	public  <T> void deleteById(Class<T> type, Serializable id) {
		Object obj = load(type, id);
		getHibernateTemplate().delete(obj);
	}


	public <T> void deleteById(Class<T> type, Serializable... ids) {

		for(Serializable key : ids) {
			deleteById(type, key);
		}
	}



	@SuppressWarnings("unchecked")
	public <T> void deleteAll(final Class<T> type) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String hqlDelete = "delete " + type.getName();
				session.createQuery(hqlDelete)
				.executeUpdate();
				return null;
			}

		});
	}


	public <T> int count(final Class<T> type) {
		List list = getHibernateTemplate().find(
				"select count(*) from " + type.getName() + " x");
		Long count = (Long) list.get(0);
		return count.intValue();
	}

	public void flush() {
		getHibernateTemplate().flush();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findSqlQuery(final String sqlQuery) {
		List<T> result = getHibernateTemplate().executeFind(
				new HibernateCallback<List<T>>() {
					public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
						return	session.createSQLQuery(sqlQuery).list();
					}
				});

		return result;
	}

	public void executeDatabaseCommands(final String command) {

		Boolean result = getHibernateTemplate().execute(
				new HibernateCallback<Boolean>() {
					public Boolean doInHibernate(Session session) throws HibernateException, SQLException {
						session.doWork(new Work() {

							public void execute(Connection connection)
									throws SQLException {
								try {
									MyJdbcTemplate.executeSQL(connection, command);
								} catch (Exception e) {
									throw new SQLException(e);
								}

							}

						});

						return true;
					}
				});
	}

	private <T> int insert(T t) {

		final InsertParameter insertParam = MyJdbcTemplate.sqlInsert(t);

		Integer result = getHibernateTemplate().execute(
				new HibernateCallback<Integer>() {
					public Integer doInHibernate(Session session) throws HibernateException, SQLException {
						MyInsertWork myWork = new MyInsertWork(insertParam.getSql(),insertParam.getParams());
						session.doWork(myWork);
						return myWork.getResult();
					}
				});

		return result;
	}

	public <T> void evict(T entity) {
		getHibernateTemplate().evict(entity);
	}


}