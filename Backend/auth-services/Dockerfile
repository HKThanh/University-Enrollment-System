FROM gradle:8.12.1-jdk17-alpine AS build
WORKDIR /app
COPY . /app
RUN gradle build -x test

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/auth-services-0.0.1-SNAPSHOT.jar auth-services-0.0.1-SNAPSHOT.jar
EXPOSE 9004
ENTRYPOINT ["java", "-jar", "auth-services-0.0.1-SNAPSHOT.jar"]