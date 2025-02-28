package br.com.unisagrado.Unisagrado.unieventos.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "evento_categoria")
public class EventoCategoria {

	@EmbeddedId
    private EventoCategoriaId id;
	
	@ManyToOne
    @MapsId("eventoId")
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @ManyToOne
    @MapsId("categoriaId")
    @JoinColumn(name = "categoria_id")
    private Categoria usuario;

    
	public EventoCategoria(EventoCategoriaId id, Evento evento, Categoria usuario) {
		this.id = id;
		this.evento = evento;
		this.usuario = usuario;
	}
	
	public EventoCategoria() {
	}


	public EventoCategoriaId getId() {
		return id;
	}

	public void setId(EventoCategoriaId id) {
		this.id = id;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Categoria getUsuario() {
		return usuario;
	}

	public void setUsuario(Categoria usuario) {
		this.usuario = usuario;
	}
    
}
