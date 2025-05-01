package br.com.unisagrado.Unisagrado.unieventos.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRecord(@NotBlank String login, @NotBlank String curso, @Email String email, @NotBlank String senha,
                               @NotBlank String nome, @NotBlank String sobrenome, String role) {
	
}
