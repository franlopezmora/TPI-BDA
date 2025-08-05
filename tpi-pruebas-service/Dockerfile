# ——— STAGE 1: builder —————————————————————————————
FROM eclipse-temurin:24-jdk AS builder

# Instalamos Maven
RUN apt-get update && apt-get install -y maven

WORKDIR /workspace

# Copiamos el script y la carpeta de pruebas
COPY build.sh .
COPY tpi-pruebas-service ./tpi-pruebas-service

# Ejecutamos solo el build de pruebas con JDK 24
RUN chmod +x build.sh && ./build.sh pruebas

# ——— STAGE 2: runtime —————————————————————————————
FROM eclipse-temurin:24-jdk
WORKDIR /app

# Para Spring Boot temp files
VOLUME /tmp

# Copiamos el JAR compilado
COPY --from=builder \
     /workspace/tpi-pruebas-service/target/*.jar \
     app.jar

EXPOSE 8085
ENTRYPOINT ["java","-jar","app.jar"]
