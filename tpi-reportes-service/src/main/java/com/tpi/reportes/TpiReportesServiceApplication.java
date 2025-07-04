package com.tpi.reportes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.tpi.reportes.clients")
public class TpiReportesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpiReportesServiceApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void logStartup() {
		System.out.println("\n \uD83D\uDCCA Microservicio REPORTES corriendo en el puerto 8083 \n");
	}
}