package br.com.unisagrado.Unisagrado.unieventos.model;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyDiscriminator;
import org.hibernate.annotations.AnyDiscriminatorValue;
import org.hibernate.annotations.AnyKeyJavaClass;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "foto")
public class Foto {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "foto_id")
	private String id;

	private String path;
	
	@Any
	@AnyDiscriminator(DiscriminatorType.STRING)
	@AnyKeyJavaClass(Long.class)
	@AnyDiscriminatorValue(discriminator = "EVENTO", entity = Evento.class)
	@AnyDiscriminatorValue(discriminator = "COMENTARIO", entity = Evento.class)
	@Column(name = "detail_type")
	@JoinColumn(name = "detail_id")
	private ContemFoto alvo;
	
	public Foto(String id, String path, ContemFoto alvo) {
		this.id = id;
		this.path = path;
		this.alvo = alvo;
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

	public ContemFoto getAlvo() {
		return alvo;
	}

	public void setAlvo(ContemFoto alvo) {
		this.alvo = alvo;
	}

}
