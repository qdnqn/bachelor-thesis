FROM openjdk:11-jre

COPY maven/${project.build.finalName}.${project.packaging} /usr/src/rest/rest.jar
WORKDIR /usr/src/rest

ENTRYPOINT ["java", "-jar", "rest.jar"]