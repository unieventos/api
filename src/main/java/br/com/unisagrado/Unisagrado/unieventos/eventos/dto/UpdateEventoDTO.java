package br.com.unisagrado.Unisagrado.unieventos.eventos.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.unisagrado.Unisagrado.unieventos.courses.model.Course;
import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateEventoDTO {

	@NotBlank
	String nomeEvento;

	@NotBlank
	String descricao;

	@NotNull
	LocalDate dateInicio;

	@NotNull
	LocalDate dateFim;

	@NotNull
	List<String> categorias;

	@NotNull
	Long courseId;

	public UpdateEventoDTO() {
	}

	public UpdateEventoDTO(@NotBlank String nomeEvento, @NotBlank String descricao, @NotNull LocalDate dateInicio,
			@NotNull LocalDate dateFim, @NotNull List<String> categorias, @NotNull Long courseId) {
		super();
		this.nomeEvento = nomeEvento;
		this.descricao = descricao;
		this.dateInicio = dateInicio;
		this.dateFim = dateFim;
		this.categorias = categorias;
		this.courseId = courseId;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDateInicio() {
		return dateInicio;
	}

	public void setDateInicio(LocalDate dateInicio) {
		this.dateInicio = dateInicio;
	}

	public LocalDate getDateFim() {
		return dateFim;
	}

	public void setDateFim(LocalDate dateFim) {
		this.dateFim = dateFim;
	}

	public List<String> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<String> categorias) {
		this.categorias = categorias;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Evento toEntity() {
		return new Evento(this.getNomeEvento(), this.getDescricao(), this.getDateInicio(), this.getDateFim(),
				null, new Course(this.getCourseId()));
	}

}