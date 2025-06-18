package com.tpi.notificaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class TpiNotificacionesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpiNotificacionesServiceApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void logStartup() {
		System.out.println("\n 📣 Microservicio NOTIFICACIONES corriendo en el puerto 8084 \n");
	}
}
