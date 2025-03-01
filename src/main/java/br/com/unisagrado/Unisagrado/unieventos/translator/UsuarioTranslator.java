package br.com.unisagrado.Unisagrado.unieventos.translator;

import java.util.ArrayList;
import java.util.List;

import br.com.unisagrado.Unisagrado.unieventos.dto.UsuarioDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.model.Usuario;

public class UsuarioTranslator {

	public static UsuarioDTOV1 translate(Usuario usuario) {
		UsuarioDTOV1 dtov1 = new UsuarioDTOV1(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getCurso(), usuario.getTipoAcesso());
		return dtov1;
	}
	
	public List<UsuarioDTOV1> translate(List<Usuario> usuario) {
		List<UsuarioDTOV1> dtos = new ArrayList<UsuarioDTOV1>();
		dtos.addAll(usuario.stream().map(UsuarioTranslator::translate).toList());
		
		return dtos;
	}
}
