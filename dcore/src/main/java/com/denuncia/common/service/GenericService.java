package com.denuncia.common.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


public interface GenericService {

	<T> T load(Class<T> type, Serializable id);
	<T> List<T> findByExample(T entity);
	<T> T findUniqueByExample(T entity);
	<T> List<T> findAll(Class<T> type);
	<T> List<T> findAllDistinct(Class<T> type);
	<T> List<T> findAll(Class<T> type, final int first,final int max);
	<T> void update(T entity);

	<T> void save(T entity);
	<T> void saveAll(T... entities);
	<T> void saveOrUpdateAll(Collection<T> entities);
	<T> void saveOrUpdate(T entity);

	<T> void delete(T entity);
    <T> void delete(T... entities);
    <T> void deleteById(Class<T> type, Serializable id);
    <T> void deleteById(Class<T> type,Serializable... ids);
    <T> void deleteAll(Class<T> type);

    <T> int count(Class<T> type);

    <T> List<T> findSqlQuery(String sqlQuery);
    void executeDatabaseCommands(String command);
    <T> void evict(T entity);


}
