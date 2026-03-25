package br.com.unisagrado.Unisagrado.unieventos.eventos.repository;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Repository;

import br.com.unisagrado.Unisagrado.unieventos.courses.model.Course;
import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.FilterRelatorioDTO;
import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.FilterRelatorioType;
import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;
import br.com.unisagrado.Unisagrado.unieventos.eventos.service.EventFilterStrategy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

@Repository
public class CourseFilterStrategy implements EventFilterStrategy {

	private EntityManager entityManager;

	public CourseFilterStrategy(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public boolean isApplicable(FilterRelatorioType type) {
		return type == FilterRelatorioType.COURSE;
	}

	@Override
	public List<Evento> filter(FilterRelatorioDTO params) {
		String courseId = params.getParams().getCourse();
		boolean courseIsNull = Objects.isNull(courseId);

		if (courseIsNull)
			throw new IllegalArgumentException("Course could not be null !!");

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Evento> query = builder.createQuery(Evento.class);
		Root<Evento> from = query.from(Evento.class);
		Join<Evento, Course> course = from.join("curso");
		query.where(builder.equal(course.get("id"), courseId));

		return entityManager.createQuery(query).getResultList();
	}
}