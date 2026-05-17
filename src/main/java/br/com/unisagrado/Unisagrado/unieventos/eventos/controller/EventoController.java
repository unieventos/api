package br.com.unisagrado.Unisagrado.unieventos.eventos.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.CreateEventRecord;
import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.EventoDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.EventoResourceV1;
import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.FilterEventoDTO;
import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.UpdateEventoDTO;
import br.com.unisagrado.Unisagrado.unieventos.eventos.usecase.CreateEventUseCase;
import br.com.unisagrado.Unisagrado.unieventos.eventos.usecase.DownloadFotoEvento;
import br.com.unisagrado.Unisagrado.unieventos.eventos.usecase.FindEventoUseCase;
import br.com.unisagrado.Unisagrado.unieventos.eventos.usecase.FindFotosEventoUseCase;
import br.com.unisagrado.Unisagrado.unieventos.eventos.usecase.RelatorioEventoUseCase;
import br.com.unisagrado.Unisagrado.unieventos.eventos.usecase.UpdateEventUseCase;
import br.com.unisagrado.Unisagrado.unieventos.fotos.dto.FotoDTOV1;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/eventos")
public class EventoController {

	private FindEventoUseCase findEventoUseCase;
	private CreateEventUseCase createEventUseCase;
	private UpdateEventUseCase updateEventUseCase;
	private DownloadFotoEvento downloadFotoEvento;
	private FindFotosEventoUseCase findFotosEventoUseCase;
	private RelatorioEventoUseCase relatorioEventoUseCase;

	public EventoController(FindEventoUseCase findEventoUseCase, CreateEventUseCase createEventUseCase,
			UpdateEventUseCase updateEventUseCase, DownloadFotoEvento downloadFotoEvento,
			FindFotosEventoUseCase findFotosEventoUseCase, RelatorioEventoUseCase relatorioEventoUseCase) {
		this.findEventoUseCase = findEventoUseCase;
		this.createEventUseCase = createEventUseCase;
		this.updateEventUseCase = updateEventUseCase;
		this.downloadFotoEvento = downloadFotoEvento;
		this.findFotosEventoUseCase = findFotosEventoUseCase;
		this.relatorioEventoUseCase = relatorioEventoUseCase;
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

	@PostMapping("/search")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Eventos encontrados"),
			@ApiResponse(responseCode = "404", description = "Eventos não encontrados"),
			@ApiResponse(responseCode = "400", description = "Parametro evento inválido") })
	public CollectionModel<EventoResourceV1> searchWithFilter(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy,
			@RequestBody FilterEventoDTO filter) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		List<EventoDTOV1> all = findEventoUseCase.findAll(pageable, filter);

		List<EventoResourceV1> list = all.stream().map(EventoResourceV1::new).toList();

		return CollectionModel.of(list,
				WebMvcLinkBuilder.linkTo(
						WebMvcLinkBuilder.methodOn(EventoController.class).searchWithFilter(page, size, sortBy, filter))
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

	@GetMapping("/{id}/fotos")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Fotos encontradas"),
			@ApiResponse(responseCode = "404", description = "Fotos não encontradas"),
			@ApiResponse(responseCode = "400", description = "Evento id inválido") })
	public CollectionModel<FotoDTOV1> findFotos(@PathVariable String id) {
		List<FotoDTOV1> resource = findFotosEventoUseCase.execute(id);

		return CollectionModel.of(resource, WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(EventoController.class).findFotos(id)).withSelfRel());
	}

	@GetMapping("/{id}/fotos/download")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Fotos encontradas"),
			@ApiResponse(responseCode = "404", description = "Fotos não encontradas"),
			@ApiResponse(responseCode = "400", description = "Evento id inválido") })
	public ResponseEntity<Resource> downloadFoto(@PathVariable String id) {
		Resource resource = downloadFotoEvento.execute(id);
		String contentType = "image/jpeg";

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"foto_evento.jpg\"").body(resource);

	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Evento registrado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Parametro evento inválido") })
	public ResponseEntity<?> register(@RequestPart("dados") CreateEventRecord createEvent,
			@RequestPart(name = "fotos", required = false) Optional<List<MultipartFile>> fotos) {
		createEventUseCase.execute(createEvent, fotos.orElse(Collections.emptyList()));

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@Operation(summary = "Gerar relatório de eventos", description = "Gerar relatório dos id de eventos passados.")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Relatório gerado com sucesso"), })
	@PostMapping(params = "action=relatorio")
	public ResponseEntity<?> activeUser(@RequestBody FilterEventoDTO filter) {
		byte[] pdfBytes = relatorioEventoUseCase.execute(filter);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=eventos.pdf")
				.contentType(MediaType.APPLICATION_PDF).body(pdfBytes);
	}

	@PutMapping(value = "/{id}" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Evento registrado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Parametro evento inválido") })
	public ResponseEntity<EventoResourceV1> update(@PathVariable String id,@RequestPart("dados") UpdateEventoDTO dados,
			@RequestPart(name = "fotos", required = false) Optional<List<MultipartFile>> fotos) {
		return new ResponseEntity<EventoResourceV1>(new EventoResourceV1(updateEventUseCase.execute(id, dados, fotos)),
				HttpStatus.OK);
	}
}
