package br.com.unisagrado.Unisagrado.unieventos.util.email.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import br.com.unisagrado.Unisagrado.unieventos.users.exception.SendEmailException;
import br.com.unisagrado.Unisagrado.unieventos.util.email.model.EmailRequest;
import br.com.unisagrado.Unisagrado.unieventos.util.email.model.Recipient;
import br.com.unisagrado.Unisagrado.unieventos.util.email.model.Sender;

@Service
public class SendEmailService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${unieventos.email.apikey}")
	private String apiKey;

	@Value("${unieventos.email.apiurl}")
	private String apiUrl;
	
	@Value("${unieventos.email.from}")
	private String from;
	
	@Value("${spring.application.name}")
	private String fromName;

	@Async
	public void sendEmail(Map<String, String> users, String subject, String template) {
		List<Recipient> recipients = new ArrayList<Recipient>();
		ObjectMapper mapper = new ObjectMapper();
		users.forEach((k, v) -> {
			recipients.add(new Recipient(k, v));
		});

		try {
			String htmlContent = carregarHtml(template);
			
			EmailRequest emailRequest = new EmailRequest(new Sender(fromName, from), recipients,
					subject, htmlContent);
			
			HttpResponse<JsonNode> request = Unirest.post(apiUrl)
					.header("accept", "application/json")
					.header("api-key", apiKey)
					.header("content-type", "application/json")
					.body(mapper.writeValueAsString(emailRequest))
					.asJson();

			logger.info(request.getStatusText());
			logger.info(request.getBody().toString());
		} catch (IOException | UnirestException ex) {
			logger.error(ex.getMessage(), ex);
			throw new SendEmailException(ex);
		}
	}

	public static String carregarHtml(String path) throws IOException {
		try (InputStream inputStream = SendEmailService.class.getClassLoader()
				.getResourceAsStream(path)) {
			if (inputStream == null) {
				throw new IllegalArgumentException("Arquivo não encontrado: " + path);
			}
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
				return reader.lines().collect(Collectors.joining("\n"));
			}
		}
	}
}
