#Below command will build the docker image of the application.

FROM maven:latest as build
LABEL com.TheSpiritMan.image.authors="TheSpiritMan"
WORKDIR /opt
COPY . .
RUN mvn  install

FROM openjdk:17-alpine 
LABEL com.TheSpiritMan.image.authors="TheSpiritMan"
WORKDIR /deploy
COPY --from=build /opt/target/FirstCRUDApplication.jar .
EXPOSE 5555
ENTRYPOINT ["java","-jar","/deploy/FirstCRUDApplication.jar"]