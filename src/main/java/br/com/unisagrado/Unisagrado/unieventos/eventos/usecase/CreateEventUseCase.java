package br.com.unisagrado.Unisagrado.unieventos.eventos.usecase;

import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.categoria.validator.CategoriaValidator;
import br.com.unisagrado.Unisagrado.unieventos.eventocategoria.service.EventoCategoriaService;
import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.CreateEventRecord;
import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;
import br.com.unisagrado.Unisagrado.unieventos.eventos.service.EventoService;

@Component
public class CreateEventUseCase {

	private EventoService eventoService;

	private CategoriaValidator categoriaValidator;

	private EventoCategoriaService eventoCategoriaService;

	public CreateEventUseCase(EventoService eventoService, CategoriaValidator categoriaValidator,
			EventoCategoriaService eventoCategoriaService) {
		this.eventoService = eventoService;
		this.categoriaValidator = categoriaValidator;
		this.eventoCategoriaService = eventoCategoriaService;
	}

	public void execute(CreateEventRecord createEvent) {
		categoriaValidator.validateCategoriaExists(createEvent.categoria());
		Evento evento = eventoService.createNewEvent(createEvent);
		eventoCategoriaService.createNewEventoCategoria(createEvent.categoria(), evento.getId());
	}
}
