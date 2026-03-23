package br.com.unisagrado.Unisagrado.unieventos.eventos.translator;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.EventoDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;

@Component
public class EventoTranslatorDTOV1 implements EventoTranslatorDTO<EventoDTOV1>{
	
	@Override
	public EventoDTOV1 toDto(Evento evento) {
		return new EventoDTOV1(evento.getId(), evento.getNomeEvento(), evento.getDescricao(), evento.getDateInicio(),
				evento.getDateFim(), evento.getUsuarioCriador().getId(), evento.getUsuariosPermissao());
	}
	
	@Override
	public List<EventoDTOV1> toDTO(List<Evento> eventos){
		return eventos.stream().map( (e) -> toDto(e)).toList();
	}
	
}
