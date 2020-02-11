FROM openjdk:8-alpine
LABEL maintainer=doutorking@gmail.com
ADD /build/libs/backend-module.jar app.jar
ENTRYPOINT ["sh", "-c", "java $JAVA_TOOL_OPTIONS -jar /app.jar"]