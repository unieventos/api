package br.com.unisagrado.Unisagrado.unieventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unisagrado.Unisagrado.unieventos.model.Role;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {

}
