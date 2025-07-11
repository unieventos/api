package br.com.unisagrado.Unisagrado.unieventos.categoria.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaDTOV1(@NotBlank String id,@NotBlank String nomeCategoria) {

	
}
