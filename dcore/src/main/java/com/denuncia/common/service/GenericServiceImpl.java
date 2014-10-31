package com.denuncia.common.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.denuncia.common.dao.GenericDAO;

@Repository
@Transactional
public class GenericServiceImpl implements GenericService {
	
	private static Logger LOGGER = Logger.getLogger(GenericServiceImpl.class);

	@Autowired
	@Qualifier("genericDAOImpl")
	private GenericDAO genericDAO;


	public GenericDAO getGenericDao() {
		return genericDAO;
	}

	public void setGenericDao(GenericDAO genericDao) {
		this.genericDAO = genericDao;
	}

	public <T> void save(T entity) {
		try {
			genericDAO.save(entity);
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro ao salvar a entidade "+entity, e);
			throw new RuntimeException(e);
		}
	}

	@Transactional(readOnly=true)
	public <T> T load(Class<T> type, Serializable id) {
		T result=null;
		try {
			result = genericDAO.load(type, id);
		} catch(Exception e) {
			LOGGER.error("Ocorreu um erro ao carregar a entidade "+type.getName()+" e com id "+id, e);
			throw new RuntimeException(e);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public <T> List<T> findByExample(T entity) {
		List<T> result = null;
		try {
			result = genericDAO.findByExample(entity);
		} catch(Exception e) {
			LOGGER.error("Ocorreu um erro ao carregar a entidade "+entity.getClass().getName()+" e com exemplo "+entity, e);
			throw new RuntimeException(e);
		}
		return result;

	}

	@Transactional(readOnly=true)
	public <T> T findUniqueByExample(T entity) {
		T result=null;
		try {
			result = genericDAO.findUniqueByExample(entity);
		} catch(Exception e) {
			LOGGER.error("Ocorreu um erro ao carregar a entidade "+entity.getClass().getName()+" e com exemplo "+entity, e);
			throw new RuntimeException(e);
		}

		return result;
	}

	@Transactional(readOnly=true)
	public <T> List<T> findAll(Class<T> type) {
		List<T> result = null;
		try {
			result = genericDAO.findAll(type);
		} catch(Exception e) {
			LOGGER.error("Ocorreu um erro ao carregar todos os elementos da entidade "+type.getName(), e);
			throw new RuntimeException(e);
		}
		return result;
	}
	
	@Transactional(readOnly=true)
	public <T> List<T> findAllDistinct(Class<T> type) {
		List<T> result = null;
		try {
			result = genericDAO.findAllDistinct(type);
		} catch(Exception e) {
			LOGGER.error("Ocorreu um erro ao carregar todos os elementos da entidade "+type.getName(), e);
			throw new RuntimeException(e);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public <T> List<T> findAll(Class<T> type, int first, int max) {
		List<T> result = null;
		try {
			result = genericDAO.findAll(type,first,max);
		} catch(Exception e) {
			LOGGER.error("Ocorreu um erro ao carregar todos os elementos da entidade "+type.getName(), e);
			throw new RuntimeException(e);
		}
		return result;
	}

	public <T> void update(T entity) {
		try {
			genericDAO.update(entity);
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro ao atualizar a entidade "+entity, e);
			throw new RuntimeException(e);
		}
	}

	public <T> void saveAll(T... entities) {
		try {
			genericDAO.saveAll(entities);
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro ao salvar as entidades "+entities, e);
			throw new RuntimeException(e);
		}

	}

	public <T> void saveOrUpdateAll(Collection<T> entities) {
		try {
			genericDAO.saveOrUpdateAll(entities);
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro ao salvar as entidades "+entities, e);
			throw new RuntimeException(e);
		}

	}

	public <T> void saveOrUpdate(T entity) {
		try {
			genericDAO.saveOrUpdate(entity);
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro ao salvar a entidade "+entity, e);
			throw new RuntimeException(e);
		}

	}

	public <T> void delete(T entity) {
		try {
			genericDAO.delete(entity);
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro ao apagar a entidade "+entity, e);
			throw new RuntimeException(e);
		}

	}

	public <T> void delete(T... entities) {
		try {
			genericDAO.delete(entities);
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro ao apagar as entidades "+entities, e);
			throw new RuntimeException(e);
		}

	}

	public <T> void deleteById(Class<T> type, Serializable id) {
		try {
			genericDAO.deleteById(type, id);
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro ao apagar a entidade "+type.getName()+" com id "+id, e);
			throw new RuntimeException(e);
		}

	}

	public <T> void deleteById(Class<T> type, Serializable... ids) {
		try {
			genericDAO.deleteById(type, ids);
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro ao apagar as entidades "+type.getName()+" com ids "+ids, e);
			throw new RuntimeException(e);
		}


	}

	public <T> void deleteAll(Class<T> type) {
		try {
			genericDAO.deleteAll(type);
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro ao apagar todas as entidades de "+type.getName(), e);
			throw new RuntimeException(e);
		}

	}

	public <T> int count(Class<T> type) {
		int result=0;
		try {
			result = genericDAO.count(type);
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro ao contar registros de "+type.getName(), e);
			throw new RuntimeException(e);
		}
		return result;
	}

	public <T> List<T> findSqlQuery(String sqlQuery) {
		List<T> result = null;
		try {
			result = genericDAO.findSqlQuery(sqlQuery);
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro executar a query "+sqlQuery, e);
			throw new RuntimeException(e);
		}

		return result;
	}

	public void executeDatabaseCommands(String command) {
		try {
			genericDAO.executeDatabaseCommands(command);
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro executar o comando do banco "+command, e);
			throw new RuntimeException(e);
		}

	}

	
	public <T> void evict(T entity) {
		genericDAO.evict(entity);
		
	}
}
