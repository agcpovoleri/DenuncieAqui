package com.denuncia.common.dao.hibernate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;


public class MyJdbcTemplate {
	private static Logger logger = Logger.getLogger(MyJdbcTemplate.class);
	
	private static boolean DEBUG = true;

	private static void logQuery(String sql) {
        if (DEBUG)
                System.out.println("[SQL]: " + sql);
    }
	
	private static void logParams(Collection params) {
        if (DEBUG)
                System.out.println("[PARAMS]: " + params);
    }
	
	private static List<Field> getAllFields(Class<?> type) {
		List<Field> fields = new ArrayList<Field>();
	    for (Field field: type.getDeclaredFields()) {
	        fields.add(field);
	    }

	    if (type.getSuperclass() != null) {
	        fields.addAll(getAllFields(type.getSuperclass()));
	    }

	    return fields;
	}
	
	public static <T> InsertParameter sqlInsert(T domain) {
		String tableName = domain.getClass().getAnnotation(Table.class).name();
		List<String> fields = new ArrayList<String>();
		List<Object> fieldValues =  new ArrayList<Object>();

		for (Field field : getAllFields(domain.getClass())) {
			if (field.isAnnotationPresent(Column.class)) {
				String columnName = field.getAnnotation(Column.class).name();				
				String fieldName = field.getName();
				Object fieldValue = "";
				try {
					if (!field.isAccessible()) {
						field.setAccessible(true);
					}
					fieldValue = field.get(domain);
					field.setAccessible(false);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				
				fields.add(columnName);
				fieldValues.add(fieldValue);
				
			}
			
		}
		String scampos="";
		String svalores="";
		for (int i = 0; i < fields.size(); i++) {
            scampos += (scampos.isEmpty() ? "" : ", ") + fields.get(i);
            svalores += (svalores.isEmpty() ? "" : ", ") + "?";
        }

        String sql = "INSERT INTO "+tableName+" (" + scampos + ") VALUES (" + svalores + ")";
		
        //System.out.println("[SQL]:"+sql);
        
        InsertParameter result = new InsertParameter();
        result.setSql(sql);
        result.setParams(fieldValues);
		
		return result;
	}
	
	
	public static <T> String sqlSelectById(T domain) {
		String tableName = domain.getClass().getAnnotation(Table.class).name();
		List<String> fields = new ArrayList<String>();
		List<Object> fieldValues =  new ArrayList<Object>();

		for (Field field : getAllFields(domain.getClass())) {
			if (field.isAnnotationPresent(Column.class) && field.isAnnotationPresent(Id.class)) {
				String columnName = field.getAnnotation(Column.class).name();				
				Object fieldValue = null;
				try {
					if (!field.isAccessible()) {
						field.setAccessible(true);
					}
					fieldValue = field.get(domain);
					field.setAccessible(false);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				
				fields.add(columnName);
				fieldValues.add(fieldValue);
				
			}
			
		}
		String scampos="";
		//String svalores="";
		for (int i = 0; i < fields.size(); i++) {
            scampos += (scampos.isEmpty() ? "" : "AND ") + fields.get(i) +" = '"+fieldValues.get(i)+"'";
        }

        String sql = "SELECT * FROM "+tableName+" WHERE (1=1) AND " + scampos +";";
		
        //System.out.println("[SQL]:"+sql);
        
        
		
		return sql;
	}
	
	
	 /**
     * Executa consulta SQL com parametros
     * 
     * @param sql
     * @param params
     * @return
     * @throws DAOException
     */
    public static ResultSet executeQuery(Connection connection, String sql, Collection params) throws Exception {
        ResultSet resultSet = null;

        try {
            // Cria Statement 
            PreparedStatement pstmt = connection.prepareStatement(sql);

            // Seta parametros
            setParamsStmt(pstmt, params);

            // Executa query
            resultSet = pstmt.executeQuery(sql);
            logQuery(sql);
        } catch (SQLException e) {
            logger.error(e);
            throw new Exception(e.getMessage());
        } // end try

        // Retorna reultSet
        return resultSet;
    }
    
    
    /**
     * Executa query na Base Dados
     *
     * @param sql - Instrucao select
     * @return ResultSet - ResultSet com os dados da query
     *
     * @throws DAOException
     */
    public static ResultSet executeQuery(Connection connection, String sql) throws Exception {
        return executeQuery(connection, sql, null);
    }

    /**
     * Executa instrucao SQL - (INSERT, UPDATE, DELETE)
     *
     * @param sql
     * @return boolean - Indica se a instrucao foi realizada com sucesso.
     *
     * @throws DAOException
     */
    public static boolean executeSQL(Connection connection, String sql, Collection params) throws Exception {
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            // Seta parametros
            setParamsStmt(pstmt, params);
            logQuery(sql);
            // Executa SQL e retorna
            return pstmt.execute();
        } catch (SQLException e) {
            logger.error(e);
            throw new Exception(e.getMessage());
        } // end try
    }

    
    /**
     * Executa instrucao SQL - (INSERT, UPDATE, DELETE)
     *
     * @param sql
     * @return boolean - Indica se a instrucao foi realizada com sucesso.
     *
     * @throws DAOException
     */
    public static int executeSQLUpdate(Connection connection, String sql, Collection params) throws Exception {
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            // Seta parametros
            setParamsStmt(pstmt, params);
            logQuery(sql);
            logParams(params);
            // Executa SQL e retorna
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new Exception(e.getMessage());
        } // end try
    }
    
    
    /**
     * Executa instrucao SQL - (INSERT, UPDATE, DELETE)
     * 
     * @param sql
     * @return boolean - Indica se a instrucao foi realizada com sucesso.
     * 
     * @throws DAOException
     */
    public static boolean executeSQL(Connection connection, String sql) throws Exception {
        return executeSQL(connection, sql, null);
    }

