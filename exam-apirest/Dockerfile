FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/apirest-exam-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]   

#  java -jar app.jar

