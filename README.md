# Spring Human Resources Application

## Use cases of the HR Application 

### For admin

* Add a new job to the system
* Update the information pertaining to a job
* Delete a job
* View a list of applications of all users
* View information pertaining to an application
* Delete an application


### For user

* Apply for a job
* Upload a resume (just pdf and max 1mb)
* View a list of their applications
* Update the information pertaining to their applications
* Delete their applications

### For both

* Search for a job
* Update the information pertaining to their profile

## Project Management
I used Trello for project management.
    
<img width="1042" alt="trello-ss" src="https://user-images.githubusercontent.com/53643180/77248923-65d61800-6c4e-11ea-88a1-a7a3725f747d.png">

## Running hr-app locally
HR App is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/). You can build a jar file and run it from the command line:


```
git clone https://github.com/enesoral/HR-App
cd HR-App

HR-App> mvn clean install -U

[INFO]
[INFO] simple-hr .......................................... SUCCESS [  2.104 s]
[INFO] hr-data ............................................ SUCCESS [ 18.940 s]
[INFO] hr-web ............................................. SUCCESS [ 30.228 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------


HR-App\hr-web> mvn spring-boot:run

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.2.4.RELEASE)

```

You can then access hr-app here: http://localhost:8080/

<img width="1042" alt="hrapp-ss1" src="https://user-images.githubusercontent.com/53643180/77248401-2e656c80-6c4a-11ea-86db-5c347f960c91.png">

<img width="1042" alt="hrapp-ss2" src="https://user-images.githubusercontent.com/53643180/77248403-2f969980-6c4a-11ea-87e2-02785c67bc5c.png">

## Working with HR App in your IDE

### Prerequisites
The following items should be installed in your system:
* Java 8 or newer.
* Your preferred IDE.

## Database configuration

In its default configuration, HR App uses an in-memory database (H2) which
gets populated at startup with data. The h2 console is automatically exposed at `http://localhost:8080/h2-console`
and it is possible to inspect the content of the database using the `jdbc:h2:mem:testdb` url.
 
You can take a look at [here](https://spring.io/guides/gs/accessing-data-mysql/) for MySql connection.

## In case you find a bug/suggested improvement for HR App
The [issue tracker](https://github.com/enesoral/HR-App/issues) is the preferred channel for bug reports, features requests and submitting pull requests.
