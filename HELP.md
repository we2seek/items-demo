### Run application from IntelliJ (dev mode)

1. Start MySQL in docker container with one of the following way:
```
docker run \
-p 3306:3306 \
-e MYSQL_ROOT_PASSWORD=my-secret-pw \
-e MYSQL_ROOT_HOST=% \
-e MYSQL_DATABASE=test \
-v /home/DN151084TVA/projects/udemy/docker-for-java-developers/items-demo/src/main/resources/sql/schema-mysql.sql:/docker-entrypoint-initdb.d/a.sql \
-v /home/DN151084TVA/projects/udemy/docker-for-java-developers/items-demo/src/main/resources/sql/data-mysql.sql:/docker-entrypoint-initdb.d/b.sql \
--name mysqldb \
--detach mysql \
--init-connect="GRANT CREATE USER ON *.* TO 'root'@'%'; FLUSH PRIVILEGES;"
```
```
docker run \
-p 3306:3306 \
-e MYSQL_ALLOW_EMPTY_PASSWORD=yes \
-e MYSQL_DATABASE=test \
--name mysqldb \
--detach mysql
```
2. Start spring-boot application from IntelliJ

### Build/Run docker image with application
```
mvn clean package docker:build
mvn docker:start
mvn docker:stop
```

### Run application using docker-compose
```
docker-compose -f ./src/main/docker/docker-compose.yml up -d
docker-compose -f ./src/main/docker/docker-compose.yml down
```