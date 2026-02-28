# =========================
# BUILD STAGE
# =========================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /build

# copiar TODO el proyecto multi-modulo
COPY . .

# compilar SOLO ms-admin pero incluyendo dependencias locales
RUN mvn clean package -DskipTests -pl ms-admin -am


# =========================
# RUNTIME STAGE
# =========================
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=build /build/ms-admin/target/*.jar app.jar
COPY ms-admin/wait-for-mysql.sh wait-for-mysql.sh

RUN apk add --no-cache netcat-openbsd
RUN chmod +x wait-for-mysql.sh

ENTRYPOINT ["sh","wait-for-mysql.sh"]