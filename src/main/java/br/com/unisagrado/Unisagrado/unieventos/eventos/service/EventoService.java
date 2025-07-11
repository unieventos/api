package br.com.unisagrado.Unisagrado.unieventos.eventos.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.unisagrado.Unisagrado.unieventos.eventocategoria.service.EventoCategoriaService;
import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.CreateEventRecord;
import br.com.unisagrado.Unisagrado.unieventos.eventos.exception.EventNotFoundException;
import br.com.unisagrado.Unisagrado.unieventos.eventos.model.Evento;
import br.com.unisagrado.Unisagrado.unieventos.eventos.repository.EventoRepository;
import br.com.unisagrado.Unisagrado.unieventos.eventos.translator.EventoTranslator;
import br.com.unisagrado.Unisagrado.unieventos.users.model.Usuario;
import br.com.unisagrado.Unisagrado.unieventos.users.service.UserService;

@Service
public class EventoService {

	private EventoRepository eventoRepository;
	private UserService userService;

	public EventoService(EventoRepository eventoRepository, UserService userService) {
		this.eventoRepository = eventoRepository;
		this.userService = userService;
	}

	public List<Evento> findAll(Pageable pageable, String name) {
		List<Evento> byNomeEventoContainingIgnoreCase = eventoRepository.findByNomeEventoContainingIgnoreCase(pageable,
				name);

		if (byNomeEventoContainingIgnoreCase.isEmpty()) {
			throw new EventNotFoundException();
		}

		return byNomeEventoContainingIgnoreCase;
	}

	public Evento findById(String idEvento) {
		return eventoRepository.findById(idEvento).orElseThrow(EventNotFoundException::new);
	}

	public Evento createNewEvent(CreateEventRecord createEvent) {
		Evento entity = EventoTranslator.toEntity(createEvent);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario byLogin = userService.findByLogin(authentication.getName());
		entity.setUsuarioCriador(byLogin);

		return eventoRepository.save(entity);
	}
}
