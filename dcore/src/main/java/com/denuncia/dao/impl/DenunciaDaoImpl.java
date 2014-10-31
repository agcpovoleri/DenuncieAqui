package com.denuncia.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.denuncia.common.dao.GenericDAOImpl;
import com.denuncia.dao.DenunciaDao;
import com.denuncia.model.vo.CategoriaVo;
import com.denuncia.model.vo.DenunciaVo;

@Repository
public class DenunciaDaoImpl extends GenericDAOImpl implements DenunciaDao{

	public List<DenunciaVo> getAllByCategoria(Long idCategoria) {
		
		//Long id, String descricao, Date date, Categoria categoria
		String query = 
				"SELECT new com.denuncia.model.vo.DenunciaVo(d.id, d.dataRealizacao, d.descricao, c.id) " +
				"FROM Denuncia d LEFT JOIN d.categoria as c " +
				"WHERE c.id = :idCategoria";
		
		List<String>params = new ArrayList<String>();
		List<Object>paramsObj = new ArrayList<Object>();

		params.add("idCategoria");
		paramsObj.add(idCategoria);

		String[] paramsArray = params.toArray(new String[params.size()]);		
		Object[] paramsObjArray= paramsObj.toArray();
		
		List<DenunciaVo> denuncias = getHibernateTemplate().find(query, paramsArray, paramsObjArray);
		return denuncias;
	}

}

