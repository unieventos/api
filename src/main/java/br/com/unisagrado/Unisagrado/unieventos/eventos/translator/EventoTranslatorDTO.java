package br.com.unisagrado.Unisagrado.unieventos.eventos.translator;

import java.util.List;

import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.CreateEventRecord;
import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.EventoDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;

public interface EventoTranslatorDTO<T> {

	public T toDto(Evento evento);
	public List<T> toDTO(List<Evento> eventos);
}
