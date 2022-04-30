#Below command will build the docker image of the application.
FROM openjdk:11
MAINTAINER TheSpiritMan
WORKDIR /opt
ADD target/FirstCRUDApplication.jar FirstCRUDApp.jar
EXPOSE 5555
ENTRYPOINT ["java","-jar","FirstCRUDApp.jar"]