package br.com.unisagrado.Unisagrado.unieventos.categoria.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unisagrado.Unisagrado.unieventos.categoria.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, String>{

	List<Categoria> findByNomeCategoriaContainingIgnoreCase(Pageable pageable, String name);
}
