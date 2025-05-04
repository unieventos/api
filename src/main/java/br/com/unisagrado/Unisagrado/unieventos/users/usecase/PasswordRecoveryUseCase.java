package br.com.unisagrado.Unisagrado.unieventos.users.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.users.dto.UserPasswordRecoveryDTO;
import br.com.unisagrado.Unisagrado.unieventos.users.model.Usuario;
import br.com.unisagrado.Unisagrado.unieventos.users.service.SendEmailRecoveryPasswordService;
import br.com.unisagrado.Unisagrado.unieventos.users.service.UserService;
import br.com.unisagrado.Unisagrado.unieventos.users.validator.PasswordRecoveryValidator;

@Component
public class PasswordRecoveryUseCase {

	@Autowired
	private UserService userService;
	
	@Autowired
	private SendEmailRecoveryPasswordService emailRecoveryPasswordService;
	
	public void execute(UserPasswordRecoveryDTO userEmail) {
		Usuario user = userService.findUsuarioByEmail(userEmail.getEmail());
		PasswordRecoveryValidator.validate(user);
		emailRecoveryPasswordService.send(user);
	}
}
