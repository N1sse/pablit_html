# Stage 1: Build the application with Maven + JDK
FROM maven:3.9.4-eclipse-temurin-21 AS builder

WORKDIR /app

# Copiamos sólo el pom.xml para aprovechar caché de dependencias
COPY pom.xml .

# Copiamos el código fuente
COPY src ./src

# Compilamos y generamos el JAR (sin ejecutar tests para agilizar)
RUN mvn clean package -DskipTests

# Stage 2: Ejecutar la aplicación con JRE
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copiamos el JAR generado en el build previo
COPY --from=builder /app/target/*jar app.jar

# Exponemos el puerto 8080
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]