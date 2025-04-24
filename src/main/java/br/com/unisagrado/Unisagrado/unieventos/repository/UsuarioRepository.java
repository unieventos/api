package br.com.unisagrado.Unisagrado.unieventos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.unisagrado.Unisagrado.unieventos.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{

	Optional<Usuario> findByLogin(String login);
	
	@Query(value = "SELECT id FROM Usuario where login = ?1")
	Optional<String> findIdByLogin(String login);
}
