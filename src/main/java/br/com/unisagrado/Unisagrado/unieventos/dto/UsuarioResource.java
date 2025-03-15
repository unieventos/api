package br.com.unisagrado.Unisagrado.unieventos.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import br.com.unisagrado.Unisagrado.unieventos.controller.UsuarioController;

public class UsuarioResource extends RepresentationModel<UsuarioResource> {
	private final UsuarioDTOV1 usuarioDTOV1;

	public UsuarioResource(UsuarioDTOV1 usuarioDTOV1) {
		this.usuarioDTOV1 = usuarioDTOV1;

		add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).findUsuarioById(usuarioDTOV1.id()))
				.withSelfRel());
	} 

	public UsuarioDTOV1 getProduct() {
		return usuarioDTOV1;
	}
}