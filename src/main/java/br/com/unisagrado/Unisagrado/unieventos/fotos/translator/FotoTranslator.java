package br.com.unisagrado.Unisagrado.unieventos.fotos.translator;

import java.util.List;

import br.com.unisagrado.Unisagrado.unieventos.fotos.dto.FotoDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.fotos.model.Foto;

public class FotoTranslator {

	public static FotoDTOV1 toDto(Foto foto) {
		return new FotoDTOV1(foto.getId(), foto.getPath(), foto.getAlvo().nome(), foto.getAlvo().getId());
	}
	
	public static List<FotoDTOV1> toDto(List<Foto> fotos) {
		return fotos.stream().map(FotoTranslator::toDto).toList();
	}
	
}
