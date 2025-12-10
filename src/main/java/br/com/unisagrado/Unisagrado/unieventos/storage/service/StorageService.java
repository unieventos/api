// language: java
package br.com.unisagrado.Unisagrado.unieventos.storage.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

		Path dir = Paths.get(storageProperties.getPath());

		try {
			if (!Files.exists(dir)) {
				Files.createDirectories(dir);
			}
		} catch (IOException e) {
			throw new StorageErrorException();
		}

		boolean diretorioComPermissoesCorretas = Files.isDirectory(dir) && Files.isReadable(dir) && Files.isWritable(dir);
		if (!diretorioComPermissoesCorretas) {
			throw new StorageErrorException();
		}

		String original = file.getOriginalFilename();
		if (original == null) {
			original = "";
		}

		String filename = UUID.randomUUID().toString() + "-" + original;
		Path destino = dir.resolve(filename);

		try {
			file.transferTo(destino.toFile());
		} catch (IOException e) {
			throw new SaveFileException(e);
		}

		return destino.toAbsolutePath().toString();
	}

}
