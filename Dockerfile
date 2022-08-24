FROM java:8

WORKDIR /app

COPY target/Maven-1.0-SNAPSHOT.jar  /app/Maven-1.0-SNAPSHOT.jar
COPY src/main/resources/db.properties  /app/src/main/resources/db.properties

CMD ["java", "-jar", "Maven-1.0-SNAPSHOT.jar"]


#creat an image of java app w/ command

# then create a container that connect to the networkk of the dababase container
#   docker run --name linkcontainer --link postgres-db myApp

#   docker build -t urbooks .
#  docker run --name urbooks(name of this container) --link 180808bc3693(is the container id of postgresql) -it  urbooks(image name when using the build .)

# src/main/resources/db.properties

# to restart a stoped container with interactive mode
#  docker start -ai urbooks

#  docker start -ai urbooks2