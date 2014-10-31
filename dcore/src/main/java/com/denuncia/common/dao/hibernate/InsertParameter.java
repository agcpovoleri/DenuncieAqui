package com.denuncia.common.dao.hibernate;

import java.util.Collection;

public class InsertParameter {
	private String sql;
	private Collection params;
	
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
	
	@Override
	public String toString() {
		return "InsertParameter [ sql=" + sql + ", params=" + params + "]";
	}
	
	
}
