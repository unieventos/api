package br.com.unisagrado.Unisagrado.unieventos.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario_evento_permissao")
public class UsuarioEventoPermissao {

	@EmbeddedId
    private UsuarioEventoPermissaoId id;
	
	@ManyToOne
    @MapsId("eventoId")
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    public UsuarioEventoPermissao() {
	}

	public UsuarioEventoPermissao(UsuarioEventoPermissaoId id, Evento evento, Usuario usuario) {
		this.id = id;
		this.evento = evento;
		this.usuario = usuario;
	}

	public UsuarioEventoPermissaoId getId() {
		return id;
	}

	public void setId(UsuarioEventoPermissaoId id) {
		this.id = id;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
    
}
