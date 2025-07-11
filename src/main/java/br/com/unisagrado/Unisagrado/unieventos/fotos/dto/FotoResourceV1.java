package br.com.unisagrado.Unisagrado.unieventos.fotos.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import br.com.unisagrado.Unisagrado.unieventos.eventos.controller.EventoController;
import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.EventoDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.fotos.controller.FotoController;

public class FotoResourceV1 extends RepresentationModel<FotoResourceV1> {
	private final FotoDTOV1 fotoDTOV1;

	public FotoResourceV1(FotoDTOV1 fotoDTOV1) {
		this.fotoDTOV1 = fotoDTOV1;

		add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(FotoController.class).findById(fotoDTOV1.id()))
				.withSelfRel());
	} 

	public FotoDTOV1 getFoto() {
		return fotoDTOV1;
	}
}
