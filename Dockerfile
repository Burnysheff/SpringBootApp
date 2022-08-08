FROM openjdk:18
LABEL maintainer="demoApp"
ADD target/demo-0.0.1-SNAPSHOT.jar spring-boot-docker.jar
ENTRYPOINT ["java", "-jar", "spring-boot-docker.jar"]