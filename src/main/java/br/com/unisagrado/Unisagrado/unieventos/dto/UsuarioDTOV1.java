package br.com.unisagrado.Unisagrado.unieventos.dto;

import br.com.unisagrado.Unisagrado.unieventos.model.AcessoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTOV1(@NotBlank String id, @NotBlank String nome,
		@Email(message = "Email should be valid") String email, @NotBlank String curso, AcessoUsuario tipoAcesso) {

}
