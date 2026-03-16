package br.com.unisagrado.Unisagrado.unieventos.eventos.dto;

import java.time.LocalDate;
import java.util.List;

public record CreateEventRecord(String nomeEvento, String descricao,
	 LocalDate dateInicio, LocalDate dateFim, List<String> categorias) {

}
