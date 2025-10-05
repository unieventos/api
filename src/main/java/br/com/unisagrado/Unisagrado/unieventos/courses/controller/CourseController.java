package br.com.unisagrado.Unisagrado.unieventos.courses.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.unisagrado.Unisagrado.unieventos.courses.dto.CourseDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.courses.dto.CourseResourceV1;
import br.com.unisagrado.Unisagrado.unieventos.courses.dto.CreateCourseRecord;
import br.com.unisagrado.Unisagrado.unieventos.courses.dto.UpdateCourseRecord;
import br.com.unisagrado.Unisagrado.unieventos.courses.usecase.CreateCourseUseCase;
import br.com.unisagrado.Unisagrado.unieventos.courses.usecase.DeleteCourseUseCase;
import br.com.unisagrado.Unisagrado.unieventos.courses.usecase.FindCourseUseCase;
import br.com.unisagrado.Unisagrado.unieventos.courses.usecase.UpdateCourseUseCase;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/courses")
public class CourseController {

	private CreateCourseUseCase createCourseUseCase;
	private FindCourseUseCase findCourseUseCase;
	private DeleteCourseUseCase deleteCourseUseCase;
	private UpdateCourseUseCase updateCourseUseCase;
	
	public CourseController(CreateCourseUseCase createCourseUseCase, FindCourseUseCase findCourseUseCase,
			DeleteCourseUseCase deleteCourseUseCase, UpdateCourseUseCase updateCourseUseCase) {
		this.createCourseUseCase = createCourseUseCase;
		this.findCourseUseCase = findCourseUseCase;
		this.deleteCourseUseCase = deleteCourseUseCase;
		this.updateCourseUseCase = updateCourseUseCase;
	}

	@GetMapping
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Courses encontrados"),
			@ApiResponse(responseCode = "404", description = "Courses não encontrados"),
			@ApiResponse(responseCode = "400", description = "Parametro inválido") })
	public CollectionModel<CourseResourceV1> findAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		List<CourseDTOV1> all = findCourseUseCase.findAll(pageable);

		List<CourseResourceV1> list = all.stream().map(CourseResourceV1::new).toList();

		return CollectionModel.of(list,
				WebMvcLinkBuilder
						.linkTo(WebMvcLinkBuilder.methodOn(CourseController.class).findAll(page, size, sortBy))
						.withSelfRel());
	}

	@GetMapping("/{id}")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Course encontrado"),
			@ApiResponse(responseCode = "404", description = "Course não encontrado"),
			@ApiResponse(responseCode = "400", description = "Parametro Course inválido") })
	public ResponseEntity<CourseResourceV1> findById(@PathVariable Long id) {
		return new ResponseEntity<CourseResourceV1>(new CourseResourceV1(findCourseUseCase.findById(id)),
				HttpStatus.OK);
	}

	@PostMapping
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Curso registrado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Parametro para curso inválido") })
	public ResponseEntity<?> register(@RequestBody CreateCourseRecord createEvent) {
		createCourseUseCase.execute(createEvent);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/{id}")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Course removido com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID para course inválido") })
	public ResponseEntity<?> register(@PathVariable Long id) {
		deleteCourseUseCase.execute(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping("/{id}")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Course atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Parametro coruse inválido") })
	public ResponseEntity<?> update(@RequestBody UpdateCourseRecord updateCourseRecord) {
		return new ResponseEntity<CourseResourceV1>(new CourseResourceV1(updateCourseUseCase.execute(updateCourseRecord)),
				HttpStatus.OK);
	}
}
