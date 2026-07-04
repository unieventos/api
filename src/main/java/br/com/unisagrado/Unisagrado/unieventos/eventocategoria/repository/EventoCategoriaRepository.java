package br.com.unisagrado.Unisagrado.unieventos.eventocategoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.unisagrado.Unisagrado.unieventos.eventocategoria.model.EventoCategoria;
import br.com.unisagrado.Unisagrado.unieventos.eventocategoria.model.EventoCategoriaId;

@Repository
public interface EventoCategoriaRepository extends JpaRepository<EventoCategoria, EventoCategoriaId>{

	@Modifying
	@Query("DELETE FROM EventoCategoria ec WHERE ec.evento.id = :eventoId")
	void deleteByEventoId(@Param("eventoId") String eventoId);
}
