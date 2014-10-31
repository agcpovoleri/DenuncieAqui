package com.denuncia.service;

import java.util.List;

import com.denuncia.common.service.GenericService;
import com.denuncia.model.vo.DenunciaVo;

public interface DenunciaService extends GenericService{

	List<DenunciaVo> getAllByCategoria(Long idCategoria);
	

}