    /**
     * Setar parametros no objeto Statement
     *
     * @param cStmt     - Statement de atribuicao
     * @param listParam - Colecao de parametros a serem definidos no Statement
     *
     * @throws SQLException
     */
    private static void setParamsStmt(PreparedStatement cStmt, Collection listParam) throws SQLException {
        if ((cStmt != null) && listParam != null) {
            int index = 1;
            Iterator it = listParam.iterator();
            while (it.hasNext()) {
                Object param = it.next();

                // Verifica parmetros
                // Caso o param seja nulo, seta parmetro vazio
                if (param == null) {
                    cStmt.setObject(index, param);
                } // end else if
                else if ((param instanceof String) || (param instanceof Character)) {
                    cStmt.setString(index, param.toString());
                } // end if
                else if (param instanceof Integer) {
                    cStmt.setInt(index, ((Integer) param).intValue());
                } // end else if
                else if (param instanceof Long) {
                    cStmt.setLong(index, ((Long) param).longValue());
                } // end else if
                else if (param instanceof Float) {
                    cStmt.setFloat(index, ((Float) param).floatValue());
                } // end else if
                else if (param instanceof Double) {
                    cStmt.setDouble(index, ((Double) param).doubleValue());
                } // end else if
                else if (param instanceof java.util.Date) {
                    java.util.Date dateUtil = (java.util.Date) param;
                    java.sql.Timestamp dateSQL = new java.sql.Timestamp(dateUtil.getTime());
                    cStmt.setTimestamp(index, dateSQL);
                } // end else if
                else if (param instanceof Boolean) {
                    cStmt.setBoolean(index, ((Boolean)param).booleanValue());
                } // end else if
                else if (param instanceof ByteArrayOutputStream) {
                    byte[] matrixAsBytes = ((ByteArrayOutputStream) param).toByteArray();
                    ByteArrayInputStream bais = new ByteArrayInputStream(matrixAsBytes);
                    cStmt.setBinaryStream(index, bais, matrixAsBytes.length);
                } // end else if
                // caso n�o seja nenhum tipo conhecido
                else {
                	 cStmt.setObject(index, param);
                }
                

                // Incrementa index
                index++;
            } // end while
        } // end if  
    }
    
    
    
}


