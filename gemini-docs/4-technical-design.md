# Technical Design for Modernization

This document outlines the technical design for migrating the legacy `ContactManager` application to a modern Java 21 application.

## 1. Project Structure

The new application will be structured as a multi-module Maven project. This structure promotes separation of concerns and modularity.

```
contact-manager/
├── pom.xml
├── contact-manager-app/
│   ├── pom.xml
│   └── src/
├── contact-manager-business/
│   ├── pom.xml
│   └── src/
├── contact-manager-data/
│   ├── pom.xml
│   └── src/
├── contact-manager-unit-tests/
│   ├── pom.xml
│   └── src/
└── contact-manager-integration-tests/
    ├── pom.xml
    └── src/
```

| Module                          | Description                                                                                             |
|---------------------------------|---------------------------------------------------------------------------------------------------------|
| `contact-manager-app`           | The main application module, built with Spring Boot. This module will expose a RESTful API.             |
| `contact-manager-business`      | Contains the business logic of the application.                                                         |
| `contact-manager-data`          | The data access layer, responsible for interacting with the database using Spring Data JPA.             |
| `contact-manager-unit-tests`    | Contains unit tests for all modules.                                                                    |
| `contact-manager-integration-tests` | Contains integration tests for verifying end-to-end functionality.                                      |

## 2. Key Libraries and Technologies

| Technology                | Version/Library                               | Purpose                                                                                                |
|---------------------------|-----------------------------------------------|--------------------------------------------------------------------------------------------------------|
| **Java**                  | 21                                            | The core programming language.                                                                         |
| **Build Tool**            | Maven                                         | For managing dependencies and building the project.                                                    |
| **Framework**             | Spring Boot 3                                 | For creating the stand-alone, production-grade Spring based Applications.                              |
| **Data Access**           | Spring Data JPA / Hibernate                   | For persisting data to the database.                                                                   |
| **Database**              | PostgreSQL                                    | The new relational database.                                                                           |
| **Database Driver**       | PostgreSQL JDBC Driver                        | For connecting to the PostgreSQL database.                                                             |
| **Unit Testing**          | JUnit 5, Mockito, AssertJ                     | For writing and running unit tests.                                                                    |
| **Integration Testing**   | Testcontainers, REST Assured                  | For writing and running integration tests.                                                             |
| **Schema Migration**      | Flyway or Liquibase                           | For managing database schema changes.                                                                  |

## 3. Architectural Patterns

### RESTful API

The new application will expose a RESTful API for managing contacts. The API will be stateless and follow standard HTTP conventions.

**Example Endpoints:**

| Method | Path              | Description                 |
|--------|-------------------|-----------------------------|
| `GET`    | `/api/contacts`     | Get all contacts.           |
| `GET`    | `/api/contacts/{id}`| Get a specific contact.     |
| `POST`   | `/api/contacts`     | Create a new contact.       |
| `PUT`    | `/api/contacts/{id}`| Update an existing contact. |
| `DELETE` | `/api/contacts/{id}`| Delete a contact.           |

### Dependency Injection

The application will use Spring's dependency injection to manage components and their dependencies. This will make the code more modular and easier to test.

### Repository Pattern

The data access layer will use the Repository pattern, provided by Spring Data JPA. This will abstract the data store and make it easier to switch between different database technologies.

### DTO (Data Transfer Object)

DTOs will be used to transfer data between the different layers of the application. This will help to decouple the layers and prevent leaking of internal data structures.

## 4. Database Migration Strategy

The existing SQLite database will be migrated to PostgreSQL.

### Schema Migration

A database migration tool like **Flyway** or **Liquibase** will be used to manage and version the database schema. The initial schema will be created from the `1-database-schema.md` document.

### Data Migration

A separate script will be written to migrate the data from the existing SQLite database to the new PostgreSQL database. This script will only be executed once during the migration process.
