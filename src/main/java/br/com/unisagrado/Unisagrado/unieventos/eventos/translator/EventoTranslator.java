package br.com.unisagrado.Unisagrado.unieventos.eventos.translator;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.CreateEventRecord;
import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.EventoDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;

@Component
public class EventoTranslator {
	
	public static EventoDTOV1 toDto(Evento evento) {
		return new EventoDTOV1(evento.getId(), evento.getNomeEvento(), evento.getDescricao(), evento.getDateInicio(),
				evento.getDateFim(), evento.getUsuarioCriador().getId(), evento.getUsuariosPermissao());
	}
	
	public static List<EventoDTOV1> toDTO(List<Evento> eventos){
		return eventos.stream().map(EventoTranslator::toDto).toList();
	}
	
	public static Evento toEntity(CreateEventRecord record) {
		Evento evento = new Evento();
		evento.setDateFim(record.dateFim());
		evento.setDateInicio(record.dateInicio());
		evento.setDescricao(record.descricao());
		evento.setNomeEvento(record.nomeEvento());
		
		return evento;
	}
	
}
