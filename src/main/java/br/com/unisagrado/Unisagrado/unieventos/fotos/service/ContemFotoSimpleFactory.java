package br.com.unisagrado.Unisagrado.unieventos.fotos.service;

import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;
import br.com.unisagrado.Unisagrado.unieventos.fotos.exception.TypeFotoNotValid;
import br.com.unisagrado.Unisagrado.unieventos.model.Comentario;
import br.com.unisagrado.Unisagrado.unieventos.model.ContemFoto;

public class ContemFotoSimpleFactory {

	public static ContemFoto create(String tipo) {
		
		if("EVENTO".equalsIgnoreCase(tipo)) {
			return new Evento();
		}
		
		if("COMENTARIO".equalsIgnoreCase(tipo)) {
			return new Comentario();
		}
		
		throw new TypeFotoNotValid();
	}
}
