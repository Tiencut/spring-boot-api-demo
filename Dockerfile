# Dockerfile cho Spring Boot Application
FROM openjdk:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Maven wrapper và pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src src

# Build application
RUN ./mvnw clean package -DskipTests

# Copy JAR file
RUN cp target/*.jar app.jar

# Expose port
EXPOSE 8080

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]
