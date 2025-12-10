package br.com.unisagrado.Unisagrado.unieventos.eventos.dto;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;


public record CreateEventRecord(String nomeEvento, String descricao,
	 LocalDate dateInicio, LocalDate dateFim, String categoria, MultipartFile foto) {

}
