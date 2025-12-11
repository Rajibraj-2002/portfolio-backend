FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
# Build the JAR file, skipping tests to speed it up
RUN mvn clean package -DskipTests

# --- Stage 2: Run the Application ---
# Using a lightweight Java 21 Runtime to run the app
FROM eclipse-temurin:21-jre
WORKDIR /app
# Copy the JAR from the build stage to the run stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Command to run the app
ENTRYPOINT ["java", "-jar", "app.jar"]