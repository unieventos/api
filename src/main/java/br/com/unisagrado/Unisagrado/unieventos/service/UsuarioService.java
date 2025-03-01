package br.com.unisagrado.Unisagrado.unieventos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unisagrado.Unisagrado.unieventos.exception.UserNotFoundException;
import br.com.unisagrado.Unisagrado.unieventos.model.Usuario;
import br.com.unisagrado.Unisagrado.unieventos.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	public UsuarioRepository repository;

	public List<Usuario> findUsuarios() {
		return repository.findAll();
	}

	public Usuario findUsuarioById(String id) {
		return repository.findById(id).orElseThrow(UserNotFoundException::new);
	}
}
