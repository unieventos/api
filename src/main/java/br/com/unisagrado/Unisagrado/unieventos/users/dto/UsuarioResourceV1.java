package br.com.unisagrado.Unisagrado.unieventos.users.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import br.com.unisagrado.Unisagrado.unieventos.users.controller.UsuarioController;

public class UsuarioResourceV1 extends RepresentationModel<UsuarioResourceV1> {
	private final UsuarioDTOV1 usuarioDTOV1;

	public UsuarioResourceV1(UsuarioDTOV1 usuarioDTOV1) {
		this.usuarioDTOV1 = usuarioDTOV1;

		add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).findUsuarioById(usuarioDTOV1.id()))
				.withSelfRel());
	} 

	public UsuarioDTOV1 getUser() {
		return usuarioDTOV1;
	}
}