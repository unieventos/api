package br.com.unisagrado.Unisagrado.unieventos.eventos.service;

import java.util.List;

import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.FilterEventoDTO;
import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.FilterRelatorioType;
import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;

public interface EventFilterStrategy {
    boolean isApplicable(FilterRelatorioType type);
    List<Evento> filter(FilterEventoDTO filter);
}