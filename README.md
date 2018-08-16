# storemanagementrest
Small snack store management REST API using Spring Data REST and h2 in memory database.
The API receives request under specific URLs, retrieving data from the database and performing actions associated with each
URL.
Database is generated using an h2 memory database, so you don't need any script in order to be able to load the schema nor the data.

## Getting Started

To get project for testing and developing purposes just perform:

```
git clone https://github.com/LuisMario01/storemanagementrest.git
```

or download as zip file. To get a WAR file, contact author.

After dowloading the project in the desired location, Spring STS is recommended to open it (if you want to keep developing it).
To import it from Spring STS, go to File > Open Projects From File System and browse the location where git/zip was put under.

### Prerequisites
Tools required to develop or test the API:

```
Java 8
Pivotal tC Server Developer Edition v4.0.
Postman
Store-DataRest-API.json attached to test requests.
Maven 3 
GNU/Linux is recommended to run project.
```

## Installing and usage

### With an IDE
In Spring STS/Eclipse, select File>Import project from filesystem, browse the directory where the project was downloaded and 
import it to your workspace.
Select Run>Run, select Pivotal Server and the project will be automatically deployed.
Import [JSON file for local test](Requests-Locale.json) to your Postman, by selecting option Import>From file (browsing the location where the project is located).

Once project is deployed and database is running, test it with Postman and the collections provided before.

### As a Springboot/Maven standalone project
For more information, please refer to [SpringBoot official documentation](https://spring.io/guides/gs/spring-boot/).
Reach the location in your system where you have downloaded the repository content. Go to storemanagementrest (it is the root of the project), make sure you are in the same level as the pom.xml file, and then type:

```
mvn spring-boot:run
```
This command will start the application and you should see the initializing process coming in your screen. Remember, to use this, mvn must be available in your PATH. The last thing you should be seeing are the test queries for the basic structure of the database, performed by hibernate.
After the application has finished loading on to the server, you can start testing it with the request collection provided.

## Test it online
You can also check an online version containing exactly the same project and hosted using Heroku cloud service for Spring Boot in
https://storemanagementrest.herokuapp.com/store/
[Requests](Requests-Remote.json) can be done to this usign the Request set for remote service. 

## Developed with

* [Spring STS](https://spring.io/tools) - IDE used.
* [Spring Data Rest](https://docs.spring.io/spring-data/rest/docs/current/reference/html/) - Restful framework for endpoint exposure.
* [SpringBoot](https://spring.io/projects/spring-boot) - Web framework used.
* [Maven](https://maven.apache.org/) - Dependency Management.
* [H2 database](http://www.h2database.com/html/main.html) - Database engine.
* [Postman](https://www.getpostman.com/) - Used to test the API
* [EclEmma](https://www.eclemma.org/) - Code coverage tool as an extension to Spring STS.
* [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/) - Framework used by Spring Rest to perform database actions.
* [Spring Security](https://spring.io/projects/spring-security) - Used for authentication and authorization over the enpoints.
* [Heroku](https://storemanagementrest.herokuapp.com/store/) - Hosting service.
* [Hibernate Envers](http://hibernate.org/orm/envers/) - ORM and entity auditing. 


### Spring MVC
Spring Data Rest integrated with Spring MVC gives flexibility when working with specialized queries that don't exactly match with a
method provided by repositories. In this project, a controller is used to perform only 3 activities required by the API.
### Spring Data JPA
Spring Data JPA provides a set of well-defined interfaces and methods that allow an easy manipulation of the data layer. A repository
is created for every entity that needs to be persisted, in this case, all of the entities. 
### Spring Data Rest
Built on top of JPA, Data Rest is used to expose the endpoints the API has available. Most of the listing methods  and searching
features are mapped using Spring Data Rest de-facto URL mapping, 
### Spring Security
Spring Security blends seamlessly with Spring Data Rest for method exposure. Basic Authorization is used, with a username and password 
(which is encoded) to allow role-based endpoint visualization.
### Hibernate Envers
Envers allows for an easy auditing framework built on top of Spring JPA, making possible monitorizing changes done over an entity,
saving each change associated with a specific version/state of the entity. 
### Criteria API
Provides with an application-level query construction that allows sorting methods to be perfomed with Java (and not with the database) 
in order to expose a like-based sorting.

## Authors

* **Luis De Paz** - *Initial work* - [storemanagementrest](https://github.com/LuisMario01/storemanagementrest.git)

## License

This project is licensed under the GPL v3.0. License - see the [LICENSE.md](LICENSE.md) file for details


