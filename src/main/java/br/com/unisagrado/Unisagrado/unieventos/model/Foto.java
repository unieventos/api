package br.com.unisagrado.Unisagrado.unieventos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "foto")
public class Foto {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "foto_id")
	private String id;

	private String path;

	public Foto(String id, String path) {
		this.id = id;
		this.path = path;
	}

	public Foto(String id, String path, Evento evento) {
		this.id = id;
		this.path = path;
	}

	public Foto() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
