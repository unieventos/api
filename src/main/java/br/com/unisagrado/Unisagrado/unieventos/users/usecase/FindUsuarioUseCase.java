package br.com.unisagrado.Unisagrado.unieventos.users.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.users.dto.UsuarioDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.users.dto.UsuarioDTOV2;
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
		return UsuarioTranslator.translateV1(usuarioById);
	}
	
	public UsuarioDTOV2 findPersonalData() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return UsuarioTranslator.translateV2(service.findByLogin(authentication.getName()));
	}	
	
	public List<UsuarioDTOV1> findAllByFilter(Pageable pageable, String name ){
		return UsuarioTranslator.translateV1(service.findAllByFilter(pageable, name).toList());
	}
}
