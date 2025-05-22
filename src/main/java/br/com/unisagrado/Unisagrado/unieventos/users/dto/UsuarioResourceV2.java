package br.com.unisagrado.Unisagrado.unieventos.users.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import br.com.unisagrado.Unisagrado.unieventos.users.controller.UsuarioController;

public class UsuarioResourceV2 extends RepresentationModel<UsuarioResourceV2> {
	private final UsuarioDTOV2 usuarioDTOV2;

	public UsuarioResourceV2(UsuarioDTOV2 usuarioDTOV2) {
		this.usuarioDTOV2 = usuarioDTOV2;

		add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).findUsuarioById(usuarioDTOV2.id()))
				.withSelfRel());
	} 

	public UsuarioDTOV2 getUser() {
		return usuarioDTOV2;
	}
}