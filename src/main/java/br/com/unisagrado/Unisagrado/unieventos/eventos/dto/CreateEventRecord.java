package br.com.unisagrado.Unisagrado.unieventos.eventos.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateEventRecord(@NotBlank String nomeEvento,@NotBlank String descricao,
		@NotNull LocalDate dateInicio,@NotNull LocalDate dateFim,@NotNull List<String> categorias, @NotNull Long courseId) {

}
