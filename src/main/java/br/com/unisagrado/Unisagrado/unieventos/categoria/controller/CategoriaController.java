package br.com.unisagrado.Unisagrado.unieventos.categoria.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.unisagrado.Unisagrado.unieventos.categoria.dto.CategoriaDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.categoria.dto.CategoriaResourceV1;
import br.com.unisagrado.Unisagrado.unieventos.categoria.dto.CreateCategoriaRecord;
import br.com.unisagrado.Unisagrado.unieventos.categoria.usecase.CreateCategoriaUseCase;
import br.com.unisagrado.Unisagrado.unieventos.categoria.usecase.FindCategoriaUseCase;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	private FindCategoriaUseCase findCategoriaUseCase;
	private CreateCategoriaUseCase createCategoriaUseCase;

	public CategoriaController(FindCategoriaUseCase findCategoriaUseCase,
			CreateCategoriaUseCase createCategoriaUseCase) {
		this.findCategoriaUseCase = findCategoriaUseCase;
		this.createCategoriaUseCase = createCategoriaUseCase;
	}

	@GetMapping
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Categorias encontradas"),
			@ApiResponse(responseCode = "404", description = "Categorias não encontradas"),
			@ApiResponse(responseCode = "400", description = "Parametro categorias inválido") })
	public CollectionModel<CategoriaResourceV1> findAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "", required = false) String name) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		List<CategoriaDTOV1> all = findCategoriaUseCase.findAll(pageable, name);

		List<CategoriaResourceV1> list = all.stream().map(CategoriaResourceV1::new).toList();

		return CollectionModel.of(list,
				WebMvcLinkBuilder
						.linkTo(WebMvcLinkBuilder.methodOn(CategoriaController.class).findAll(page, size, sortBy, name))
						.withSelfRel());
	}
	
	@GetMapping("/{id}")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Categorias encontradas"),
			@ApiResponse(responseCode = "404", description = "Categorias não encontradas"),
			@ApiResponse(responseCode = "400", description = "Parametro categorias inválido") })
	public ResponseEntity<CategoriaResourceV1> findById(@PathVariable String id) {
		return new ResponseEntity<CategoriaResourceV1>(new CategoriaResourceV1(findCategoriaUseCase.findById(id)),HttpStatus.OK);

	}

	@PostMapping
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Categoria registrada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Categoria evento inválido") })
	public ResponseEntity<?> register(@RequestBody @Valid CreateCategoriaRecord createEvent) {
		createCategoriaUseCase.execute(createEvent);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
