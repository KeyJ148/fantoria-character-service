FROM gradle:8.14-jdk21 AS build
WORKDIR /app
COPY build.gradle.kts docker.gradle.kts settings.gradle.kts gradlew ./
COPY gradle gradle/
COPY src src/
RUN gradle bootJar --no-daemon

FROM eclipse-temurin:21-jdk-alpine
EXPOSE 8080/tcp
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY --from=build /app/build/libs/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]