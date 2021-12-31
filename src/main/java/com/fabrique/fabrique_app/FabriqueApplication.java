package com.fabrique.fabrique_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FabriqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(FabriqueApplication.class, args);
	}
}
