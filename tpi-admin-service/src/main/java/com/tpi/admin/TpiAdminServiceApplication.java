package com.tpi.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

@EnableFeignClients
@SpringBootApplication
public class TpiAdminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpiAdminServiceApplication.class, args);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void logStartup() {
        System.out.println("\n 🧑‍💼 Microservicio ADMIN corriendo en el puerto 8081 \n");
    }

}
