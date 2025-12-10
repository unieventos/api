// language: java
package br.com.unisagrado.Unisagrado.unieventos.storage.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.unisagrado.Unisagrado.unieventos.storage.exception.SaveFileException;
import br.com.unisagrado.Unisagrado.unieventos.storage.exception.StorageErrorException;
import br.com.unisagrado.Unisagrado.unieventos.storage.properties.StorageProperties;

@Component
public class StorageService {
	private static final Logger logger = LoggerFactory.getLogger(StorageService.class);

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

		// substituir caracteres inválidos no nome original
		String sanitized = original.replaceAll("[\\\\/:*?\"<>|]", "_");
		String filename = UUID.randomUUID().toString() + "-" + sanitized;
		Path destino = dir.resolve(filename).normalize();

		// proteção contra path traversal
		if (!destino.startsWith(dir)) {
			logger.error("Tentativa de gravar fora do diretório permitido: {}", destino);
			throw new SaveFileException(new SecurityException("Destino inválido"));
		}

		try {
			Files.copy(file.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			logger.error("Erro ao salvar arquivo em {}", destino, e);
			throw new SaveFileException(e);
		}

		logger.info("Arquivo salvo em {}", destino.toAbsolutePath());
		return destino.toAbsolutePath().toString();
	}

}
