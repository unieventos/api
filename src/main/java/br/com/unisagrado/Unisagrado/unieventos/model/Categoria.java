package br.com.unisagrado.Unisagrado.unieventos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "categoria_id")
	private String id;
	
	@Column(name = "nome_categoria", nullable = false)
	private String nomeCategoria;

	public Categoria(String id, String nomeCategoria) {
		this.id = id;
		this.nomeCategoria = nomeCategoria;
	}
	
	public Categoria() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	
	
}
