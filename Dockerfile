# Use OpenJDK 17 image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy everything to container
COPY . .

# Build the app using Maven Wrapper
RUN chmod +x ./mvnw && ./mvnw clean package -DskipTests

# Expose port 8080
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "$(find target -name '*.jar' | head -n 1)"]
