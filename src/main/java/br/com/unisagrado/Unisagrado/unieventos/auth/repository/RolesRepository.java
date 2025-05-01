package br.com.unisagrado.Unisagrado.unieventos.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unisagrado.Unisagrado.unieventos.auth.model.Role;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {

}
