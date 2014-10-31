package com.denuncia.model.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.denuncia.model.Categoria;

public class CategoriaVo {
	
	private Long id;
	
	private String nome;
		
	private String descricao;
	
	private boolean anonimato;
	
    private Long idpai;
 	
	public CategoriaVo() {
		super();
	}

	
	public CategoriaVo(Long id, String nome, String descricao, boolean anonimato, Long idpai) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.anonimato = anonimato;
		this.idpai = idpai;
	}


	public CategoriaVo(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
		this.descricao = categoria.getDescricao();
		this.anonimato = categoria.isAnonimato();
		this.idpai = (categoria.getPai()!=null? categoria.getPai().getId() : null);
		//TODO adicionar dados da categoria pai
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isAnonimato() {
		return anonimato;
	}

	public void setAnonimato(boolean anonimato) {
		this.anonimato = anonimato;
	}


	public Long getIdpai() {
		return idpai;
	}


	public void setIdpai(Long idpai) {
		this.idpai = idpai;
	}


	@Override
	public String toString() {
		return "CategoriaVo [id=" + id + ", nome=" + nome + ", descricao="
				+ descricao + ", anonimato=" + anonimato + ", idpai=" + idpai
				+ "]";
	}

	

	
}
