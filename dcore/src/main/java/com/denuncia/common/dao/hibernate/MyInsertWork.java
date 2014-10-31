package com.denuncia.common.dao.hibernate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.jdbc.Work;

public class MyInsertWork implements Work {
	private static Logger logger = Logger.getLogger(MyInsertWork.class);
	private String sql = null;
	private Collection params = null;
	
	
	public MyInsertWork(String sql, Collection params) {
		super();		this.sql = sql;
		this.params = params;
	}

	private Integer result = null;
	
	
	public void execute(Connection connection) throws SQLException {

		try {
			result = MyJdbcTemplate.executeSQLUpdate(connection, sql, params);
		} catch (Exception e) {
			logger.error("Erro ao executar insert: "+sql+", params:" + params,e);
			throw new SQLException(e);
		}		
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Collection getParams() {
		return params;
	}

	public void setParams(Collection params) {
		this.params = params;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	
	
}
