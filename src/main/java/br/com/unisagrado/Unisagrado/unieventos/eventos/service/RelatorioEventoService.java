package br.com.unisagrado.Unisagrado.unieventos.eventos.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itextpdf.html2pdf.HtmlConverter;

import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.EventoRelatorioDTO;
import br.com.unisagrado.Unisagrado.unieventos.fotos.exception.GenericException;
import br.com.unisagrado.Unisagrado.unieventos.fotos.service.FotoService;

@Service
public class RelatorioEventoService {
	private static final Logger logger = LoggerFactory.getLogger(FotoService.class);

	public byte[] gerarRelatorioEventos(List<EventoRelatorioDTO> eventos) {
		ByteArrayOutputStream target = new ByteArrayOutputStream();

		StringBuilder htmlBuilder = new StringBuilder();
		htmlBuilder.append("<html><head><style>").append("body { font-family: sans-serif; } ")
				.append("table { width: 100%; border-collapse: collapse; } ")
				.append("th, td { border: 1px solid #ccc; padding: 8px; text-align: left; } ")
				.append("th { background-color: #f2 f2 f2; } ").append(".foto { width: 100px; height: auto; }")
				.append("</style></head><body>");

		htmlBuilder.append("<h1>Lista de Eventos</h1>");
		htmlBuilder.append("<table><thead><tr>")
				.append("<th>Nome</th><th>Descrição</th><th>Início</th><th>Fim</th><th>Fotos</th>")
				.append("</tr></thead><tbody>");

		for (EventoRelatorioDTO evento : eventos) {
			htmlBuilder.append("<tr>").append("<td>").append(evento.getNomeEvento()).append("</td>").append("<td>")
					.append(evento.getDescricao()).append("</td>").append("<td>").append(evento.getDateInicio())
					.append("</td>").append("<td>").append(evento.getDateFim()).append("</td>").append("<td>");

			if (evento.getFoto() != null) {
				try {
					Path path = Paths.get(evento.getFoto().getPath());
					byte[] bytesDaFoto = Files.readAllBytes(path);
					String base64Image = Base64.getEncoder().encodeToString(bytesDaFoto);
					htmlBuilder.append("<img src='data:image/png;base64,").append(base64Image)
							.append("' class='foto' />");
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
					throw new GenericException(e);
				}
			}

			htmlBuilder.append("</td></tr>");
		}

		htmlBuilder.append("</tbody></table></body></html>");

		HtmlConverter.convertToPdf(htmlBuilder.toString(), target);

		return target.toByteArray();
	}
}
