package br.com.unisagrado.Unisagrado.unieventos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

import br.com.unisagrado.Unisagrado.unieventos.model.Curso;

public record CreateUserRecord(@NotBlank String login, @NotBlank Curso curso, @Email String email, @NotBlank String senha,
                               @NotBlank String nome, @NotBlank String sobrenome, Set<String> roles) {
}
