FROM maven as build
WORKDIR /app
COPY . .
RUN mvn install

FROM openjdk:17
WORKDIR /app
ADD target/spring-mongo-docker.jar spring-mongo-docker.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","spring-mongo-docker.jar"]