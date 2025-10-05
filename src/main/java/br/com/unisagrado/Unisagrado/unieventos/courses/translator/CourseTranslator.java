package br.com.unisagrado.Unisagrado.unieventos.courses.translator;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.courses.dto.CourseDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.courses.dto.UpdateCourseRecord;
import br.com.unisagrado.Unisagrado.unieventos.courses.model.Course;

@Component
public class CourseTranslator {

	public CourseDTOV1 toDto(Course course) {
		return new CourseDTOV1(course.getId(), course.getNome());
	}
	
	public List<CourseDTOV1> toDTO(List<Course> eventos){
		return eventos.stream().map(this::toDto).toList();
	}
	
	public Course toEntity(UpdateCourseRecord courseRecord) {
		return new Course(courseRecord.id(), courseRecord.nome());
	}
}
