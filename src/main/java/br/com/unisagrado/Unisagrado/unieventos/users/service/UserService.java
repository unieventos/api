package br.com.unisagrado.Unisagrado.unieventos.users.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.unisagrado.Unisagrado.unieventos.auth.model.Role;
import br.com.unisagrado.Unisagrado.unieventos.auth.service.RoleService;
import br.com.unisagrado.Unisagrado.unieventos.courses.service.CursoService;
import br.com.unisagrado.Unisagrado.unieventos.model.Curso;
import br.com.unisagrado.Unisagrado.unieventos.users.dto.CreateUserRecord;
import br.com.unisagrado.Unisagrado.unieventos.users.exception.UserAlreadyInactive;
import br.com.unisagrado.Unisagrado.unieventos.users.exception.UserNotFoundException;
import br.com.unisagrado.Unisagrado.unieventos.users.model.Usuario;
import br.com.unisagrado.Unisagrado.unieventos.users.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CursoService cursoService;

	@Autowired
	private RoleService roleService;
	
	public List<Usuario> findUsuarios() {
		return repository.findAll();
	}
	
	@Transactional
	public void createUser(CreateUserRecord createUserRecord) {
		
		Curso curso = cursoService.findCursoByName(createUserRecord.curso());
		Role role = roleService.findRoleByNome(createUserRecord.role());

		Usuario user = new Usuario();
		user.setId(UUID.randomUUID().toString());
		user.setLogin(createUserRecord.login());
		user.setSobrenome(createUserRecord.sobrenome());
		user.setNome(createUserRecord.nome());
		user.setCurso(curso);
		user.setEmail(createUserRecord.email());
		user.setRole(role);
		user.setSenha(passwordEncoder.encode(createUserRecord.senha()));
		user.setActive(true);
		
		repository.save(user);
	}

	public Usuario findUsuarioById(String uuid) {
		return repository.findById(uuid).orElseThrow(UserNotFoundException::new);
	}
	
	public Usuario findUsuarioByEmail(String email) {
		return repository.findByEmail(email).orElseThrow(UserNotFoundException::new);
	}
	
	@Modifying
	public void updateUser(String userId, CreateUserRecord createUserRecord) {
		Usuario user = findUsuarioById(userId);
		Curso curso = cursoService.findCursoByName(createUserRecord.curso());
		Role role = roleService.findRoleByNome(createUserRecord.role());
		
		user.setLogin(createUserRecord.login());
		user.setCurso(curso);
		user.setNome(createUserRecord.nome());
		user.setSobrenome(createUserRecord.sobrenome());
		user.setEmail(createUserRecord.email());
		user.setSenha(passwordEncoder.encode(createUserRecord.senha()));
		user.setRole(role);
		
		repository.save(user);
	}
	
	@Modifying
	public void inactivateUser(String userId) {
		Usuario user = findUsuarioById(userId);
		
		if(!user.isActive()) {
			throw new UserAlreadyInactive();
		}
		
		user.setActive(false);
		repository.save(user);
	}
	
	
	public Page<Usuario> findAllByFilter(Pageable pageable, String name){
		return repository.findByNomeContainingIgnoreCase(name, pageable);
	}
	
	
	public Usuario findByLogin(String login) {
		return repository.findByLogin(login).orElseThrow(UserNotFoundException::new);
	}
}
