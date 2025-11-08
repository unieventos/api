# Estágio de build
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Estágio final
FROM openjdk:26-ea-17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/Unisagrado-2.00.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]