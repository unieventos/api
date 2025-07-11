package br.com.unisagrado.Unisagrado.unieventos.eventocategoria.service;

import org.springframework.stereotype.Service;

import br.com.unisagrado.Unisagrado.unieventos.categoria.model.Categoria;
import br.com.unisagrado.Unisagrado.unieventos.categoria.service.CategoriaService;
import br.com.unisagrado.Unisagrado.unieventos.eventocategoria.model.EventoCategoria;
import br.com.unisagrado.Unisagrado.unieventos.eventocategoria.model.EventoCategoriaId;
import br.com.unisagrado.Unisagrado.unieventos.eventocategoria.repository.EventoCategoriaRepository;
import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;
import br.com.unisagrado.Unisagrado.unieventos.eventos.service.EventoService;

@Service
public class EventoCategoriaService {
	
	private EventoCategoriaRepository eventoCategoriaRepository;
	private EventoService eventoService;
	private CategoriaService categoriaService;
	
	public EventoCategoriaService(EventoCategoriaRepository eventoCategoriaRepository, EventoService eventoService,
			CategoriaService categoriaService) {
		this.eventoCategoriaRepository = eventoCategoriaRepository;
		this.eventoService = eventoService;
		this.categoriaService = categoriaService;
	}

	public void createNewEventoCategoria(String idCategoria, String idEvento) {
		Evento evento = eventoService.findById(idEvento);
		Categoria categoria = categoriaService.findById(idCategoria);
		eventoCategoriaRepository.save(new EventoCategoria(new EventoCategoriaId(idCategoria, idEvento),evento, categoria));
	}
}
