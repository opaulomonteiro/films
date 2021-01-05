# Films &middot; [![Build Status](https://travis-ci.com/opaulomonteiro/films.svg?branch=main)](https://travis-ci.com/opaulomonteiro/films)

This is a pet project that used Spring Boot, Gradle, Java and H2 for in memory database

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Gradle 6.7](https://gradle.org/install/)

### Running the application locally

To run the project, you must run the main FilmsApplication class, doing this the application will start on your machine at port 8080 and will also automatically load the film base. 

To run the query you can run the following command on your terminal or Postman

curl --location --request GET 'http: // localhost: 8080 / producers / awards'

### Run Tests

To run the tests you can run via IDEA by running the ProducerControllerTest and FilmsServiceTest classes. If you do not want to run through IDEA you can execute the following command in the terminal in the project folder './gradlew clean build' which will build the applications and run the tests automatically as well.

