FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package

FROM eclipse-temurin:21-jre AS runner
WORKDIR /app
COPY --from=builder ./app/target/posts-service-0.0.1-SNAPSHOT.jar ./app.jar

ARG AWS_ACCESS_KEY_ID
ARG AWS_SECRET_ACCESS_KEY

EXPOSE 4001

ENTRYPOINT ["java", "-jar", "app.jar"]