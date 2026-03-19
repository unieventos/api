package br.com.unisagrado.Unisagrado.unieventos.eventos.usecase;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;
import br.com.unisagrado.Unisagrado.unieventos.eventos.service.EventoService;
import br.com.unisagrado.Unisagrado.unieventos.fotos.model.Foto;
import br.com.unisagrado.Unisagrado.unieventos.fotos.service.FotoService;

@Component
public class DownloadFotoEvento {

	private EventoService eventoService;
	private FotoService fotoService;
	
	public DownloadFotoEvento(EventoService eventoService, FotoService fotoService) {
		this.eventoService = eventoService;
		this.fotoService = fotoService;
	}
	
	public Resource execute(String eventoId) {
		Evento evento = eventoService.findById(eventoId);
		Foto fotoByEventoId = fotoService.findFirstFotoByTargetId(evento.getId());
		return fotoService.downloadFoto(fotoByEventoId);
	}
}
