package br.com.unisagrado.Unisagrado.unieventos.fotos.usecase;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.fotos.exception.GenericException;
import br.com.unisagrado.Unisagrado.unieventos.fotos.model.Foto;
import br.com.unisagrado.Unisagrado.unieventos.fotos.service.FotoService;

@Component
public class DownloadFotoUseCase {

	private FotoService fotoService;

	public DownloadFotoUseCase(FotoService fotoService) {
		this.fotoService = fotoService;
	}
	
	public Resource execute(String idFoto) {
		
		Foto foto = fotoService.findById(idFoto);
		
        Path path = Paths.get(foto.getPath());
    	try {
			Resource resource = new UrlResource(path.toUri());
			return resource;
		} catch (MalformedURLException e) {
			throw new GenericException(e);
		}
	}

}
