package br.com.unisagrado.Unisagrado.unieventos.courses.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.unisagrado.Unisagrado.unieventos.courses.exception.CourseAlreadyExists;
import br.com.unisagrado.Unisagrado.unieventos.courses.exception.CursoNotFoundException;
import br.com.unisagrado.Unisagrado.unieventos.courses.model.Course;
import br.com.unisagrado.Unisagrado.unieventos.courses.repository.CourseRepository;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;

	
	public Course findCourseById(Long id) {
		
		Course course = courseRepository.findById(id).orElseThrow(CursoNotFoundException::new);
		return course;
	}
	
	public List<Course> findAll(Pageable pageable) {
		return courseRepository.findAll(pageable).toList();
	}
	
	public Course findCursoByName(String nomeCurso) {
		return courseRepository.findByNome(nomeCurso).orElseThrow(CursoNotFoundException::new);
	}
	
	public Course createCourse(String nome) {
		
		Optional<Course> byNome = courseRepository.findByNome(nome);
		if(byNome.isPresent()) {
			throw new CourseAlreadyExists();
		}
		
		Course entity = courseRepository.save(new Course(nome));
		return entity;
	}

	public void deleteById(Long id) {
		courseRepository.deleteById(id);
	}

	public Course update(Course course) {
		Course entity = courseRepository.findById(course.getId()).orElseThrow(CursoNotFoundException::new);
		entity.setNome(course.getNome());
		courseRepository.save(entity);
		return entity;
	}
}
