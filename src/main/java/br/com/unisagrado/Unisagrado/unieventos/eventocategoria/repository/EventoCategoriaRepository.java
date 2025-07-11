package br.com.unisagrado.Unisagrado.unieventos.eventocategoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unisagrado.Unisagrado.unieventos.eventocategoria.model.EventoCategoria;
import br.com.unisagrado.Unisagrado.unieventos.eventocategoria.model.EventoCategoriaId;

@Repository
public interface EventoCategoriaRepository extends JpaRepository<EventoCategoria, EventoCategoriaId>{

}
