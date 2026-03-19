package br.com.unisagrado.Unisagrado.unieventos.eventos.usecase;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;
import br.com.unisagrado.Unisagrado.unieventos.eventos.service.EventoService;
import br.com.unisagrado.Unisagrado.unieventos.fotos.model.Foto;
import br.com.unisagrado.Unisagrado.unieventos.fotos.service.FotoService;

@Component
public class FindFotosEventoUseCase {

	private EventoService eventoService;
	private FotoService fotoService;
	
	public FindFotosEventoUseCase(EventoService eventoService, FotoService fotoService) {
		this.eventoService = eventoService;
		this.fotoService = fotoService;
	}
	
	public List<Resource> execute(String eventoId) {
		Evento evento = eventoService.findById(eventoId);
		return fotoService.downloadFotosByEventoId(evento.getId());
	}
}
