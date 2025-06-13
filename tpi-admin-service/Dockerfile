FROM eclipse-temurin:24-jdk

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el JAR generado
COPY target/tpi-admin-service-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto que usa Spring Boot (ajustar si es otro)
EXPOSE 8081

# Comando para ejecutar el microservicio
ENTRYPOINT ["java", "-jar", "app.jar"]
