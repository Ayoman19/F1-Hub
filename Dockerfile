# Build stage: JDK 25 + Maven installed via apt
FROM eclipse-temurin:25-jdk AS build
RUN apt-get update \
    && apt-get install -y --no-install-recommends maven \
    && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY pom.xml ./
RUN mvn -q dependency:go-offline
COPY src/ src/
RUN mvn -q clean package -DskipTests

# Runtime stage: lean JRE image with just the jar
FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /app/target/sehja-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/app.jar"]
