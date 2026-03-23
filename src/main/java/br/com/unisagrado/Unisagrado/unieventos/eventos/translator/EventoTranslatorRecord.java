package br.com.unisagrado.Unisagrado.unieventos.eventos.translator;

import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;

public interface EventoTranslatorRecord<T> {

	public Evento toEntity(T record);
}
