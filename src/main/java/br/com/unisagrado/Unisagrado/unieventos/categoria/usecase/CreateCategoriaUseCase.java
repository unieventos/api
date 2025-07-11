package br.com.unisagrado.Unisagrado.unieventos.categoria.usecase;

import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.categoria.dto.CreateCategoriaRecord;
import br.com.unisagrado.Unisagrado.unieventos.categoria.service.CategoriaService;

@Component
public class CreateCategoriaUseCase {

	private CategoriaService categoriaService;

	public CreateCategoriaUseCase(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	public void execute(CreateCategoriaRecord record) {
		categoriaService.createNewCategoria(record);
	}
}
