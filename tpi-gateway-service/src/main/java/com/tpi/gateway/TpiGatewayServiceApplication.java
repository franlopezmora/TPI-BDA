package com.tpi.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class TpiGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpiGatewayServiceApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void logStartup() {
		System.out.println("\n 🌐 API GATEWAY corriendo en el puerto 8080 \n");
	}
}
