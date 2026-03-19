package br.com.unisagrado.Unisagrado.unieventos.fotos.usecase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.fotos.exception.GenericException;
import br.com.unisagrado.Unisagrado.unieventos.fotos.model.Foto;
import br.com.unisagrado.Unisagrado.unieventos.fotos.service.FotoService;

@Component
public class DownloadFotoUseCase {

	private FotoService fotoService;
	private static final Logger logger = LoggerFactory.getLogger(FotoService.class);

	public DownloadFotoUseCase(FotoService fotoService) {
		this.fotoService = fotoService;
	}
	
	public Resource execute(String idFoto) {
		
		Foto foto = fotoService.findById(idFoto);
		
        Path path = Paths.get(foto.getPath());
    	try {
    		byte[] bytesDaFoto = Files.readAllBytes(path);
            return new ByteArrayResource(bytesDaFoto);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
			throw new GenericException(e);
		}
	}

}
