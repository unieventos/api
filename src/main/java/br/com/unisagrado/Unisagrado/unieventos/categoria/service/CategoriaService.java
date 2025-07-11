package br.com.unisagrado.Unisagrado.unieventos.categoria.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.unisagrado.Unisagrado.unieventos.categoria.dto.CreateCategoriaRecord;
import br.com.unisagrado.Unisagrado.unieventos.categoria.exceptions.CategoriaNotFoundException;
import br.com.unisagrado.Unisagrado.unieventos.categoria.model.Categoria;
import br.com.unisagrado.Unisagrado.unieventos.categoria.repository.CategoriaRepository;
import br.com.unisagrado.Unisagrado.unieventos.categoria.translator.CategoriaTranslator;

@Service
public class CategoriaService {

	private CategoriaRepository categoriaRepository;

	public CategoriaService(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}
	
	public Categoria findById(String idCategoria) {
		return categoriaRepository.findById(idCategoria).orElseThrow(CategoriaNotFoundException::new);
	}
	
	public List<Categoria> findAll(Pageable pageable, String name) {
		List<Categoria> categorias = categoriaRepository.findByNomeCategoriaContainingIgnoreCase(pageable,
				name);

		if (categorias.isEmpty()) {
			throw new CategoriaNotFoundException();
		}

		return categorias;
	}

	public void createNewCategoria(CreateCategoriaRecord record) {
		categoriaRepository.save(CategoriaTranslator.toEntity(record));
	}
}
