package br.com.unisagrado.Unisagrado.unieventos.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTOV1(@NotBlank String id, @NotBlank String nome, @NotBlank String sobrenome,
		@Email(message = "Email should be valid") String email, @NotBlank Long cursoId) {

}
