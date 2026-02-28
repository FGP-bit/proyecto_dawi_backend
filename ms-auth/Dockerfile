# ===============================
# STAGE 1 - BUILD
# ===============================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /build

# copiar proyecto completo (multi-module)
COPY . .

# compilar SOLO ms-auth y dependencias
RUN mvn clean package -pl ms-auth -am -DskipTests


# ===============================
# STAGE 2 - RUNTIME
# ===============================
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=build /build/ms-auth/target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]