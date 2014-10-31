package com.denuncia.dao;

import java.util.List;

import com.denuncia.model.vo.CategoriaVo;

public interface CategoriaDao {
	
	List<CategoriaVo> getSubcategoriasByIdcategoria(Long idCategoria);
	
	List<CategoriaVo> getAll();
	
	List<CategoriaVo> getAllByCity(Long idCidade);

	List<CategoriaVo> getAllCategorias();
	
}
