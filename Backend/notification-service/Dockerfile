FROM gradle:8.12.1-jdk17-alpine AS build
WORKDIR /app
COPY . /app
RUN gradle build -x test

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/notification-service-0.0.1-SNAPSHOT.jar notification-service-0.0.1-SNAPSHOT.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "notification-service-0.0.1-SNAPSHOT.jar"]