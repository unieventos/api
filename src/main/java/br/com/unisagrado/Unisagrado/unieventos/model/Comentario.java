package br.com.unisagrado.Unisagrado.unieventos.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "comentario")
public class Comentario implements ContemFoto{

	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "comentario_id")
	private String id;
	
	@Column(nullable = false)
	private String description;

	public Comentario() {
	}
	
	public Comentario(String id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String nome() {
		return "Comentário";
	}
}
