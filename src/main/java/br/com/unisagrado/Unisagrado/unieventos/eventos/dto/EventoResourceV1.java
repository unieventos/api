package br.com.unisagrado.Unisagrado.unieventos.eventos.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import br.com.unisagrado.Unisagrado.unieventos.eventos.controller.EventoController;
import br.com.unisagrado.Unisagrado.unieventos.users.controller.UsuarioController;

public class EventoResourceV1 extends RepresentationModel<EventoResourceV1> {
	private final EventoDTOV1 eventoDTOV1;

	public EventoResourceV1(EventoDTOV1 eventoDTOV1) {
		this.eventoDTOV1 = eventoDTOV1;

		add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(EventoController.class).findById(eventoDTOV1.id()))
				.withSelfRel());
	} 

	public EventoDTOV1 getEvento() {
		return eventoDTOV1;
	}
}
