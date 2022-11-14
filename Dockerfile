#Below command will build the docker image of the application.

FROM ubuntu:latest
LABEL com.TheSpiritMan.image.authors="TheSpiritMan"
WORKDIR /opt
COPY . .
RUN apt update
RUN apt install maven -y
RUN mvn  install
EXPOSE 5555
ENTRYPOINT ["java","-jar","/opt/target/FirstCRUDApplication.jar"]