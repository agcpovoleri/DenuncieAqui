package com.denuncia.dao;

import java.util.List;

import com.denuncia.model.vo.DenunciaVo;


public interface DenunciaDao {
	
	List<DenunciaVo> getAllByCategoria(Long idCategoria);
	
}
