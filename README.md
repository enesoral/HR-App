# Spring Recipe Sample Application [![Build Status](https://circleci.com/gh/enesoral/Recipe-App.svg?style=svg)](https://circleci.com/gh/enesoral/Recipe-App)

## Use cases of the Recipe Application 

* View a list of recipe names and their difficulties
* View information pertaining to a recipe
* Update the information pertaining to a recipe
* Delete the information pertaining to a recipe
* Add a new recipe to the system

## Running recipeapp locally
Recipe App is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/). You can run Recipe App from the command line:


```
git clone https://github.com/enesoral/Recipe-App
cd Recipe-App
./mvnw spring-boot:run
```

You can then access recipeapp here: http://localhost:8080/

<img width="1042" alt="recipeapp-ss1" src="https://user-images.githubusercontent.com/53643180/77230456-5cdd3c00-6ba5-11ea-9180-e1c7e325fa7f.png">

<img width="1042" alt="recipeapp-ss2" src="https://user-images.githubusercontent.com/53643180/77230458-5ea6ff80-6ba5-11ea-9324-7a49def930c7.png">

## Working with Recipe App in your IDE

### Prerequisites
The following items should be installed in your system:
* Java 8 or newer.
* Your preferred IDE.

## Database configuration

In its default configuration, Recipe App uses an in-memory database (H2) which
gets populated at startup with data. The h2 console is automatically exposed at `http://localhost:8080/h2-console`
and it is possible to inspect the content of the database using the `jdbc:h2:mem:testdb` url.
 
You can take a look at [here](https://spring.io/guides/gs/accessing-data-mysql/) for MySql connection.

## In case you find a bug/suggested improvement for Recipe App
The [issue tracker](https://github.com/enesoral/Recipe-App/issues) is the preferred channel for bug reports, features requests and submitting pull requests.

