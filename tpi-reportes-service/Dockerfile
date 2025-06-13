FROM eclipse-temurin:24-jdk

VOLUME /tmp
ARG JAR_FILE=target/tpi-reportes-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8083
