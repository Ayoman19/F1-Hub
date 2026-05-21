# Build stage: compile the app with Maven + JDK 25
FROM eclipse-temurin:25-jdk AS build
WORKDIR /app
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw && ./mvnw -q dependency:go-offline
COPY src/ src/
RUN ./mvnw -q clean package -DskipTests

# Runtime stage: lean image with just the JRE + jar
FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /app/target/sehja-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/app.jar"]
