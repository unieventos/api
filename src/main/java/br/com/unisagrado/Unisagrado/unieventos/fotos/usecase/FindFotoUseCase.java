package br.com.unisagrado.Unisagrado.unieventos.fotos.usecase;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.fotos.dto.FotoDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.fotos.service.FotoService;
import br.com.unisagrado.Unisagrado.unieventos.fotos.translator.FotoTranslator;

@Component
public class FindFotoUseCase {

	private FotoService fotoService;

	public FindFotoUseCase(FotoService fotoService) {
		this.fotoService = fotoService;
	}
	
	public List<FotoDTOV1> findAll(Pageable pageable){
		return FotoTranslator.toDto(fotoService.findAll(pageable));
	}
	
	public FotoDTOV1 findById(String fotoId) {
		return FotoTranslator.toDto(fotoService.findById(fotoId));
	}
	
}
