package br.com.unisagrado.Unisagrado.unieventos.courses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unisagrado.Unisagrado.unieventos.courses.exception.CursoNotFoundException;
import br.com.unisagrado.Unisagrado.unieventos.courses.repository.CursoRepository;
import br.com.unisagrado.Unisagrado.unieventos.model.Curso;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository cursosRepository;

	public Curso findCursoByName(String nomeCurso) {
		return cursosRepository.findByNome(nomeCurso).orElseThrow(CursoNotFoundException::new);
	}
}
