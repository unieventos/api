package br.com.unisagrado.Unisagrado.unieventos.eventos.usecase;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.EventoRelatorioDTO;
import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.FilterEventoDTO;
import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;
import br.com.unisagrado.Unisagrado.unieventos.eventos.service.EventFilterStrategy;
import br.com.unisagrado.Unisagrado.unieventos.eventos.service.EventoService;
import br.com.unisagrado.Unisagrado.unieventos.eventos.service.RelatorioEventoService;
import br.com.unisagrado.Unisagrado.unieventos.eventos.translator.EventoTranslatorEventoRelatorioDTO;
import br.com.unisagrado.Unisagrado.unieventos.fotos.model.Foto;
import br.com.unisagrado.Unisagrado.unieventos.fotos.service.FotoService;

@Component
public class RelatorioEventoUseCase {

	private EventoService eventoService;
	private RelatorioEventoService relatorioEventoService;
	private FotoService fotoService;
	private EventoTranslatorEventoRelatorioDTO eventoTranslatorEventoRelatorioDTO;
	private List<EventFilterStrategy> eventFilterStrategies;
	
	public RelatorioEventoUseCase(EventoService eventoService, RelatorioEventoService relatorioEventoService,
			FotoService fotoService, List<EventFilterStrategy> eventFilterStrategies) {
		this.eventoService = eventoService;
		this.relatorioEventoService = relatorioEventoService;
		this.fotoService = fotoService;
		this.eventoTranslatorEventoRelatorioDTO = new EventoTranslatorEventoRelatorioDTO();
		this.eventFilterStrategies = eventFilterStrategies;
	}


	public byte[] execute(FilterEventoDTO filter) {
		
		EventFilterStrategy strategy = eventFilterStrategies.stream()
	            .filter(s -> s.isApplicable(filter.getFilterType()))
	            .findFirst()
	            .orElseThrow(() -> new IllegalArgumentException("Tipo de filtro inválido"));

        List<Evento> eventos = strategy.filter(filter);
		
		List<EventoRelatorioDTO> eventoRelatorioDTO = new ArrayList<EventoRelatorioDTO>();
		
		for(Evento evento : eventos) {
			EventoRelatorioDTO dto = eventoTranslatorEventoRelatorioDTO.toDto(evento);
			Foto firstFotoByEventoId = fotoService.findFirstFotoByEventoId(evento.getId());
			dto.setFoto(firstFotoByEventoId);
			eventoRelatorioDTO.add(dto);
		}
		
	    return relatorioEventoService.gerarRelatorioEventos(eventoRelatorioDTO);
	    
	}
}
