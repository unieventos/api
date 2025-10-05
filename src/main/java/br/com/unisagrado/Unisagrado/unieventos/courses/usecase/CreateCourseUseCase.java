package br.com.unisagrado.Unisagrado.unieventos.courses.usecase;

import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.courses.dto.CourseDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.courses.dto.CreateCourseRecord;
import br.com.unisagrado.Unisagrado.unieventos.courses.model.Course;
import br.com.unisagrado.Unisagrado.unieventos.courses.service.CourseService;
import br.com.unisagrado.Unisagrado.unieventos.courses.translator.CourseTranslator;

@Component
public class CreateCourseUseCase {

	private CourseService courseService;
	private CourseTranslator courseTranslator;

	public CreateCourseUseCase(CourseService courseService, CourseTranslator courseTranslator) {
		this.courseService = courseService;
		this.courseTranslator = courseTranslator;
	}

	public CourseDTOV1 execute(CreateCourseRecord courseRecord) {
		Course course = courseService.createCourse(courseRecord.nome());
		return courseTranslator.toDto(course);
	}

}
