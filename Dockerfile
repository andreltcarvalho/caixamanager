FROM amazoncorretto:21.0.4-alpine3.18
VOLUME /tmp
ARG JAR_FILE=target/caixamanager-1.0.0.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
