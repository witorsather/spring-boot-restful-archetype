package com.exemplo.listadetarefasspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Main class.
 * 
 * @author condessalovelace
 *
 */
@SpringBootApplication(scanBasePackages = "br.*")
public class ServicoRestfulApplication {
	private static ConfigurableApplicationContext ctx;

	public static void main(String[] args) {
		ctx = SpringApplication.run(ServicoRestfulApplication.class, args);
	}

	public static void shutDown() {
		ctx.close();
	}
}
