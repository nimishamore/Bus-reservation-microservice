FROM adoptopenjdk/maven-openjdk11

COPY target/userservice.jar app.jar

EXPOSE 9091:9091

RUN apt-get update
RUN apt-get install -y gcc
RUN apt-get install -y curl

ENTRYPOINT ["java","-jar","app.jar"]