package br.com.unisagrado.Unisagrado.unieventos.eventos.usecase;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.unisagrado.Unisagrado.unieventos.courses.model.Course;
import br.com.unisagrado.Unisagrado.unieventos.courses.service.CourseService;
import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.EventoDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.UpdateEventoDTO;
import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;
import br.com.unisagrado.Unisagrado.unieventos.eventos.service.EventoService;
import br.com.unisagrado.Unisagrado.unieventos.eventos.translator.EventoTranslatorDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.fotos.usecase.UpdateFotosUseCase;
import br.com.unisagrado.Unisagrado.unieventos.categoria.validator.CategoriaValidator;
import br.com.unisagrado.Unisagrado.unieventos.eventocategoria.service.EventoCategoriaService;

@Component
public class UpdateEventUseCase {

	private EventoService eventoService;
	private EventoTranslatorDTOV1 dtov1;
	private UpdateFotosUseCase updateFotosUseCase;
	private CourseService courseService;
	private CategoriaValidator categoriaValidator;
	private EventoCategoriaService eventoCategoriaService;

	public UpdateEventUseCase(EventoService eventoService, EventoTranslatorDTOV1 dtov1,
			UpdateFotosUseCase updateFotosUseCase, CourseService courseService,
			CategoriaValidator categoriaValidator, EventoCategoriaService eventoCategoriaService) {
		this.eventoService = eventoService;
		this.dtov1 = dtov1;
		this.updateFotosUseCase = updateFotosUseCase;
		this.courseService = courseService;
		this.categoriaValidator = categoriaValidator;
		this.eventoCategoriaService = eventoCategoriaService;
	}

	public EventoDTOV1 execute(String eventoId, UpdateEventoDTO updateEvento, Optional<List<MultipartFile>> fotos) {
		categoriaValidator.validateCategoriaExists(updateEvento.getCategorias());
		
		if(fotos.isPresent()) updateFotosUseCase.updateForEventoId(eventoId, fotos.get());
		
		Course course = courseService.findCourseById(updateEvento.getCourseId());
		Evento entity = eventoService.findById(eventoId);
		entity.update(updateEvento.toEntity(), course);
		
		Evento updatedEvento = eventoService.update(entity);
		eventoCategoriaService.updateEventoCategoria(updateEvento.getCategorias(), updatedEvento.getId());
		
		return dtov1.toDto(updatedEvento);
	}

}
