package br.com.unisagrado.Unisagrado.unieventos.storage.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.unisagrado.Unisagrado.unieventos.storage.exception.SaveFileException;
import br.com.unisagrado.Unisagrado.unieventos.storage.exception.StorageErrorException;
import br.com.unisagrado.Unisagrado.unieventos.storage.properties.StorageProperties;

@Component
public class StorageService {

	private StorageProperties storageProperties;

	public StorageService(StorageProperties storageProperties) {
		this.storageProperties = storageProperties;
	}

	public String createNewFile(MultipartFile file) {

		File filePath = new File(storageProperties.getPath());

		boolean diretorioComPermissoesCorretas = filePath.exists() && filePath.canRead() && filePath.canWrite();
		if (!diretorioComPermissoesCorretas) {
			throw new StorageErrorException();
		}
		
		File destino = new File(filePath, UUID.randomUUID().toString().concat(file.getOriginalFilename()));

	    try {
	        file.transferTo(destino);

	    } catch (IOException e) {
	    	throw new SaveFileException(e);
	    }
	    
	    return destino.getAbsolutePath();
	}

}
