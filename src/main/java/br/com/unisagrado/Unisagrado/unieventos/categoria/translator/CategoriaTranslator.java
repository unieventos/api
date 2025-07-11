package br.com.unisagrado.Unisagrado.unieventos.categoria.translator;

import java.util.List;

import br.com.unisagrado.Unisagrado.unieventos.categoria.dto.CategoriaDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.categoria.dto.CreateCategoriaRecord;
import br.com.unisagrado.Unisagrado.unieventos.categoria.model.Categoria;

public class CategoriaTranslator {

	public static CategoriaDTOV1 toDto(Categoria categoria) {
		return new CategoriaDTOV1(categoria.getId(), categoria.getNomeCategoria());
	}
	
	public static List<CategoriaDTOV1> toDto(List<Categoria> categorias) {
		return categorias.stream().map(CategoriaTranslator::toDto).toList();
	}

	public static Categoria toEntity(CreateCategoriaRecord categoria) {
		return new Categoria(categoria.nomeCategoria());
	}
}
