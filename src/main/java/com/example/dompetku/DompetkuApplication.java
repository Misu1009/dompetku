package com.example.dompetku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.dompetku"})
public class DompetkuApplication {

	public static void main(String[] args) {
		SpringApplication.run(DompetkuApplication.class, args);
	}

}
