FROM openjdk:8-jdk-alpine
MAINTAINER sarithacse2011@gmail.com
COPY build/libs/wallet-0.0.1-SNAPSHOT.jar wallet-1.0.0.jar
ENTRYPOINT ["java","-jar","/wallet-1.0.0.jar"]
