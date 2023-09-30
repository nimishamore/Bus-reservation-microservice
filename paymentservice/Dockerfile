FROM adoptopenjdk/maven-openjdk11

COPY target/paymentservice-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8025:8025

RUN apt-get update
RUN apt-get install -y gcc
RUN apt-get install -y curl

ENTRYPOINT ["java","-jar","app.jar"]