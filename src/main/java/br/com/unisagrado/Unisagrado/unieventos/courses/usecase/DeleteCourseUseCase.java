package br.com.unisagrado.Unisagrado.unieventos.courses.usecase;

import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.courses.service.CourseService;

@Component
public class DeleteCourseUseCase {

	private CourseService courseService;

	public DeleteCourseUseCase(CourseService courseService) {
		this.courseService = courseService;
	}

	public void execute(Long id) {
		courseService.deleteById(id);
	}
}
