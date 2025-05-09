package br.com.unisagrado.Unisagrado.unieventos.users.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.users.dto.CreateUserRecord;
import br.com.unisagrado.Unisagrado.unieventos.users.service.UserService;

@Component
public class UpdateUserUseCase {
	
	@Autowired
	private UserService userService;

	public void execute(String userId, CreateUserRecord userRecord) {
		userService.updateUser(userId, userRecord);
	}
}
