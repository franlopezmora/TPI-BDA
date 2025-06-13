FROM eclipse-temurin:24-jdk


# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el JAR al contenedor
COPY target/tpi-vehiculos-service-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto del microservicio
EXPOSE 8082

# Comando para correr la app
ENTRYPOINT ["java", "-jar", "app.jar"]
