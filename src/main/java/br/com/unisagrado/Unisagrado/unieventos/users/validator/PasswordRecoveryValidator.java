package br.com.unisagrado.Unisagrado.unieventos.users.validator;

import br.com.unisagrado.Unisagrado.unieventos.users.exception.UsuarioInativoException;
import br.com.unisagrado.Unisagrado.unieventos.users.model.Usuario;

public class PasswordRecoveryValidator {
	
	public static void validate(Usuario usuario) {
		if(!usuario.getActive()) {
			throw new UsuarioInativoException();
		}
	}
}
