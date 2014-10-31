package com.denuncia.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "denuncia")
@SequenceGenerator( name = "id_denuncia_seq", initialValue = 1, sequenceName = "denuncia_id_seq")
public class Denuncia implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "id_denuncia_seq", strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "data_realizacao")
	private Date dataRealizacao;
	
	@Column(name = "descricao")
	private String descricao;
	
	@ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="idCategoria")
    private Categoria categoria;

	@OneToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="idLocalizacao")
    private LocalizacaoDenuncia localizacao;
	
		
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public LocalizacaoDenuncia getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(LocalizacaoDenuncia localizacao) {
		this.localizacao = localizacao;
	}

	@Override
	public String toString() {
		return "Denuncia [id=" + id + ", dataRealizacao=" + dataRealizacao
				+ ", descricao=" + descricao + ", categoria=" + categoria
				+ ", localizacao=" + localizacao + "]";
	}

}
