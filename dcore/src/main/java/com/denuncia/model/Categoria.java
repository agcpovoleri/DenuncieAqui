package com.denuncia.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "categoria")
@SequenceGenerator( name = "id_categoria_seq", initialValue = 1, sequenceName = "categoria_id_seq")
public class Categoria implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "id_categoria_seq", strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "anonimato")
	private boolean anonimato;
	
	@Column(name = "isvalid")
	private boolean isValid;
	
	@Transient
	@ManyToOne(fetch= FetchType.LAZY)
    private Categoria pai;
 
	@OneToMany(mappedBy="pai")
    private Set<Categoria> subordinadas = new HashSet<Categoria>();
	
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

	public Categoria getPai() {
		return pai;
	}

	public void setPai(Categoria pai) {
		this.pai = pai;
	}

	public Set<Categoria> getSubordinadas() {
		return subordinadas;
	}

	public void setSubordinadas(Set<Categoria> subordinadas) {
		this.subordinadas = subordinadas;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + ", descricao="
				+ descricao + ", anonimato=" + anonimato + ", isValid="
				+ isValid + ", pai=" + pai + ", subordinadas=" + subordinadas
				+ "]";
	}

	
	
}
