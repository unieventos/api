package br.com.unisagrado.Unisagrado.unieventos.users.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import br.com.unisagrado.Unisagrado.unieventos.users.exception.SendEmailException;
import br.com.unisagrado.Unisagrado.unieventos.users.model.Usuario;

@Service
public class SendEmailRecoveryPasswordService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${unieventos.email.apikey}")
	private String apiKey;
	
	@Value("${unieventos.email.apiurl}")
	private String apiUrl;

	public static String carregarHtml(String path) throws IOException {
		try (InputStream inputStream = SendEmailRecoveryPasswordService.class.getClassLoader()
				.getResourceAsStream(path)) {
			if (inputStream == null) {
				throw new IllegalArgumentException("Arquivo não encontrado: " + path);
			}
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
				return reader.lines().collect(Collectors.joining("\n"));
			}
		}
	}

	@Async
	public void send(Usuario usuario) {
		try {
			String htmlContent = carregarHtml("templates/email/password-recovery.html");

			HttpResponse<JsonNode> request = Unirest
					.post(apiUrl)
					.queryString("apikey", apiKey)
					.queryString("from", "otaviopegorin@gmail.com")
					.queryString("to", to(usuario))
					.queryString("subject", "Recuperação de Senha").queryString("bodyHtml", htmlContent).asJson();
			
		} catch (IOException | UnirestException ex) {
			logger.error(ex.getMessage(), ex);
			throw new SendEmailException(ex);
		}

	}
	
	private String to(Usuario usuario) {
		StringBuilder stb = new StringBuilder();
		
		stb.append(usuario.getNome());
		stb.append(" ");
		stb.append(usuario.getSobrenome());
		stb.append(" <");
		stb.append(usuario.getEmail());
		stb.append(">");
		return stb.toString();
	}
}
