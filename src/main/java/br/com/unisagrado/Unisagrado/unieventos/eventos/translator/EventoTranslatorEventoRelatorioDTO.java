package br.com.unisagrado.Unisagrado.unieventos.eventos.translator;

import java.util.List;

import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.EventoRelatorioDTO;
import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;

public class EventoTranslatorEventoRelatorioDTO implements EventoTranslatorDTO<EventoRelatorioDTO>{

	@Override
	public EventoRelatorioDTO toDto(Evento evento) {
		return new EventoRelatorioDTO(evento.getNomeEvento(), evento.getDescricao(), evento.getDateInicio(), evento.getDateFim());
	}

	@Override
	public List<EventoRelatorioDTO> toDTO(List<Evento> eventos) {
		return eventos.stream().map( (e) -> toDto(e)).toList();
	}

	
}
