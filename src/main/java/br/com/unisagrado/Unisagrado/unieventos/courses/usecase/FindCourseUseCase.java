package br.com.unisagrado.Unisagrado.unieventos.courses.usecase;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.courses.dto.CourseDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.courses.model.Course;
import br.com.unisagrado.Unisagrado.unieventos.courses.service.CourseService;
import br.com.unisagrado.Unisagrado.unieventos.courses.translator.CourseTranslator;

@Component
public class FindCourseUseCase {

	private CourseService courseService;
	private CourseTranslator courseTranslator;
	
	public FindCourseUseCase(CourseService courseService, CourseTranslator courseTranslator) {
		this.courseService = courseService;
		this.courseTranslator = courseTranslator;
	}

	public List<CourseDTOV1> findAll(Pageable pageable){
		List<Course> all = courseService.findAll(pageable);
		return courseTranslator.toDTO(all);
	}
	
	public CourseDTOV1 findById(Long id) {
		Course courseById = courseService.findCourseById(id);
		return courseTranslator.toDto(courseById);
	}
}
