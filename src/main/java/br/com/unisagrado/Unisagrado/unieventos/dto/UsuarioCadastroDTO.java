package br.com.unisagrado.Unisagrado.unieventos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioCadastroDTO(@NotBlank String curso, @Email(message = "Email should be valid") String email,
		@NotBlank String senha, @NotBlank String nome) {

}
