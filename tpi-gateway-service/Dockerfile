# ——— STAGE 1: builder —————————————————————————————
FROM eclipse-temurin:24-jdk AS builder
RUN apt-get update && apt-get install -y maven

WORKDIR /workspace
COPY build.sh .
COPY tpi-gateway-service ./tpi-gateway-service

RUN chmod +x build.sh && ./build.sh gateway

# ——— STAGE 2: runtime —————————————————————————————
FROM eclipse-temurin:24-jdk
WORKDIR /app
VOLUME /tmp

COPY --from=builder \
     /workspace/tpi-gateway-service/target/*.jar \
     app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
