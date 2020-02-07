# todo https://blog.xebialabs.com/2017/05/25/multi-stage-docker-image-build-for-java-applications/
#FROM gradle:4.2.1-jdk8-alpine as build
#WORKDIR /app
#ADD --chown=gradle:gradle . /app
#RUN ./gradlew build --stacktrace

FROM openjdk:8-alpine
LABEL maintainer=doutorking@gmail.com
ADD /build/libs/backend-module.jar app.jar
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app.jar"]