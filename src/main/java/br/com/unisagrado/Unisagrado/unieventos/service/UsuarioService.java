package br.com.unisagrado.Unisagrado.unieventos.service;

import java.util.List;

import br.com.unisagrado.Unisagrado.unieventos.dto.CreateUserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.unisagrado.Unisagrado.unieventos.exception.UserNotFoundException;
import br.com.unisagrado.Unisagrado.unieventos.model.Usuario;
import br.com.unisagrado.Unisagrado.unieventos.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	public UsuarioRepository repository;

	@Autowired
	public PasswordEncoder passwordEncoder;

	public List<Usuario> findUsuarios() {
		return repository.findAll();
	}


	public Usuario creteUser(CreateUserRecord createUserDto) {

		Usuario user = new Usuario();
		user.setLogin(createUserDto.login());
		user.setSobrenome(createUserDto.sobrenome());
		user.setNome(createUserDto.nome());
		user.setCurso(createUserDto.curso());
		user.setEmail(createUserDto.email());
		user.setSenha(passwordEncoder.encode(createUserDto.senha()));

		return user;
	}

	public Usuario findUsuarioById(String uuid) {
		return repository.findById(uuid).orElseThrow(UserNotFoundException::new);
	}
}
