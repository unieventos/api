package br.com.unisagrado.Unisagrado.unieventos.categoria.usecase;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.categoria.dto.CategoriaDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.categoria.service.CategoriaService;
import br.com.unisagrado.Unisagrado.unieventos.categoria.translator.CategoriaTranslator;

@Component
public class FindCategoriaUseCase {
	
	private CategoriaService categoriaService;
	
	public FindCategoriaUseCase(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	public List<CategoriaDTOV1> findAll(Pageable pageable, String name) {
		return CategoriaTranslator.toDto(categoriaService.findAll(pageable, name));
	}
	
	public CategoriaDTOV1 findById(String idCategoria) {
		return CategoriaTranslator.toDto(categoriaService.findById(idCategoria));
	}
}
