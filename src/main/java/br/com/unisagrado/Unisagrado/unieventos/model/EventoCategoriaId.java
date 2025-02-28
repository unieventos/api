package br.com.unisagrado.Unisagrado.unieventos.model;

public class EventoCategoriaId {

	private String categoriaId;
	private String eventoId;

	public EventoCategoriaId() {
	}

	public EventoCategoriaId(String categoriaId, String eventoId) {
		this.categoriaId = categoriaId;
		this.eventoId = eventoId;
	}


	public String getEventoId() {
		return eventoId;
	}

	public void setEventoId(String eventoId) {
		this.eventoId = eventoId;
	}

	public String getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(String categoriaId) {
		this.categoriaId = categoriaId;
	}
	
}
