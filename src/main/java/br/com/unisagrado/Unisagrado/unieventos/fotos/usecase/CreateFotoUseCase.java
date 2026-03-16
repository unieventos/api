package br.com.unisagrado.Unisagrado.unieventos.fotos.usecase;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.unisagrado.Unisagrado.unieventos.fotos.dto.CreateFotoRecord;
import br.com.unisagrado.Unisagrado.unieventos.fotos.service.FotoService;
import br.com.unisagrado.Unisagrado.unieventos.storage.service.StorageService;

@Component
public class CreateFotoUseCase {

	private FotoService fotoService;
	private StorageService storageService;
	
	public CreateFotoUseCase(FotoService fotoService, StorageService storageService) {
		this.fotoService = fotoService;
		this.storageService = storageService;
	}

	public void execute(CreateFotoRecord record, MultipartFile foto) {
		String newFilePath = storageService.createNewFile(foto);
		fotoService.createNewFoto(record, newFilePath);
	}
	
	public void execute(CreateFotoRecord record, List<MultipartFile> fotos) {
		fotos.forEach((foto) -> this.execute(record, foto));
	}
	
}
