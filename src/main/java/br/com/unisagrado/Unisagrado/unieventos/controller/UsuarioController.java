package br.com.unisagrado.Unisagrado.unieventos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.unisagrado.Unisagrado.unieventos.dto.UsuarioResource;
import br.com.unisagrado.Unisagrado.unieventos.usecase.FindUsuarioUseCase;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private FindUsuarioUseCase useCase;
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResource> findUsuarioById(@PathVariable("id") String id){
		return new ResponseEntity<UsuarioResource>(new UsuarioResource(useCase.findById(id)),HttpStatus.OK);
	}
	
	@PostMapping
	public UsuarioResource createUser(@PathVariable("id") String id){
		return new UsuarioResource(useCase.findById(id));
	}
}
