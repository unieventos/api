package br.com.unisagrado.Unisagrado.unieventos.model;

public class UsuarioEventoPermissaoId {

	private String usuarioId;
	private String eventoId;
	
	public UsuarioEventoPermissaoId() {
	}
	
	public UsuarioEventoPermissaoId(String usuarioId, String eventoId) {
		this.usuarioId = usuarioId;
		this.eventoId = eventoId;
	}


	public String getUsuarioId() {
		return usuarioId;
	}


	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}


	public String getEventoId() {
		return eventoId;
	}


	public void setEventoId(String eventoId) {
		this.eventoId = eventoId;
	}
	
	
}
