package com.denuncia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "cidade")
@SequenceGenerator( name = "id_cidade_seq", initialValue = 1, allocationSize=1, sequenceName = "cidade_id_seq")
public class Cidade implements Serializable{

	@Id
	@GeneratedValue(generator = "id_cidade_seq", strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "id_estado")
	private Integer idEstado;
	
	@Transient
	private Estado estado;

	public Cidade(Long id, String nome, Integer quantidade,
			Integer idEstado, Estado estado) {
		super();
		this.id = id;
		this.nome = nome;
		this.idEstado = idEstado;
		this.estado = estado;
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

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cidade [id=" + id + ", nome=" + nome + ", " +
				"idEstado=" + idEstado + ", estado="
				+ estado + "]";
	}

	
	
	
}
