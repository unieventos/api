package br.com.unisagrado.Unisagrado.unieventos.eventos.translator;

import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.CreateEventRecord;
import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;

public class EventoTranslatorCreateEventRecord implements EventoTranslatorRecord<CreateEventRecord>{

	@Override
	public Evento toEntity(CreateEventRecord record) {
		Evento evento = new Evento();
		evento.setDateFim(record.dateFim());
		evento.setDateInicio(record.dateInicio());
		evento.setDescricao(record.descricao());
		evento.setNomeEvento(record.nomeEvento());
		
		return evento;
	}


	
}
