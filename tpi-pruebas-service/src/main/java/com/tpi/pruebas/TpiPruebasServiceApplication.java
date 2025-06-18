package com.tpi.pruebas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableFeignClients
public class TpiPruebasServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpiPruebasServiceApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void mostrarMensajeInicio() {
		System.out.println("\n 🧪 Microservicio PRUEBAS corriendo en el puerto 8085 \n");
	}
}
