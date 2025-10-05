package br.com.unisagrado.Unisagrado.unieventos.courses.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateCourseRecord(@NotBlank Long id,@NotBlank String nome) {
	
}
