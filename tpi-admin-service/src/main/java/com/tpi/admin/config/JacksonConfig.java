package com.tpi.admin.config;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public JavaTimeModule javaTimeModule() {
        return new JavaTimeModule();
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
        return builder -> {
            // 1) instalar el módulo de java8 dates
            builder.modulesToInstall(JavaTimeModule.class);

            // 2) escribir fechas como ISO strings, no como timestamps
            builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            // 3) quitar el REQUIRE_HANDLERS_FOR_JAVA8_TIMES para que no falle
            builder.featuresToDisable(MapperFeature.REQUIRE_HANDLERS_FOR_JAVA8_TIMES);
        };
    }
}
