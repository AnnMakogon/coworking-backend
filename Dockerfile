FROM openjdk:19-jdk-slim

WORKDIR /app

COPY build/libs/coworking-0.0.1-SNAPSHOT.jar app.jar

RUN java -version

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/Coworking
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=1234

ENTRYPOINT ["java", "-jar", "app.jar"]