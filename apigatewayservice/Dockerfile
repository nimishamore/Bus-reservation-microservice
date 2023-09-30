FROM adoptopenjdk/maven-openjdk11

COPY target/apigateway-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9193:9193

RUN apt-get update
RUN apt-get install -y gcc
RUN apt-get install -y curl

ENTRYPOINT ["java","-jar","app.jar"]