package br.com.unisagrado.Unisagrado.unieventos.eventos.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itextpdf.html2pdf.HtmlConverter;

import br.com.unisagrado.Unisagrado.unieventos.eventos.dto.EventoRelatorioDTO;
import br.com.unisagrado.Unisagrado.unieventos.fotos.model.Foto;

@Service
public class RelatorioEventoService {
	private static final Logger logger = LoggerFactory.getLogger(RelatorioEventoService.class);

	public byte[] gerarRelatorioEventos(List<EventoRelatorioDTO> eventos) {
		ByteArrayOutputStream target = new ByteArrayOutputStream();
		StringBuilder htmlBuilder = new StringBuilder();

		htmlBuilder.append("<html><head><meta charset='UTF-8'><style>").append(
				"body { font-family: sans-serif; background-color: #f9fafb; color: #1f2937; margin: 0; padding: 20px; } ")
				.append(".container { max-width: 800px; margin: 0 auto; background: white; padding: 24px; border-radius: 8px; border: 1px solid #e5e7eb; margin-bottom: 20px; } ")
				.append(".header { display: table; width: 100%; border-bottom: 2px solid #b91c1c; padding-bottom: 10px; margin-bottom: 20px; } ")
				.append(".title-main { color: #b91c1c; font-size: 24px; font-weight: bold; margin: 0; } ")
				.append(".section-title { color: #dc2626; font-size: 18px; font-weight: 600; margin-bottom: 8px; border-bottom: 1px solid #fee2e2; } ")
				.append(".content-text { margin-bottom: 8px; line-height: 1.5; } ")
				.append(".bold { font-weight: bold; } ")
				.append(".grid-fotos { text-align: center; margin-top: 15px; } ")
				.append(".foto { width: 45%; display: inline-block; margin: 5px; border-radius: 4px; box-shadow: 0 1px 3px rgba(0,0,0,0.1); } ")
				.append(".footer { margin-top: 20px; background-color: #f3f4f6; padding: 10px; text-align: center; font-size: 12px; color: #4b5563; } ")
				.append("</style></head><body>");

		htmlBuilder.append("<div class='header'>")
				.append("<div style='display: table-cell; vertical-align: middle; text-align: left;'>")
				.append("<span class='title-main'>Relatório de Eventos Acadêmicos</span>").append("</div>")
				.append("<div style='display: table-cell; vertical-align: middle; text-align: right; width: 40px;'>").append("</div>").append("</div>");

		for (EventoRelatorioDTO evento : eventos) {
			htmlBuilder.append("<div class='container'>");

			htmlBuilder.append("<h2 class='section-title'>").append(evento.getNomeEvento()).append("</h2>");

			htmlBuilder.append("<div class='content-text'><span class='bold'>Descrição:</span> ")
					.append(evento.getDescricao()).append("</div>");
			htmlBuilder.append("<div class='content-text'><span class='bold'>Data:</span> ")
					.append(evento.getDateInicio()).append(" até ").append(evento.getDateFim()).append("</div>");

			if (evento.getFotos() != null && !evento.getFotos().isEmpty()) {
				htmlBuilder.append("<div class='grid-fotos'>");
				for (Foto foto : evento.getFotos()) {
					try {
						Path path = Paths.get(foto.getPath());
						if (Files.exists(path)) {
							BufferedImage image = ImageIO.read(path.toFile());
							if (image != null) {
								// Garante que a imagem seja convertida para RGB (remove transparência que o JPEG não suporta)
								BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
								java.awt.Graphics2D g2d = newImage.createGraphics();
								g2d.setColor(java.awt.Color.WHITE);
								g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
								g2d.drawImage(image, 0, 0, null);
								g2d.dispose();

								ByteArrayOutputStream baos = new ByteArrayOutputStream();
								ImageIO.write(newImage, "jpg", baos);
								byte[] bytesDaFoto = baos.toByteArray();
								String base64Image = Base64.getEncoder().encodeToString(bytesDaFoto);

								htmlBuilder.append("<div style='display: inline-block; width: 45%; margin: 5px; vertical-align: top;'>");
								htmlBuilder.append("<img src='data:image/jpeg;base64,").append(base64Image)
										.append("' style='width: 100%; border-radius: 4px; box-shadow: 0 1px 3px rgba(0,0,0,0.1);' />");
								htmlBuilder.append("</div>");
							} else {
								logger.warn("ImageIO retornou null ao ler a imagem: {}", foto.getPath());
							}
						} else {
							logger.warn("Arquivo de foto não encontrado no caminho: {}", foto.getPath());
						}
					} catch (IOException e) {
						logger.error("Erro ao processar imagem {}: {}", foto.getPath(), e.getMessage());
					}
				}
				htmlBuilder.append("</div>");
			}

			htmlBuilder.append("</div>");
		}

		htmlBuilder.append("<footer class='footer'>© 2026 UNISAGRADO - Relatório Gerado Automaticamente</footer>");
		htmlBuilder.append("</body></html>");

		HtmlConverter.convertToPdf(htmlBuilder.toString(), target);

		return target.toByteArray();
	}
}
