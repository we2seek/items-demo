FROM alpine
RUN apk add --no-cache java-cacerts openjdk17-jdk
VOLUME /tmp
#ADD ./target/items-demo-0.0.1-SNAPSHOT.jar myapp.jar
ADD maven/items-demo-0.0.1-SNAPSHOT.jar myapp.jar
RUN sh -c 'touch /myapp.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/myapp.jar"]