package br.com.unisagrado.Unisagrado.unieventos.users.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.users.dto.UsuarioDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.users.model.Usuario;
import br.com.unisagrado.Unisagrado.unieventos.users.service.UserService;
import br.com.unisagrado.Unisagrado.unieventos.users.translator.UsuarioTranslator;
import br.com.unisagrado.Unisagrado.unieventos.users.validator.ValidateUserId;

@Component
public class FindUsuarioUseCase {
	
	@Autowired
	private UserService service;
	
	public UsuarioDTOV1 findById(String uuid) {
		ValidateUserId.validate(uuid);
		Usuario usuarioById = service.findUsuarioById(uuid);
		return UsuarioTranslator.translate(usuarioById);
	}
	
	public List<UsuarioDTOV1> findAllByFilter(Pageable pageable, String name ){
		return UsuarioTranslator.translate(service.findAllByFilter(pageable, name).toList());
	}
}
