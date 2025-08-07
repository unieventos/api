package br.com.unisagrado.Unisagrado.unieventos.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.unisagrado.Unisagrado.unieventos.users.dto.CreateUserRecord;
import br.com.unisagrado.Unisagrado.unieventos.users.dto.UsuarioDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.users.dto.UsuarioResourceV1;
import br.com.unisagrado.Unisagrado.unieventos.users.dto.UsuarioResourceV2;
import br.com.unisagrado.Unisagrado.unieventos.users.usecase.CreateUserUseCase;
import br.com.unisagrado.Unisagrado.unieventos.users.usecase.FindUsuarioUseCase;
import br.com.unisagrado.Unisagrado.unieventos.users.usecase.InactivateUser;
import br.com.unisagrado.Unisagrado.unieventos.users.usecase.UpdateUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private FindUsuarioUseCase findUsuarioUseCase;

	@Autowired
	private CreateUserUseCase createUserUseCase;

	@Autowired
	private UpdateUserUseCase updateUserUseCase;

	@Autowired
	private InactivateUser inactivateUser;

	@GetMapping
	public CollectionModel<UsuarioResourceV1> findAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "", required = false) String name) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		List<UsuarioDTOV1> all = findUsuarioUseCase.findAllByFilter(pageable, name);

		List<UsuarioResourceV1> list = all.stream().map(UsuarioResourceV1::new).toList();

		return CollectionModel.of(list,
				WebMvcLinkBuilder
						.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).findAll(page, size, sortBy, name))
						.withSelfRel());
	}

	@Operation(summary = "Busca usuario por ID", description = "Retorna os dados completos da entidade correspondente ao ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
			@ApiResponse(responseCode = "404", description = "Usuario não encontrado"),
			@ApiResponse(responseCode = "400", description = "Id de usuário inválido") })
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResourceV1> findUsuarioById(@PathVariable String id) {
		return new ResponseEntity<UsuarioResourceV1>(new UsuarioResourceV1(findUsuarioUseCase.findById(id)),
				HttpStatus.OK);
	}

	@GetMapping("/me")
	public ResponseEntity<UsuarioResourceV2> findPersonalData() {
		return new ResponseEntity<UsuarioResourceV2>(new UsuarioResourceV2(findUsuarioUseCase.findPersonalData()),
				HttpStatus.OK);
	}

	@Operation(summary = "Atualizar um usuário", description = "Atualizar um usuário existente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Usuario atualizado com sucesso") })
	@PreAuthorize("hasAnyRole('ADMIN', 'GESTOR')")
	@PatchMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody CreateUserRecord body) {
		updateUserUseCase.execute(id, body);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Operation(summary = "Cadastrar novo usuário", description = "Cadastrar um novo usuário na plataforma.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Usuario criado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Parâmetros inválidos para criação do usuário") })
	@PreAuthorize("hasAnyRole('ADMIN', 'GESTOR')")
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserRecord createUserDTO) {
		createUserUseCase.execute(createUserDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@Operation(summary = "Inativar um usuário", description = "Inativar um usuário na plataforma.")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Usuario inativado com sucesso"), })
	@PreAuthorize("hasAnyRole('ADMIN', 'GESTOR')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> inactiveUser(@PathVariable String id) {
		inactivateUser.execute(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
