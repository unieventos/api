package br.com.unisagrado.Unisagrado.unieventos.courses.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unisagrado.Unisagrado.unieventos.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

	Optional<Curso> findByNome(String nome);
}
