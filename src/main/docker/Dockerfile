FROM alpine
RUN apk add --no-cache java-cacerts openjdk17-jdk
VOLUME /tmp
ADD maven/${project.build.finalName}.jar myapp.jar
RUN sh -c 'touch /myapp.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/myapp.jar"]