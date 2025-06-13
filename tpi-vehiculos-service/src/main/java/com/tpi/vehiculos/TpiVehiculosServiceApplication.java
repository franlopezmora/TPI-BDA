package com.tpi.vehiculos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class TpiVehiculosServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpiVehiculosServiceApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void logStartup() {
		System.out.println("\n 🚗 Microservicio VEHICULOS corriendo en el puerto 8082 \n");
	}
}

