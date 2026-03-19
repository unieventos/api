package br.com.unisagrado.Unisagrado.unieventos.fotos.service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;
import br.com.unisagrado.Unisagrado.unieventos.fotos.dto.CreateFotoRecord;
import br.com.unisagrado.Unisagrado.unieventos.fotos.exception.FotoNotFoundException;
import br.com.unisagrado.Unisagrado.unieventos.fotos.exception.GenericException;
import br.com.unisagrado.Unisagrado.unieventos.fotos.model.Foto;
import br.com.unisagrado.Unisagrado.unieventos.fotos.model.FotoTypeEnum;
import br.com.unisagrado.Unisagrado.unieventos.fotos.repository.FotoRepository;
import br.com.unisagrado.Unisagrado.unieventos.model.Comentario;
import br.com.unisagrado.Unisagrado.unieventos.model.ContemFoto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

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
			throw new IllegalArgumentException(
					"Registro não encontrado para tipo: " + tipo.getSimpleName() + " e id: " + record.id());
		}

		fotoRepository.save(new Foto(newFilePath, alvo));
	}
	
	public List<Foto> findFotosByEventoId(String eventoId){
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Foto> query = builder.createQuery(Foto.class);
		Root<Foto> from = query.from(Foto.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(from.get("alvo"), FotoTypeEnum.EVENTO.toString()));
		predicates.add(builder.equal(from.get("id"), eventoId));

		query.where(builder.and(predicates.toArray(new Predicate[0])));
		return entityManager.createQuery(query).getResultList();
	}
	
	public List<Resource> downloadFotosByEventoId(String eventoId){
		List<Foto> fotos = findFotosByEventoId(eventoId);
		
		List<Resource> resources = new ArrayList<Resource>();
		fotos.forEach((f) -> {
	        Path path = Paths.get(f.getPath());
	    	try {
	    		resources.add(new UrlResource(path.toUri()));
			} catch (MalformedURLException e) {
				throw new GenericException(e);
			}
		}); 
		
		return resources;
	}

}
