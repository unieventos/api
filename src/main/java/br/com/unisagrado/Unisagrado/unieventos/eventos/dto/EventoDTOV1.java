package br.com.unisagrado.Unisagrado.unieventos.eventos.dto;

import java.time.LocalDate;
import java.util.Set;

import br.com.unisagrado.Unisagrado.unieventos.users.model.Usuario;
import jakarta.validation.constraints.NotBlank;

public record EventoDTOV1(@NotBlank String id, @NotBlank String nomeEvento, @NotBlank String descricao,
		@NotBlank LocalDate dateInicio, @NotBlank LocalDate dateFim, @NotBlank String usuarioCriador,
		@NotBlank Set<Usuario> usuariosPermissao) {

}
