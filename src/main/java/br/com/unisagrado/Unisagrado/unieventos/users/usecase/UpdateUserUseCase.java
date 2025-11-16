package br.com.unisagrado.Unisagrado.unieventos.users.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.auth.model.Role;
import br.com.unisagrado.Unisagrado.unieventos.auth.service.RoleService;
import br.com.unisagrado.Unisagrado.unieventos.courses.model.Course;
import br.com.unisagrado.Unisagrado.unieventos.courses.service.CourseService;
import br.com.unisagrado.Unisagrado.unieventos.users.dto.UpdateUserRecord;
import br.com.unisagrado.Unisagrado.unieventos.users.model.Usuario;
import br.com.unisagrado.Unisagrado.unieventos.users.service.UserService;

@Component
public class UpdateUserUseCase {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public void execute(String userId, UpdateUserRecord updateUserRecord) {
		Usuario usuario = userService.findUsuarioById(userId);
		Course curso = courseService.findCursoByName(updateUserRecord.curso().isBlank() ? usuario.getCurso().getNome() : updateUserRecord.curso());
		Role role = roleService.findRoleByNome(updateUserRecord.role().isBlank() ? usuario.getRole().getName() : updateUserRecord.role());
		String senha = updateUserRecord.senha().isBlank() ? usuario.getSenha() : passwordEncoder.encode(updateUserRecord.senha());
		userService.saveOrUpdateUser(usuario.updateUser(updateUserRecord, senha, curso,role));
	}
}
