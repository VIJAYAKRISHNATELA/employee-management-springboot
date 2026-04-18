FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /app
COPY demo/mvnw .
COPY demo/.mvn .mvn
COPY demo/pom.xml .
COPY demo/src src
RUN chmod +x mvnw && ./mvnw package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]