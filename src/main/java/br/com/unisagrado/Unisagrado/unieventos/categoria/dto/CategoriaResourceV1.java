package br.com.unisagrado.Unisagrado.unieventos.categoria.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import br.com.unisagrado.Unisagrado.unieventos.categoria.controller.CategoriaController;

public class CategoriaResourceV1 extends RepresentationModel<CategoriaResourceV1> {
	private final CategoriaDTOV1 categoriaDTOV1;

	public CategoriaResourceV1(CategoriaDTOV1 categoriaDTOV1) {
		this.categoriaDTOV1 = categoriaDTOV1;

		add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(CategoriaController.class).findById(categoriaDTOV1.id()))
				.withSelfRel());
	} 

	public CategoriaDTOV1 getCategoria() {
		return categoriaDTOV1;
	}
}
