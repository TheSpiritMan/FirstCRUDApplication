# First CRUD Application

# Information
- This project is my first CRUD based web application. 
- It is created in Java Programming Language using Spring Boot Framework.

# Docker
- The Dockerfile will only build the docker image of the application.
- Command to build the image is:
```
docker build -t $IMAGE_NAME -f Dockerfile .
```
- Remember to change the $IMAGE_NAME tag with the desire name of the image.

# Docker Hub
- Docker Hub is a popular and public registry for the docker images.
- Docker image of this project can be found in Docker Hub under my account.
- Link to the image of this project in Docker Hub:
 [https://hub.docker.com/r/thespiritman/firstcrudapplication](https://hub.docker.com/r/thespiritman/firstcrudapplication)
# Setup MySQL
- Since this application is using MySQL as a database.So we also need to set it up.
- We will use MySQL docker image.
- Command to pull MySQL:
```
docker pull mysql
```
- Now run mysql in docker container.Command to run:
```
docker run -d -p 6033:3306 --name=mysql_docker --env="MYSQL_ROOT_PASSWORD=root" --env="MYSQL_PASSWORD=root" --env="MYSQL_DATABASE=FirstCRUDApp" mysql
```
- Using `-d` flag will run the container in detached mode.
- Using `-p` flag will publish the port of the container.
- Using `--name` flag will assign custom_name to a container.
- Using `--env` flag will set the environment to run the container.
- Here `root` user is accessing the mysql server using passwd `root`. And working in database named as `FirstCRUDApp`.
- Remember the username and passwd of mysql must match to that set in `/src/main/resources/application.properties`.
- Since the container is running in detached mode. Command to list the running container is:
```
docker container ls 
```
- Now let's try logging into container. Command to get shell of `mysql_docker` container.
```
docker exec -it mysql_docker bash
```
- Using `-it` flag will set the container in interactive and tty mode.

# Import custom data
- We can import our data into database.
- Command to import:
```
docker exec -i mysql_docker mysql -uroot -proot FirstCRUDApp < users_data.sql
```
- Get bash of the container with above same command and check inside the FirstCRUDApp database.

# Run and Link Our Project with mysql_docker container
- Command to link: 
```
docker run -t --link mysql_docker:mysql -p 5555:5555 thespiritman/firstcrudapplication:latest
```
- Using `--link` flag will link the container of our project with the container of mysql named as `mysql_docker`.
- Since our project is initially running in port 5555. So using `-p` flag will forward the port to 8080 in our machine.
- Now, visit the link `http://localhost:5555` in your browser. If everything goes well, the webpage must load.
