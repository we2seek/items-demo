### MySQL container
```
docker run \
-p 3306:3306 \
-e MYSQL_ROOT_PASSWORD=my-secret-pw \
-e MYSQL_ROOT_HOST=% \
-e MYSQL_DATABASE=test \
-v /home/DN151084TVA/projects/udemy/docker-for-java-developers/items-demo/src/main/resources/sql/schema-mysql.sql:/docker-entrypoint-initdb.d/a.sql \
-v /home/DN151084TVA/projects/udemy/docker-for-java-developers/items-demo/src/main/resources/sql/data-mysql.sql:/docker-entrypoint-initdb.d/b.sql \
--name some-mysql \
--detach mysql \
--init-connect="GRANT CREATE USER ON *.* TO 'root'@'%'; FLUSH PRIVILEGES;"
```
### build image
```
docker build -t items-demo-docker .
```

### run container
```
docker run --detach -p 8080:8080 items-demo-docker
```