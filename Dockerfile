FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/updateChesker-2.2.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE $PORT
CMD ["/usr/bin/java","-Xmx700m", "-jar", "app.jar"]