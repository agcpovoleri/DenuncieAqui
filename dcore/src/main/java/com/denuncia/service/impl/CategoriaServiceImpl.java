package com.denuncia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.denuncia.common.service.GenericServiceImpl;
import com.denuncia.dao.CategoriaDao;
import com.denuncia.model.Categoria;
import com.denuncia.model.vo.CategoriaVo;
import com.denuncia.service.CategoriaService;

@Repository
@Transactional
public class CategoriaServiceImpl extends GenericServiceImpl implements CategoriaService{

	@Autowired
	CategoriaDao categoriaDao;

	public List<CategoriaVo> getAll() {
		return categoriaDao.getAll();
	}

	public List<CategoriaVo> getAllByCity(Long idCidade) {
		return categoriaDao.getAllByCity(idCidade);
	}

	public CategoriaVo getById(Long id) {
		Categoria categoria = load(Categoria.class, id);
		CategoriaVo catVo = new CategoriaVo(categoria);
		return catVo;
	}

	public List<CategoriaVo> getSubcategoriasByIdcategoria(Long idCategoria) {
		return categoriaDao.getSubcategoriasByIdcategoria(idCategoria);
	}

	public List<CategoriaVo> getAllCategorias() {
		return categoriaDao.getAllCategorias();
	}

}
