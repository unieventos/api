package br.com.unisagrado.Unisagrado.unieventos.fotos.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;
import br.com.unisagrado.Unisagrado.unieventos.fotos.dto.CreateFotoRecord;
import br.com.unisagrado.Unisagrado.unieventos.fotos.model.Foto;
import br.com.unisagrado.Unisagrado.unieventos.model.Comentario;
import br.com.unisagrado.Unisagrado.unieventos.model.ContemFoto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Repository
public class FotoRepositoryCustomImpl implements FotoRepositoryCustom{
	
	@PersistenceContext
	private EntityManager entityManager;

	public FotoRepositoryCustomImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public <T> List<Foto> findFotosByTargetId(String targetId, Class<T> type){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Foto> query = builder.createQuery(Foto.class);
		Root<Foto> from = query.from(Foto.class);
		
		T reference = entityManager.getReference(type, targetId);

		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(from.get("alvo"), reference));

		query.where(builder.or(predicates.toArray(new Predicate[0])));
		return entityManager.createQuery(query).getResultList();
	}
	

	@Transactional
	public void createNewFoto(CreateFotoRecord record, String newFilePath, Class<? extends ContemFoto> tipo) {
		ContemFoto alvo = (ContemFoto) entityManager.find(tipo, record.id());
		if (alvo == null) {
			throw new IllegalArgumentException(
					"Registro não encontrado para tipo: " + tipo.getSimpleName() + " e id: " + record.id());
		}

		entityManager.persist(new Foto(newFilePath, alvo));
	}
	
	public List<Foto> findFotosByEventoId(String targetId){
		return findFotosByTargetId(targetId, Evento.class);
	}
	
	public List<Foto> findFotosByComentarioId(String targetId){
		return findFotosByTargetId(targetId, Comentario.class);

	}
}
