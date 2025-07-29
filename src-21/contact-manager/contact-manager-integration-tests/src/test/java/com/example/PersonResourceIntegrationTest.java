package com.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import org.springframework.context.annotation.ComponentScan;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = com.example.App.class)
@Testcontainers
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.example")
class PersonResourceIntegrationTest {

    @LocalServerPort
    private Integer port;

    @Container
    private static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
    }

    @Test
    void shouldAddAndRetrievePerson() {
        Person person = new Person(1L, "John", "Doe", "2000-01-01", "25", "john.doe@example.com", "1234567890", "123 Main St", "", "Anytown", "12345", "CA", "USA");

        given()
                .contentType(ContentType.JSON)
                .body(person)
                .when()
                .post("/persons")
                .then()
                .statusCode(200);

        given()
                .when()
                .get("/persons")
                .then()
                .statusCode(200)
                .body("", hasSize(1))
                .body("[0].firstName", is("John"))
                .body("[0].lastName", is("Doe"));
    }

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }
}