package br.com.unisagrado.Unisagrado.unieventos.fotos.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;
import br.com.unisagrado.Unisagrado.unieventos.fotos.dto.CreateFotoRecord;
import br.com.unisagrado.Unisagrado.unieventos.fotos.exception.FotoNotFoundException;
import br.com.unisagrado.Unisagrado.unieventos.fotos.exception.FotosForTargetIdNotFoundException;
import br.com.unisagrado.Unisagrado.unieventos.fotos.exception.GenericException;
import br.com.unisagrado.Unisagrado.unieventos.fotos.model.Foto;
import br.com.unisagrado.Unisagrado.unieventos.fotos.repository.FotoRepository;
import br.com.unisagrado.Unisagrado.unieventos.fotos.repository.FotoRepositoryCustom;
import br.com.unisagrado.Unisagrado.unieventos.model.Comentario;
import br.com.unisagrado.Unisagrado.unieventos.model.ContemFoto;

@Service
public class FotoService {

	private FotoRepository fotoRepository;
	private FotoRepositoryCustom fotoRepositoryImpl;

	public FotoService(FotoRepository fotoRepository, FotoRepositoryCustom fotoRepositoryImpl) {
		this.fotoRepository = fotoRepository;
		this.fotoRepositoryImpl = fotoRepositoryImpl;
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

	public Foto findFirstFotoByEventoId(String eventoId) {
		List<Foto> fotosByTargetId = fotoRepositoryImpl.findFotosByEventoId(eventoId);
		if (fotosByTargetId.isEmpty())
			throw new FotosForTargetIdNotFoundException(eventoId);
		return fotosByTargetId.get(0);
	}

	public Resource downloadFoto(Foto foto) {
		Path path = Paths.get(foto.getPath());
		try {
			byte[] bytesDaFoto = Files.readAllBytes(path);
			return new ByteArrayResource(bytesDaFoto);
		} catch (IOException e) {
			throw new GenericException(e);
		}
	}

	public void createNewFoto(CreateFotoRecord record, String newFilePath) {
		Class<? extends ContemFoto> tipo = switch (record.tipo().toUpperCase()) {
			case "EVENTO" -> Evento.class;
			case "COMENTARIO" -> Comentario.class;
			default -> throw new IllegalArgumentException("Tipo inválido: " + record.tipo());
		};
		
		fotoRepositoryImpl.createNewFoto(record, newFilePath, tipo);
	}

	public List<Foto> findFotosByEventoId(String id) {
		return fotoRepositoryImpl.findFotosByEventoId(id);
	}
	
	public void deleteFotosForEventoId(String eventoId){
		List<Foto> fotosByEventoId = findFotosByEventoId(eventoId);
		
		fotosByEventoId.stream().forEach(foto -> fotoRepository.delete(foto));
	}

}
