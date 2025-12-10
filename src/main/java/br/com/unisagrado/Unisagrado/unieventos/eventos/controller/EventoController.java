package br.com.unisagrado.Unisagrado.unieventos.eventos.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.CreateEventRecord;
import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.EventoDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.EventoResourceV1;
import br.com.unisagrado.Unisagrado.unieventos.eventos.usecase.CreateEventUseCase;
import br.com.unisagrado.Unisagrado.unieventos.eventos.usecase.FindEventoUseCase;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/eventos")
public class EventoController {

	private FindEventoUseCase findEventoUseCase;
	private CreateEventUseCase createEventUseCase;

	public EventoController(FindEventoUseCase findEventoUseCase, CreateEventUseCase createEventUseCase) {
		this.findEventoUseCase = findEventoUseCase;
		this.createEventUseCase = createEventUseCase;
	}

	@GetMapping
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Eventos encontrados"),
			@ApiResponse(responseCode = "404", description = "Eventos não encontrados"),
			@ApiResponse(responseCode = "400", description = "Parametro evento inválido") })
	public CollectionModel<EventoResourceV1> findAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "", required = false) String name) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		List<EventoDTOV1> all = findEventoUseCase.findAll(pageable, name);

		List<EventoResourceV1> list = all.stream().map(EventoResourceV1::new).toList();

		return CollectionModel.of(list,
				WebMvcLinkBuilder
						.linkTo(WebMvcLinkBuilder.methodOn(EventoController.class).findAll(page, size, sortBy, name))
						.withSelfRel());
	}

	@GetMapping("/{id}")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Evento encontrado"),
			@ApiResponse(responseCode = "404", description = "Evento não encontrado"),
			@ApiResponse(responseCode = "400", description = "Parametro evento inválido") })
	public ResponseEntity<EventoResourceV1> findById(@PathVariable String id) {
		return new ResponseEntity<EventoResourceV1>(new EventoResourceV1(findEventoUseCase.findById(id)),
				HttpStatus.OK);

	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Evento registrado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Parametro evento inválido") })
	public ResponseEntity<?> register(@RequestPart("dados") CreateEventRecord createEvent,
			@RequestPart("foto") MultipartFile foto) {
		createEventUseCase.execute(createEvent, foto);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
