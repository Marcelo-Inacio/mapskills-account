FROM openjdk:8-jdk-alpine
RUN apk update && apk add --no-cache bash
ADD target/account-1.1.0.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]