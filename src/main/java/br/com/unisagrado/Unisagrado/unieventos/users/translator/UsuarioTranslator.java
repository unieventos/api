package br.com.unisagrado.Unisagrado.unieventos.users.translator;

import java.util.ArrayList;
import java.util.List;

import br.com.unisagrado.Unisagrado.unieventos.users.dto.UsuarioDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.users.dto.UsuarioDTOV2;
import br.com.unisagrado.Unisagrado.unieventos.users.model.Usuario;

public class UsuarioTranslator {

	public static UsuarioDTOV1 translateV1(Usuario usuario) {
		UsuarioDTOV1 dtov1 = new UsuarioDTOV1(usuario.getId(), usuario.getNome(), usuario.getSobrenome(), usuario.getEmail(), usuario.getCurso().getId());
		return dtov1;
	}
	
	public static UsuarioDTOV2 translateV2(Usuario usuario) {
		UsuarioDTOV2 dtov2 = new UsuarioDTOV2(usuario.getId(), usuario.getNome(), usuario.getSobrenome(), usuario.getEmail(), usuario.getCurso().getNome(), usuario.getRole().getName());
		return dtov2;
	}
	
	public static List<UsuarioDTOV1> translateV1(List<Usuario> usuario) {
		List<UsuarioDTOV1> dtos = new ArrayList<UsuarioDTOV1>();
		dtos.addAll(usuario.stream().map(UsuarioTranslator::translateV1).toList());
		
		return dtos;
	}
	
	public static List<UsuarioDTOV2> translateV2(List<Usuario> usuario) {
		List<UsuarioDTOV2> dtos = new ArrayList<UsuarioDTOV2>();
		dtos.addAll(usuario.stream().map(UsuarioTranslator::translateV2).toList());
		
		return dtos;
	}
}
