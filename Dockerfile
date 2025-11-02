# Use Maven with JDK 17
FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app

# Copy all project files
COPY . .

# Grant permission to mvnw
RUN chmod +x ./mvnw

# Build the app
RUN ./mvnw clean package -DskipTests

# Copy jar to a fixed name
RUN cp target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "app.jar"]
