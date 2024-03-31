FROM maven:3.8.4-openjdk-17-slim AS build
COPY . /app
WORKDIR /app
RUN mvn clean package


FROM eclipse-temurin:17

LABEL author=codifacil.club

ENV DATABASE_URL jdbc:mysql://192.168.1.9:3306/codibdfacil
ENV DATABASE_USERNAME codi_user
ENV DATABASE_PASSWORD uA2uV6cM4CAmWTS
ENV DATABASE_PLATFORM org.hibernate.dialect.MySQL57Dialect
ENV DATABASE_DRIVER com.mysql.cj.jdbc.Driver

#Previamente realizar un mvn clean package
COPY --from=build /app/target/codifacil-backend-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

#docker build -t codifacil-backend-image:1 .
#docker run -p8081:8081 --name codifacil-backend-container codifacil-backend-image:1.3