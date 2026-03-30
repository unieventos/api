package br.com.unisagrado.Unisagrado.unieventos.eventos.repository;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Repository;

import br.com.unisagrado.Unisagrado.unieventos.categoria.model.Categoria;
import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.FilterEventoDTO;
import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.FilterRelatorioType;
import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;
import br.com.unisagrado.Unisagrado.unieventos.eventos.service.EventFilterStrategy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

@Repository
public class CategoryFilterStrategy implements EventFilterStrategy {

	private EntityManager entityManager;

	public CategoryFilterStrategy(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public boolean isApplicable(FilterRelatorioType type) {
		return type == FilterRelatorioType.CATEGORY;
	}

	@Override
	public List<Evento> filter(FilterEventoDTO params) {
		String categoryId = params.getParams().getCategoryId();
		boolean courseIsNull = Objects.isNull(categoryId);

		if (courseIsNull)
			throw new IllegalArgumentException("Course could not be null !!");

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Evento> query = builder.createQuery(Evento.class);
		Root<Evento> evento = query.from(Evento.class);

		Join<Evento, Categoria> categoriaJoin = evento.join("eventoCategoria");

		query.where(builder.equal(categoriaJoin.get("id"), categoryId));
		
		return entityManager.createQuery(query).getResultList();
	}
}