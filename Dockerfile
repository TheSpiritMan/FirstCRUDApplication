#Below command will build the docker image of the application.
# stage 1
FROM ubuntu:latest as build
MAINTAINER TheSpiritMan
WORKDIR /opt
COPY . .
RUN apt update
RUN apt install maven -y
RUN mvn  install

# stage 2
FROM openjdk:11
COPY --from=build /opt/target/FirstCRUDApplication.jar FirstCRUDApp.jar
EXPOSE 5555
ENTRYPOINT ["java","-jar","FirstCRUDApp.jar"]
