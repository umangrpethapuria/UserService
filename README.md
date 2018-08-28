# User Service

Simple REST service with an ability to save user information

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [MYSQL](https://www.mysql.com/downloads/)

## Database configuration
1) Open src/main/resources/application.properties
2) Setup database name, host, port, username and password for MYSQL database

## Build and package the application
```shell
mvn clean package
```

## Execute application

There are several ways to run a Spring Boot application on your local machine.

1) One way is to execute the `main` method in the `com.codingtest.Application` class from your IDE.

2) Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

3) Run the executable jar
```shell
java -jar target/UserService-1.0-SNAPSHOT.jar
```

## Swagger
The REST service can be accessed through Swagger API at http://<host>:<port>/swagger-ui.html
e.g. http://localhost:8080/swagger-ui.html