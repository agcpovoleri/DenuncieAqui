package com.denuncia.service;

import java.util.List;

import com.denuncia.common.service.GenericService;
import com.denuncia.model.vo.CategoriaVo;

public interface CategoriaService extends GenericService{

	/**
	 * Lista todas as categorias e subcategorias
	 * @return
	 */
	List<CategoriaVo> getAll();
	
	/**
	 * Lista somente as categorias principais
	 * @return
	 */
	List<CategoriaVo> getAllCategorias();
	
	/**
	 * Lista somente as subcategorias de uma categoria passada como parametro
	 * @param idCategoria - id da categoria a ser filtrada
	 * @return
	 */
	List<CategoriaVo> getSubcategoriasByIdcategoria(Long idCategoria);

	List<CategoriaVo> getAllByCity(Long idCidade);

	CategoriaVo getById(Long id);

}
