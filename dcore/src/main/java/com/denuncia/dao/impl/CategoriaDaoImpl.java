package com.denuncia.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.denuncia.common.dao.GenericDAOImpl;
import com.denuncia.dao.CategoriaDao;
import com.denuncia.model.vo.CategoriaVo;

@Repository
public class CategoriaDaoImpl extends GenericDAOImpl implements CategoriaDao{

	public List<CategoriaVo> getAll() {
		//Long id, String nome, String descricao, boolean anonimato
		String query = 
				"SELECT new com.denuncia.model.vo.CategoriaVo(c.id, c.nome, c.descricao, c.anonimato, c1.id) " +
				"FROM Categoria c  LEFT JOIN c.pai as c1 " +
				"WHERE c.isValid = true";
				
		List<CategoriaVo> categorias = getHibernateTemplate().find(query);
		return categorias;
	}

	public List<CategoriaVo> getAllByCity(Long idCidade) {
		String query = "SELECT new com.marketmobile.model.vo.CategoriaCardapioVo(cc.id, cc.nome, cc.descricao) " +
				"FROM Cardapio c, CategoriaCardapio cc "+
				"WHERE cc.idCardapio = c.id AND c.id = :idCardapio";
		
		List<String>params = new ArrayList<String>();
		List<Object>paramsObj = new ArrayList<Object>();

		params.add("idCidade");
		paramsObj.add(idCidade);

		String[] paramsArray = params.toArray(new String[params.size()]);		
		Object[] paramsObjArray= paramsObj.toArray();
	
		List<CategoriaVo> categoriasCardapio = getHibernateTemplate().findByNamedParam(query, paramsArray, paramsObjArray);
		return categoriasCardapio;
	}

	public List<CategoriaVo> getSubcategoriasByIdcategoria(Long idCategoria) {
		String query = "SELECT new com.denuncia.model.vo.CategoriaVo(c.id, c.nome, c.descricao, c.anonimato, c1.id) " +
				"FROM Categoria c  RIGHT JOIN c.pai as c1 " +
				"WHERE c.isValid = true AND c1.id = :idCategoria";
		
		List<String>params = new ArrayList<String>();
		List<Object>paramsObj = new ArrayList<Object>();

		params.add("idCategoria");
		paramsObj.add(idCategoria);

		String[] paramsArray = params.toArray(new String[params.size()]);		
		Object[] paramsObjArray= paramsObj.toArray();
	
		List<CategoriaVo> categoriasCardapio = getHibernateTemplate().findByNamedParam(query, paramsArray, paramsObjArray);
		return categoriasCardapio;
	}

	public List<CategoriaVo> getAllCategorias() {
		String query = 
				"SELECT new com.denuncia.model.vo.CategoriaVo(c.id, c.nome, c.descricao, c.anonimato, c1.id) " +
				"FROM Categoria c  LEFT JOIN c.pai as c1 " +
				"WHERE c.isValid = true AND c.pai = null";
				
		List<CategoriaVo> categorias = getHibernateTemplate().find(query);
		return categorias;
	}

}

