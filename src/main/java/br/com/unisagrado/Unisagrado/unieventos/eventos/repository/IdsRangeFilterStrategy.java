package br.com.unisagrado.Unisagrado.unieventos.eventos.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Repository;

import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.FilterEventoDTO;
import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.FilterRelatorioType;
import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;
import br.com.unisagrado.Unisagrado.unieventos.eventos.service.EventFilterStrategy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class IdsRangeFilterStrategy implements EventFilterStrategy {

	private EntityManager entityManager;

	public IdsRangeFilterStrategy(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public boolean isApplicable(FilterRelatorioType type) {
		return type == FilterRelatorioType.IDS;
	}

	@Override
	public List<Evento> filter(FilterEventoDTO params) {
		List<String> eventIds = params.getParams().getEventIds();
		boolean eventIdsIsNull = Objects.isNull(eventIds);
		boolean eventIdsIsEmpty = eventIdsIsNull ? false : eventIds.isEmpty();

		if(eventIdsIsNull || eventIdsIsEmpty) throw new IllegalArgumentException("EventIds could not be null !!");
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Evento> query = builder.createQuery(Evento.class);
		Root<Evento> from = query.from(Evento.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(from.get("id").in(eventIds));

		query.where(predicates.toArray(new Predicate[0]));
		
		return entityManager.createQuery(query).getResultList();
	}
}