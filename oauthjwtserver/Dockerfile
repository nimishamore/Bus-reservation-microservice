FROM adoptopenjdk/maven-openjdk11

COPY target/oauthjwtserver-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080:8080

RUN apt-get update
RUN apt-get install -y gcc
RUN apt-get install -y curl

ENTRYPOINT ["java","-jar","app.jar"]