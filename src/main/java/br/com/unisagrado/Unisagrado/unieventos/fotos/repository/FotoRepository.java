package br.com.unisagrado.Unisagrado.unieventos.fotos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unisagrado.Unisagrado.unieventos.fotos.model.Foto;

@Repository
public interface FotoRepository extends JpaRepository<Foto, String>{

	
}
