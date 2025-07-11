package br.com.unisagrado.Unisagrado.unieventos.eventos.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, String> {

	List<Evento> findByNomeEventoContainingIgnoreCase(Pageable pageable, String name);
}
