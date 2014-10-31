package com.denuncia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.denuncia.common.service.GenericServiceImpl;
import com.denuncia.dao.DenunciaDao;
import com.denuncia.model.vo.DenunciaVo;
import com.denuncia.service.DenunciaService;

@Repository
@Transactional
public class DenunciaServiceImpl extends GenericServiceImpl implements DenunciaService{

	@Autowired
	DenunciaDao denunciaDao;

	public List<DenunciaVo> getAllByCategoria(Long idCategoria) {
		
		return denunciaDao.getAllByCategoria(idCategoria);
		
	}

	

}
