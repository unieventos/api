package br.com.unisagrado.Unisagrado.unieventos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@GetMapping
	public ResponseEntity<String> ola(){
		return new ResponseEntity<String>("Ola", HttpStatus.OK);
	}
}
