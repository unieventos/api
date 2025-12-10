package br.com.unisagrado.Unisagrado.unieventos.eventos.usecase;

import br.com.unisagrado.Unisagrado.unieventos.fotos.dto.CreateFotoRecord;
import br.com.unisagrado.Unisagrado.unieventos.fotos.service.FotoService;
import br.com.unisagrado.Unisagrado.unieventos.fotos.usecase.CreateFotoUseCase;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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

	private CreateFotoUseCase createFotoUseCase;

	public CreateEventUseCase(EventoService eventoService, CategoriaValidator categoriaValidator,
			EventoCategoriaService eventoCategoriaService, CreateFotoUseCase createFotoUseCase) {
		this.eventoService = eventoService;
		this.categoriaValidator = categoriaValidator;
		this.eventoCategoriaService = eventoCategoriaService;
		this.createFotoUseCase = createFotoUseCase;
	}

	public void execute(CreateEventRecord createEvent, MultipartFile foto) {
		categoriaValidator.validateCategoriaExists(createEvent.categoria());
		Evento evento = eventoService.createNewEvent(createEvent);
		createFotoUseCase.execute(new CreateFotoRecord("Evento", evento.getId()), foto);
		eventoCategoriaService.createNewEventoCategoria(createEvent.categoria(), evento.getId());
	}
}
