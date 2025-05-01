package br.com.unisagrado.Unisagrado.unieventos.users.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.users.dto.CreateUserRecord;
import br.com.unisagrado.Unisagrado.unieventos.users.service.UsuarioService;
import br.com.unisagrado.Unisagrado.unieventos.users.validator.CreateUserPermissionValidator;

@Component
public class CreateUserUseCase {

	@Autowired
	private UsuarioService usuarioService;
	
	public void execute(CreateUserRecord createUserRecord) {
		CreateUserPermissionValidator.validate();
		usuarioService.createUser(createUserRecord);
	}
}
