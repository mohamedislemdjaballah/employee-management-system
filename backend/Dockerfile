# ============================================================================
# Backend Spring Boot 3 - API REST EMS
# Build multi-étapes : compilation Maven puis image JRE légère
# ============================================================================

FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Mise en cache des dépendances Maven
COPY pom.xml .
RUN mvn -B -q dependency:go-offline

# Compilation de l'application
COPY src ./src
RUN mvn -B -q clean package -DskipTests

# ============================================================================

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Exécution avec un utilisateur non-root
RUN addgroup -S spring && adduser -S spring -G spring
COPY --from=build /app/target/*.jar app.jar
USER spring

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
