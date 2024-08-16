FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
FROM openjdk:17-jdk-slim
RUN mvn clean package -DskipTests
WORKDIR /app
COPY --from=build /app/target/api-0.0.1-SNAPSHOT.jar /app/api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "api.jar"]