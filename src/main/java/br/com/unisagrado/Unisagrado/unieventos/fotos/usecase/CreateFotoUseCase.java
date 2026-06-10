package br.com.unisagrado.Unisagrado.unieventos.fotos.usecase;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.unisagrado.Unisagrado.unieventos.fotos.dto.CreateFotoRecord;
import br.com.unisagrado.Unisagrado.unieventos.fotos.service.FotoService;
import br.com.unisagrado.Unisagrado.unieventos.fotos.validator.FotoValidator;
import br.com.unisagrado.Unisagrado.unieventos.storage.service.StorageService;

@Component
public class CreateFotoUseCase {

	private FotoService fotoService;
	private StorageService storageService;
	private FotoValidator fotoValidator;
	
	public CreateFotoUseCase(FotoService fotoService, StorageService storageService, FotoValidator fotoValidator) {
		this.fotoService = fotoService;
		this.storageService = storageService;
		this.fotoValidator = fotoValidator;
	}

	public void execute(CreateFotoRecord record, MultipartFile foto) {
		fotoValidator.validateIsPng(foto);
		String newFilePath = storageService.createNewFile(foto);
		fotoService.createNewFoto(record, newFilePath);
	}
	
	public void execute(CreateFotoRecord record, List<MultipartFile> fotos) {
		fotos.forEach((foto) -> this.execute(record, foto));
	}
	
}
