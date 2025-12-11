# --- Stage 1: Build the Application ---
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
# Build the JAR file, skipping tests to speed it up
RUN mvn clean package -DskipTests

# --- Stage 2: Run the Application ---
FROM openjdk:17-jdk-slim
WORKDIR /app
# Copy the JAR from the build stage to the run stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080 (Standard for Spring Boot)
EXPOSE 8080

# Command to run the app
ENTRYPOINT ["java", "-jar", "app.jar"]