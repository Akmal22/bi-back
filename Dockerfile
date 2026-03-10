# ===== STAGE 1: Build (Gradle) =====
FROM eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /app

# Copy Gradle wrapper & build files first (to leverage Docker cache)
COPY gradlew settings.gradle.kts ./
COPY gradle ./gradle
# If you use Kotlin DSL:
# COPY settings.gradle.kts build.gradle.kts ./

# Copy build script(s)
COPY build.gradle.kts ./
# or, if you use Kotlin DSL:
# COPY build.gradle.kts ./

# Download dependencies (will be cached in a Docker layer)
RUN ./gradlew --no-daemon dependencies

# Now copy the source code
COPY src ./src

# Build fat JAR with Spring Boot
RUN ./gradlew --no-daemon -x test bootJar


# ===== STAGE 2: Runtime (JRE only, lightweight) =====
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy only the final JAR from build stage
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

# Optional: add JVM options here if needed
# ENV JAVA_TOOL_OPTIONS="-Xms256m -Xmx512m"

ENTRYPOINT ["java", "-jar", "app.jar"]