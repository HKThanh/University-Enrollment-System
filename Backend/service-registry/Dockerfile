FROM gradle:8.12.1-jdk17-alpine AS build
WORKDIR /app
COPY . /app
RUN gradle build --no-daemon

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/service-registry-0.0.1-SNAPSHOT.jar service-registry-0.0.1-SNAPSHOT.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "service-registry-0.0.1-SNAPSHOT.jar"]