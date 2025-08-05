# ——— STAGE 1: builder —————————————————————————————
FROM eclipse-temurin:24-jdk AS builder
RUN apt-get update && apt-get install -y maven

WORKDIR /workspace
COPY build.sh .
COPY tpi-reportes-service ./tpi-reportes-service

RUN chmod +x build.sh && ./build.sh reportes

# ——— STAGE 2: runtime —————————————————————————————
FROM eclipse-temurin:24-jdk
WORKDIR /app
VOLUME /tmp

COPY --from=builder \
     /workspace/tpi-reportes-service/target/*.jar \
     app.jar

EXPOSE 8083
ENTRYPOINT ["java","-jar","app.jar"]
