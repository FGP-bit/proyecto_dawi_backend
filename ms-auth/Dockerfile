FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /build

COPY . .

RUN mvn -f pom.xml clean package -pl ms-auth -am -DskipTests


FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=build /build/ms-auth/target/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java","-jar","app.jar"]