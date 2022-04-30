FROM adoptopenjdk/openjdk11:alpine

WORKDIR /app
ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar app.jar"]