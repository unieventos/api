package br.com.unisagrado.Unisagrado.unieventos.fotos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;
import br.com.unisagrado.Unisagrado.unieventos.fotos.dto.CreateFotoRecord;
import br.com.unisagrado.Unisagrado.unieventos.fotos.exception.FotoNotFoundException;
import br.com.unisagrado.Unisagrado.unieventos.fotos.model.Foto;
import br.com.unisagrado.Unisagrado.unieventos.fotos.repository.FotoRepository;
import br.com.unisagrado.Unisagrado.unieventos.model.Comentario;
import br.com.unisagrado.Unisagrado.unieventos.model.ContemFoto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

@Service
public class FotoService {

	private FotoRepository fotoRepository;
	private EntityManager entityManager;

	public FotoService(FotoRepository fotoRepository, EntityManager entityManager) {
		this.fotoRepository = fotoRepository;
		this.entityManager = entityManager;
	}

	public List<Foto> findAll(Pageable pageable) {
		Page<Foto> all = fotoRepository.findAll(pageable);

		if (all.isEmpty()) {
			throw new FotoNotFoundException();
		}

		return all.toList();
	}

	public Foto findById(String id) {
		return fotoRepository.findById(id).orElseThrow(FotoNotFoundException::new);
	}

	public void createNewFoto(CreateFotoRecord record, String newFilePath) {
		Class<? extends ContemFoto> tipo = switch (record.tipo().toUpperCase()) {
	    case "EVENTO" -> Evento.class;
	    case "COMENTARIO" -> Comentario.class;
	    default -> throw new IllegalArgumentException("Tipo inválido: " + record.tipo());
	};

	ContemFoto alvo = (ContemFoto) entityManager.find(tipo, record.id());
	if (alvo == null) {
	    throw new IllegalArgumentException("Registro não encontrado para tipo: " + tipo.getSimpleName() + " e id: " + record.id());
	}
	
	fotoRepository.save(new Foto(newFilePath, alvo));
	}

}
