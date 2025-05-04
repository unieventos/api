package br.com.unisagrado.Unisagrado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class UniEventosApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniEventosApplication.class, args);
	}

}
