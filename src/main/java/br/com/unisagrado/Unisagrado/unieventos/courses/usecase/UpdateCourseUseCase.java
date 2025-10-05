package br.com.unisagrado.Unisagrado.unieventos.courses.usecase;

import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.courses.dto.CourseDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.courses.dto.UpdateCourseRecord;
import br.com.unisagrado.Unisagrado.unieventos.courses.service.CourseService;
import br.com.unisagrado.Unisagrado.unieventos.courses.translator.CourseTranslator;

@Component
public class UpdateCourseUseCase {

	private CourseService courseService;
	private CourseTranslator courseTranslator;
	
	public CourseDTOV1 execute(UpdateCourseRecord courseRecord) {
		return courseTranslator.toDto(courseService.update(courseTranslator.toEntity(courseRecord)));
	}
}
