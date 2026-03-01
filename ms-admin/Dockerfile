# =========================
# BUILD STAGE
# =========================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /build

# copiar proyecto completo (multi-module)
COPY . .

# compilar SOLO ms-admin + dependencias locales
RUN mvn -f pom.xml clean package -pl ms-admin -am -DskipTests


# =========================
# RUNTIME STAGE
# =========================
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# copiar jar generado
COPY --from=build /build/ms-admin/target/*.jar app.jar

# copiar script DESDE BUILD STAGE (✅ correcto)
COPY --from=build /build/ms-admin/wait-for-mysql.sh wait-for-mysql.sh

# instalar netcat para esperar mysql
RUN apk add --no-cache netcat-openbsd

RUN chmod +x wait-for-mysql.sh

ENTRYPOINT ["sh","/app/wait-for-mysql.sh"]