package br.com.unisagrado.Unisagrado.unieventos.users.service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.unisagrado.Unisagrado.unieventos.auth.model.Role;
import br.com.unisagrado.Unisagrado.unieventos.courses.service.CursoService;
import br.com.unisagrado.Unisagrado.unieventos.model.Curso;
import br.com.unisagrado.Unisagrado.unieventos.users.dto.CreateUserRecord;
import br.com.unisagrado.Unisagrado.unieventos.users.exception.UserNotFoundException;
import br.com.unisagrado.Unisagrado.unieventos.users.model.Usuario;
import br.com.unisagrado.Unisagrado.unieventos.users.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	@Autowired
	public UsuarioRepository repository;

	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Autowired
	public CursoService cursoService;

	public List<Usuario> findUsuarios() {
		return repository.findAll();
	}

	@Transactional
	public void createUser(CreateUserRecord createUserDto) {
		
		Curso curso = cursoService.findCursoByName(createUserDto.curso());

		Usuario user = new Usuario();
		user.setId(UUID.randomUUID().toString());
		user.setLogin(createUserDto.login());
		user.setSobrenome(createUserDto.sobrenome());
		user.setNome(createUserDto.nome());
		user.setCurso(curso);
		user.setEmail(createUserDto.email());
		user.setRoles(new HashSet<Role>());
		user.setSenha(passwordEncoder.encode(createUserDto.senha()));

		repository.save(user);
	}

	public Usuario findUsuarioById(String uuid) {
		return repository.findById(uuid).orElseThrow(UserNotFoundException::new);
	}
}
