package br.com.unisagrado.Unisagrado.unieventos.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unisagrado.Unisagrado.unieventos.auth.exception.RoleNotFoundException;
import br.com.unisagrado.Unisagrado.unieventos.auth.model.Role;
import br.com.unisagrado.Unisagrado.unieventos.auth.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public Role findRoleByNome(String roleName) {
		return roleRepository.findByName(roleName).orElseThrow(RoleNotFoundException::new);
	}
}
