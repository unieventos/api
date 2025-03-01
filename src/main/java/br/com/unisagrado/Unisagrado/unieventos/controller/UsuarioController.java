package br.com.unisagrado.Unisagrado.unieventos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public UsuarioResource findUsuarioById(@PathVariable("id") String id){
		return new UsuarioResource(useCase.findById("da67fa3f-e7d8-43c4-87ea-c96cb71971dd"));
	}
	
}
