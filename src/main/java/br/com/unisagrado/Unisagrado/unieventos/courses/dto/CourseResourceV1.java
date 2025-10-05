package br.com.unisagrado.Unisagrado.unieventos.courses.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import br.com.unisagrado.Unisagrado.unieventos.courses.controller.CourseController;

public class CourseResourceV1 extends RepresentationModel<CourseResourceV1> {
	private final CourseDTOV1 courseDTOV1;

	public CourseResourceV1(CourseDTOV1 courseDTOV1) {
		this.courseDTOV1 = courseDTOV1;

		add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(CourseController.class).findById(courseDTOV1.id()))
				.withSelfRel());
	} 

	public CourseDTOV1 getCourse() {
		return courseDTOV1;
	}
}