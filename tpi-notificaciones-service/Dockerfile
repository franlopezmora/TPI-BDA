# ——— STAGE 1: builder —————————————————————————————
FROM eclipse-temurin:24-jdk AS builder
RUN apt-get update && apt-get install -y maven

WORKDIR /workspace
COPY build.sh .
COPY tpi-notificaciones-service ./tpi-notificaciones-service

RUN chmod +x build.sh && ./build.sh notificaciones

# ——— STAGE 2: runtime —————————————————————————————
FROM eclipse-temurin:24-jdk
WORKDIR /app
VOLUME /tmp

COPY --from=builder \
     /workspace/tpi-notificaciones-service/target/*.jar \
     app.jar

EXPOSE 8084
ENTRYPOINT ["java","-jar","app.jar"]
