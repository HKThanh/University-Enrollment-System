FROM gradle:8.12.1-jdk17-alpine AS build
WORKDIR /app
COPY . /app
RUN gradle build -x test

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/schedule-service-0.0.1-SNAPSHOT.jar schedule-service-0.0.1-SNAPSHOT.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "schedule-service-0.0.1-SNAPSHOT.jar"]