package br.com.unisagrado.Unisagrado.unieventos.eventos.dto;

import java.time.LocalDate;

import br.com.unisagrado.Unisagrado.unieventos.fotos.model.Foto;

public class EventoRelatorioDTO {

	private String nomeEvento;
	private String descricao;
	private LocalDate dateInicio;
	private LocalDate dateFim;
	private Foto foto;
	
	public EventoRelatorioDTO(String nomeEvento, String descricao, LocalDate dateInicio, LocalDate dateFim) {
		this.nomeEvento = nomeEvento;
		this.descricao = descricao;
		this.dateInicio = dateInicio;
		this.dateFim = dateFim;
	}
	
	public EventoRelatorioDTO(String nomeEvento, String descricao, LocalDate dateInicio, LocalDate dateFim, Foto foto) {
		this.nomeEvento = nomeEvento;
		this.descricao = descricao;
		this.dateInicio = dateInicio;
		this.dateFim = dateFim;
		this.foto = foto;
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

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

}
