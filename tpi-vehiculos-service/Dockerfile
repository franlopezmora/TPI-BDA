# ——— STAGE 1: builder —————————————————————————————
FROM eclipse-temurin:24-jdk AS builder
RUN apt-get update && apt-get install -y maven

WORKDIR /workspace
COPY build.sh .
COPY tpi-vehiculos-service ./tpi-vehiculos-service

RUN chmod +x build.sh && ./build.sh vehiculos

# ——— STAGE 2: runtime —————————————————————————————
FROM eclipse-temurin:24-jdk
WORKDIR /app
VOLUME /tmp

COPY --from=builder \
     /workspace/tpi-vehiculos-service/target/*.jar \
     app.jar

EXPOSE 8082
ENTRYPOINT ["java","-jar","app.jar"]
