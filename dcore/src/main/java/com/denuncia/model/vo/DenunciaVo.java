package com.denuncia.model.vo;

import java.util.Date;

public class DenunciaVo {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Date dataRealizacao;
	
	private String descricao;
	
	private Long idcategoria;

	private LocalizacaoDenunciaVo localizacao;

	
	public LocalizacaoDenunciaVo getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(LocalizacaoDenunciaVo localizacao) {
		this.localizacao = localizacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getIdcategoria() {
		return idcategoria;
	}

	public void setIdcategoria(Long idCategoria) {
		this.idcategoria = idCategoria;
	}

	@Override
	public String toString() {
		return "DenunciaVo [id=" + id + ", dataRealizacao=" + dataRealizacao
				+ ", descricao=" + descricao + ", idcategoria=" + idcategoria
				+ ", localizacao=" + localizacao + "]";
	}

	
	
}
