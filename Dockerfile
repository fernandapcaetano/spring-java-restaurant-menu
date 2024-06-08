FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN  mvn clean package -DskipTests

FROM openjdk:17.0.1-jdkslin
COPY --from=build /target/restaurante-0.0.1-SNAPSHOT.war restaurante.war
EXPOSE 8080
ENTRYPOINT [ "java","-war","restaurante.war"]
