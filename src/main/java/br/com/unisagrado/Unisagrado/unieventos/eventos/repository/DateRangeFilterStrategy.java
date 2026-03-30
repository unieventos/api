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
public class DateRangeFilterStrategy implements EventFilterStrategy {

	private EntityManager entityManager;

	public DateRangeFilterStrategy(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public boolean isApplicable(FilterRelatorioType type) {
		return type == FilterRelatorioType.PERIOD;
	}

	@Override
	public List<Evento> filter(FilterEventoDTO params) {
		boolean startDateNull = Objects.isNull(params.getParams().getStartDate());
		boolean endDateNull = Objects.isNull(params.getParams().getEndDate());

		if(startDateNull || endDateNull) throw new IllegalArgumentException("StartDate or EndDate could not be null !!");
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Evento> query = builder.createQuery(Evento.class);
		Root<Evento> from = query.from(Evento.class);

		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(from.get("dateInicio"), params.getParams().getStartDate()));
		predicates.add(builder.equal(from.get("dateFim"), params.getParams().getStartDate()));

		query.where(builder.or(predicates.toArray(new Predicate[0])));
		
		return entityManager.createQuery(query).getResultList();
	}
}