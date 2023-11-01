# About

Spring-boot REST microservice based hexagonal architecture project.

## Requirements

- JDK 17.
- Maven 3.6.3

## Technologies

### Common

- Hexagonal architecture
- [Lombok](https://projectlombok.org/)
- [MapStruct](https://mapstruct.org/) 
- [Spring-boot](https://quarkus.io/)
- [Resilience4j](https://resilience4j.readme.io/docs)

### H2 adapter

- [H2Database](https://www.h2database.com/html/main.html) 
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Flyway](https://flywaydb.org/)

### REST adapter

- [springdoc-openapi-ui](https://springdoc.org/)
- [openapi-generator](https://github.com/OpenAPITools/openapi-generator)

### Integration test
- [rest-assured](https://rest-assured.io/)
- [cucumber](https://cucumber.io/)


## How to execute the application

Go to the project root directory and execute the following command to compile, test, package and install the different artifacts in your local maven repository.

```shell
mvn clean install
```

After creating all artifacts you can run the project with the following command:

```shell
mvn spring-boot:run -pl bootloader
```

If application started correctly will provide the following end points:

- `http://localhost:8081/hexagonal-chain-prices/search`. POST http method that will receive json find price request.
```shell
Example:

{
    "application_date": "2020-06-16T00:00:00.000Z",
    "product_id":35455,
    "brand_id": 1
}
```
- `http://localhost:8081/v3/api-docs`. OpenAPI schema auto-generated from the swagger annotation provided by the `springdoc` dependency.
- `http://localhost:8081/swagger-ui.html`. Swagger interface based on the OpenAPI auto-generated schema that helps you to test resource endpoints.


