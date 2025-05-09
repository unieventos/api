package br.com.unisagrado.Unisagrado.unieventos.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unisagrado.Unisagrado.unieventos.auth.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(String roleName);

}
