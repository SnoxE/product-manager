# -------- BUILD STAGE --------
FROM gradle:8.5.0-jdk21-alpine AS build
WORKDIR /app

COPY . .
RUN gradle build -x spotlessJavaCheck --no-daemon

# -------- RUNTIME STAGE --------
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]