package br.com.unisagrado.Unisagrado.unieventos.categoria.model;

import java.util.Set;

import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

	@ManyToMany
	@JoinTable(name = "evento_categoria", joinColumns = @JoinColumn(name = "categoria_id"), inverseJoinColumns = @JoinColumn(name = "evento_id"))
	private Set<Evento> eventoCategoria;

	public Categoria(String id, String nomeCategoria) {
		this.id = id;
		this.nomeCategoria = nomeCategoria;
	}
	
	public Categoria(String nomeCategoria) {
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

	public Set<Evento> getEventoCategoria() {
		return eventoCategoria;
	}

	public void setEventoCategoria(Set<Evento> eventoCategoria) {
		this.eventoCategoria = eventoCategoria;
	}

}
