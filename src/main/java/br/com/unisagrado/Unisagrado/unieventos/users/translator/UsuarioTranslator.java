package br.com.unisagrado.Unisagrado.unieventos.users.translator;

import java.util.ArrayList;
import java.util.List;

import br.com.unisagrado.Unisagrado.unieventos.users.dto.UsuarioDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.users.model.Usuario;

public class UsuarioTranslator {

	public static UsuarioDTOV1 translate(Usuario usuario) {
		UsuarioDTOV1 dtov1 = new UsuarioDTOV1(usuario.getId(), usuario.getNome(), usuario.getSobrenome(), usuario.getEmail(), usuario.getCurso().getId());
		return dtov1;
	}
	
	public List<UsuarioDTOV1> translate(List<Usuario> usuario) {
		List<UsuarioDTOV1> dtos = new ArrayList<UsuarioDTOV1>();
		dtos.addAll(usuario.stream().map(UsuarioTranslator::translate).toList());
		
		return dtos;
	}
}
