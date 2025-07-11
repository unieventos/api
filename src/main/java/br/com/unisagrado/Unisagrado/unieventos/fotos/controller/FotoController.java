package br.com.unisagrado.Unisagrado.unieventos.fotos.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.unisagrado.Unisagrado.unieventos.fotos.dto.CreateFotoRecord;
import br.com.unisagrado.Unisagrado.unieventos.fotos.dto.FotoDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.fotos.dto.FotoResourceV1;
import br.com.unisagrado.Unisagrado.unieventos.fotos.usecase.CreateFotoUseCase;
import br.com.unisagrado.Unisagrado.unieventos.fotos.usecase.FindFotoUseCase;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/fotos")
public class FotoController {
	
	private FindFotoUseCase findFotoUseCase;
	private CreateFotoUseCase createFotoUseCase;
	
	public FotoController(FindFotoUseCase findFotoUseCase, CreateFotoUseCase createFotoUseCase) {
		this.findFotoUseCase = findFotoUseCase;
		this.createFotoUseCase = createFotoUseCase;
	}

	@GetMapping
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Fotos encontradas"),
			@ApiResponse(responseCode = "404", description = "Fotos não encontradas"),
			@ApiResponse(responseCode = "400", description = "Foto evento inválido") })
	public CollectionModel<FotoResourceV1> findAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		List<FotoDTOV1> all = findFotoUseCase.findAll(pageable);

		List<FotoResourceV1> list = all.stream().map(FotoResourceV1::new).toList();

		return CollectionModel.of(list,
				WebMvcLinkBuilder
						.linkTo(WebMvcLinkBuilder.methodOn(FotoController.class).findAll(page, size, sortBy))
						.withSelfRel());
	}
	
	@GetMapping("/{id}")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Evento encontrado"),
			@ApiResponse(responseCode = "404", description = "Evento não encontrado"),
			@ApiResponse(responseCode = "400", description = "Parametro evento inválido") })
	public ResponseEntity<FotoResourceV1> findById(@PathVariable String id) {
		return new ResponseEntity<FotoResourceV1>(new FotoResourceV1(findFotoUseCase.findById(id)),HttpStatus.OK);

	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Evento registrado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Parametro evento inválido") }
	)
	public ResponseEntity<?> register( @RequestPart("foto") MultipartFile foto, @RequestPart("dados") CreateFotoRecord createFoto) {
		createFotoUseCase.execute(createFoto, foto);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
}
