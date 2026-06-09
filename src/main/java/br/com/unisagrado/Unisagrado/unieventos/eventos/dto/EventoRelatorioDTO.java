package br.com.unisagrado.Unisagrado.unieventos.eventos.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.unisagrado.Unisagrado.unieventos.fotos.model.Foto;

public class EventoRelatorioDTO {

	private String nomeEvento;
	private String descricao;
	private LocalDate dateInicio;
	private LocalDate dateFim;
	private List<Foto> fotos;
	private String curso;
	
	public EventoRelatorioDTO(String nomeEvento, String descricao, LocalDate dateInicio, LocalDate dateFim, String curso) {
		this.nomeEvento = nomeEvento;
		this.descricao = descricao;
		this.dateInicio = dateInicio;
		this.dateFim = dateFim;
		this.curso = curso;
	}
	
	public EventoRelatorioDTO(String nomeEvento, String descricao, LocalDate dateInicio, LocalDate dateFim, String curso, List<Foto> fotos) {
		this.nomeEvento = nomeEvento;
		this.descricao = descricao;
		this.dateInicio = dateInicio;
		this.dateFim = dateFim;
		this.fotos = fotos;
		this.curso = curso;
	}
	
	

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
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

	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

}
