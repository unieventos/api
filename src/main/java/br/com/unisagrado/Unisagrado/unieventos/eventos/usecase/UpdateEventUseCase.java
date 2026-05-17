package br.com.unisagrado.Unisagrado.unieventos.eventos.usecase;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.EventoDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.UpdateEventoDTO;
import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;
import br.com.unisagrado.Unisagrado.unieventos.eventos.service.EventoService;
import br.com.unisagrado.Unisagrado.unieventos.eventos.translator.EventoTranslatorDTOV1;
import br.com.unisagrado.Unisagrado.unieventos.fotos.usecase.UpdateFotosUseCase;

@Component
public class UpdateEventUseCase {

	private EventoService eventoService;
	private EventoTranslatorDTOV1 dtov1;
	private UpdateFotosUseCase updateFotosUseCase;


	public UpdateEventUseCase(EventoService eventoService, EventoTranslatorDTOV1 dtov1,
			UpdateFotosUseCase updateFotosUseCase) {
		this.eventoService = eventoService;
		this.dtov1 = dtov1;
		this.updateFotosUseCase = updateFotosUseCase;
	}


	public EventoDTOV1 execute(String eventoId, UpdateEventoDTO updateEvento, Optional<List<MultipartFile>> fotos) {
		if(fotos.isPresent()) updateFotosUseCase.updateForEventoId(eventoId, fotos.get());
		
		Evento entity = eventoService.findById(eventoId);
		entity.update(updateEvento.toEntity());
		return dtov1.toDto(eventoService.update(entity));
	}

}
