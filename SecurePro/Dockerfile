
# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the application JAR file into the container
COPY target/SecurePro-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that your application runs on
EXPOSE ${APP_PORT}

# Run the application
ENTRYPOINT ["java", "-jar", ,"-Dserver.port=${APP_PORT}", "app.jar"]
