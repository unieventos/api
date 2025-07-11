package br.com.unisagrado.Unisagrado.unieventos.eventos.model;

public class FotoEventoId {

	public String eventoId;
	public String fotoId;

	public FotoEventoId(String eventoId, String fotoId) {
		super();
		this.eventoId = eventoId;
		this.fotoId = fotoId;
	}

	public FotoEventoId() {
	}

	public String getEventoId() {
		return eventoId;
	}

	public void setEventoId(String eventoId) {
		this.eventoId = eventoId;
	}

	public String getFotoId() {
		return fotoId;
	}

	public void setFotoId(String fotoId) {
		this.fotoId = fotoId;
	}

}
