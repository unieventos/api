package br.com.unisagrado.Unisagrado.unieventos.controller;

import br.com.unisagrado.Unisagrado.unieventos.dto.CreateUserRecord;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.unisagrado.Unisagrado.unieventos.dto.UsuarioResource;
import br.com.unisagrado.Unisagrado.unieventos.usecase.FindUsuarioUseCase;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private FindUsuarioUseCase findUsuarioUseCase;

//	@Autowired
//	private FindUsuarioUseCase findUsuarioUseCase;

	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResource> findUsuarioById(@PathVariable("id") String id){
		return new ResponseEntity<UsuarioResource>(new UsuarioResource(findUsuarioUseCase.findById(id)),HttpStatus.OK);
	}
	
//	@PostMapping
//	public UsuarioResource createUser(@RequestBody @Valid CreateUserRecord createUserDTO){
//		return new
//	}
}
