# Use a lightweight Java 17 (or 21 depending on your version) runtime image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper files and pom.xml first to cache dependencies
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Download dependencies (this caches them so builds are faster)
RUN ./mvnw dependency:go-offline

# Copy the rest of your source code
COPY src ./src

# Build the application JAR file, skipping tests to speed up deployment
RUN ./mvnw clean package -DskipTests

# Find the built jar file and rename it to app.jar
RUN mv target/*.jar app.jar

# Render assigns a dynamic port via environment variables. 
# Spring Boot will automatically pick up the 'PORT' environment variable.
ENV PORT=8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]