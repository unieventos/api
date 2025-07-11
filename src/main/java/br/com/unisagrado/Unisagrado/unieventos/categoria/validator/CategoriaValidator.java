package br.com.unisagrado.Unisagrado.unieventos.categoria.validator;

import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.categoria.service.CategoriaService;

@Component
public class CategoriaValidator {

	private CategoriaService categoriaService;
	
	public CategoriaValidator(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	public void validateCategoriaExists(String idCategoria) {
		categoriaService.findById(idCategoria);
	}
	
}
