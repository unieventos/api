package br.com.unisagrado.Unisagrado.unieventos.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.dto.UsuarioDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.model.Usuario;
import br.com.unisagrado.Unisagrado.unieventos.service.UsuarioService;
import br.com.unisagrado.Unisagrado.unieventos.translator.UsuarioTranslator;

@Component
public class FindUsuarioUseCase {
	
	@Autowired
	private UsuarioService service;

	public UsuarioDTOV1 findById(String uuid) {
		Usuario usuarioById = service.findUsuarioById(uuid);
		return UsuarioTranslator.translate(usuarioById);
	}
}
