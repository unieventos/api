package br.com.unisagrado.Unisagrado.unieventos.courses.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCourseRecord(@NotBlank String nome) {
	
}
