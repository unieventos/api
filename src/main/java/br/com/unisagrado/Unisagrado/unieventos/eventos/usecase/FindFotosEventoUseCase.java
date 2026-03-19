package br.com.unisagrado.Unisagrado.unieventos.eventos.usecase;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;
import br.com.unisagrado.Unisagrado.unieventos.eventos.service.EventoService;
import br.com.unisagrado.Unisagrado.unieventos.fotos.dto.FotoDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.fotos.service.FotoService;
import br.com.unisagrado.Unisagrado.unieventos.fotos.translator.FotoTranslator;

@Component
public class FindFotosEventoUseCase {


	private EventoService eventoService;
	private FotoService fotoService;
	private static final Logger logger = LoggerFactory.getLogger(FotoService.class);
	
	public FindFotosEventoUseCase(EventoService eventoService, FotoService fotoService) {
		this.eventoService = eventoService;
		this.fotoService = fotoService;
	}
	
	public List<FotoDTOV1> execute(String eventoId) {
		Evento evento = eventoService.findById(eventoId);
		return FotoTranslator.toDto(fotoService.findFotosByEventoId(evento.getId()));
	}
}
