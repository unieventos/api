package br.com.unisagrado.Unisagrado.unieventos.eventos.usecase;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.EventoDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;
import br.com.unisagrado.Unisagrado.unieventos.eventos.service.EventoService;
import br.com.unisagrado.Unisagrado.unieventos.eventos.translator.EventoTranslator;

@Component
public class FindEventoUseCase {
	
	private EventoService eventosService;

	public FindEventoUseCase(EventoService eventosService) {
		this.eventosService = eventosService;
	}

	public List<EventoDTOV1> findAll(Pageable pageable, String name) {
		List<Evento> all = eventosService.findAll(pageable, name);
		
		return EventoTranslator.toDTO(all);
	}
	
	public EventoDTOV1 findById(String idEvento) {
		return EventoTranslator.toDto(eventosService.findById(idEvento));
	}

}
