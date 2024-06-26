# Building
FROM maven:3.9.0-amazoncorretto-17 as builder

COPY /src /home/app/src
COPY /pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip=true

EXPOSE 8080

CMD ["bash", "-c", "java -jar /home/app/target/ProyectoIntegrador-0.0.1-SNAPSHOT.jar"]
