package br.com.unisagrado.Unisagrado.unieventos.fotos.usecase;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.unisagrado.Unisagrado.unieventos.fotos.dto.CreateFotoRecord;
import br.com.unisagrado.Unisagrado.unieventos.fotos.service.FotoService;
import br.com.unisagrado.Unisagrado.unieventos.storage.service.StorageService;

@Component
public class UpdateFotosUseCase {


	private FotoService fotoService;
	private StorageService storageService;
	
	public UpdateFotosUseCase(FotoService fotoService, StorageService storageService) {
		this.fotoService = fotoService;
		this.storageService = storageService;
	}

	public void updateForEventoId(String eventoId, List<MultipartFile> fotos) {
		fotoService.deleteFotosForEventoId(eventoId);
		for (MultipartFile foto : fotos) {
			String newFilePath = storageService.createNewFile(foto);
			fotoService.createNewFoto(new CreateFotoRecord("Evento", eventoId), newFilePath);
		}
	}
	
}
